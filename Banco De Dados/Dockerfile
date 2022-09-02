FROM mysql:8.0

# Create workdir
WORKDIR /usr/src/db

COPY ./docker-create-container.sh ./
COPY ./docker-create-db.sh ./
COPY ./sql/ ./