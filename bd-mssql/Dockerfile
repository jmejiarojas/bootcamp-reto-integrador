FROM mcr.microsoft.com/mssql/server:latest

# Create work directory
RUN mkdir -p /usr/work
WORKDIR /usr/work

# Copy all scripts into working directory
COPY . /usr/work/

# Agregado
USER root

# Grant permissions for the import-data script to be executable
RUN chmod +x /usr/work/import-data.sh

EXPOSE 1433

CMD /bin/bash ./entrypoint.sh