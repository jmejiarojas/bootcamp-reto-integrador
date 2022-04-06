use master;

create database integrador;

use integrador;

create table services(
                         id int identity(1,1) primary key,
                         serviceCode varchar(50),
                         channelCode varchar(50),
                         name varchar(250),
);

create table transactions(
                             id int identity(1,1) primary key,
                             channelCode varchar(50),
                             serviceCode varchar(50),
                             supplyNumber varchar(50),
                             amount NUMERIC,
                             dateTime DATETIME
);

insert into [dbo].[services](serviceCode, channelCode, name) values('SE001','NTLC', 'Transferencias');
insert into [dbo].[services](serviceCode, channelCode, name) values('SE002','NTLC', 'Carta Fianza');
insert into [dbo].[services](serviceCode, channelCode, name) values('SE003','TLC', 'AutoDesembolso');

select * from services;
select * from transactions;