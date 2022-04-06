package com.bootcamp.channelpaymentservice.repositories.impl;

import com.bootcamp.channelpaymentservice.core.exceptions.PaymentBaseException;
import com.bootcamp.channelpaymentservice.dtos.requests.FavoriteRequest;
import com.bootcamp.channelpaymentservice.dtos.requests.TransactionRequest;
import com.bootcamp.channelpaymentservice.dtos.responses.FavoriteResponse;
import com.bootcamp.channelpaymentservice.dtos.responses.ServicioResponse;
import com.bootcamp.channelpaymentservice.dtos.responses.TransactionResponse;
import com.bootcamp.channelpaymentservice.repositories.PaymentRepository;
import com.bootcamp.channelpaymentservice.util.ServicioCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final WebClient webClientApiBusinessFavorite;
    private final WebClient webClientApiBusinessPayment;

    private final ReactiveRedisOperations<String, ServicioCache> redisOperations;
    private final ReactiveHashOperations<String, String, ServicioCache> hashOperations;

    public PaymentRepositoryImpl(WebClient.Builder builder,
                                 @Value("${application.urlApiBusinessPayments}") String urlApiBusinessPayments,
                                 @Value("${application.urlApiBusinessFavorites}") String urlApiBusinessFavorites,
                                 ReactiveRedisOperations<String, ServicioCache> redisOperations) {
        this.redisOperations = redisOperations;
        this.hashOperations = redisOperations.opsForHash();

        this.webClientApiBusinessPayment = builder.baseUrl(urlApiBusinessPayments).build();
        this.webClientApiBusinessFavorite = builder.baseUrl(urlApiBusinessFavorites).build();

        // Congigurar Response timeout
//        HttpClient client = HttpClient.create()
//                .responseTimeout(Duration.ofSeconds(5));
//        this.webClient = builder.baseUrl(urlApiBusinessPayments)
//                .clientConnector(new ReactorClientHttpConnector(client))
//                .build();

    }

    @Override
    public Flux<ServicioResponse> findByChannelCode(String channelCode) {

        return this.hashOperations.get("servicios", channelCode)
                .switchIfEmpty(this.callerApiBusinessPaymentsAndCache(channelCode)
                        .collectList()
                        .map(responseList -> ServicioCache.builder().servicioResponseList(responseList).build())
                )
                .map(ServicioCache::getServicioResponseList)
                .flatMapIterable(x -> x);
    }

    private Flux<ServicioResponse> callerApiBusinessPaymentsAndCache(String channelCode) {

        return this.webClientApiBusinessPayment.get().uri("/servicios/channelCode/{channelCode}", channelCode).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new PaymentBaseException("Server error")))
                .bodyToFlux(ServicioResponse.class)
                .switchIfEmpty(Mono.error(new PaymentBaseException("El canal enviado no tiene servicios registrados")))
//                .retryWhen(
//                        Retry.fixedDelay(3, Duration.ofSeconds(2))
//                                .doBeforeRetry(retrySignal -> log.info("log before retry=" + retrySignal))
//                                .doAfterRetry(retrySignal -> log.info("log after retry=" + retrySignal))
//                )
                .collectList()
                .flatMap(responseList -> {
                            ServicioCache servicioCache = ServicioCache.builder().servicioResponseList(responseList).build();
                            return this.hashOperations.put("servicios", channelCode, servicioCache)
                                    .thenReturn(servicioCache);
                        }
                )
                .map(ServicioCache::getServicioResponseList)
                .flatMapIterable(x -> x)
                .doOnError(throwable -> log.info("Log error"));
    }

    @Override
    public Mono<TransactionResponse> payService(TransactionRequest paymentRequest) {

        // Solo se pueden pagar servicios que esten registrados en el canal
        return this.hashOperations.get("servicios", paymentRequest.getChannelCode())
                .switchIfEmpty(this.callerApiBusinessPaymentsAndCache(paymentRequest.getChannelCode())
                        .collectList()
                        .map(responseList -> ServicioCache.builder().servicioResponseList(responseList).build())
                )
                .map(ServicioCache::getServicioResponseList)
                .map(responseList -> responseList.stream().anyMatch(servicioResponse -> servicioResponse.getServiceCode().equals(paymentRequest.getServiceCode())))
                .flatMap(aBoolean ->
                        aBoolean ? this.webClientApiBusinessPayment.post().uri("/transactions")
                                .body(Mono.just(paymentRequest), TransactionRequest.class)
                                .retrieve()
                                .bodyToMono(TransactionResponse.class)
                                .flatMap(transactionResponse ->
                                        this.webClientApiBusinessFavorite.post().uri("/favorites")
                                                .body(Mono.just(
                                                        FavoriteRequest
                                                                .builder()
                                                                .nombre(paymentRequest.getFavoriteRequest().getNombre())
                                                                .tipo(paymentRequest.getFavoriteRequest().getTipo())
                                                                .codigoServicio(paymentRequest.getServiceCode())
                                                                .build()
                                                ), FavoriteRequest.class)
                                                .retrieve()
                                                .bodyToMono(FavoriteResponse.class)
                                                .map(favoriteResponse -> Pair.of(transactionResponse, favoriteResponse))
                                )
                                .map(Pair::getFirst)
                                : Mono.error(new PaymentBaseException("El servicio a pagar no pertenece al canal")));
    }
}
