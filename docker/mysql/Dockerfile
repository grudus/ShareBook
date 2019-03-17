FROM mysql:8

# context is set to root dir
COPY ./docker/mysql/custom.cnf /etc/mysql/conf.d/custom.cnf
COPY ./docker/mysql/init.sql /docker-entrypoint-initdb.d
