FROM mysql:latest

MAINTAINER valon

ENV SQL_FILE_NAME api.sql

ENV AUTO_RUN_DIR /docker-entrypoint-initdb.d

ENV INSTALL_DB_SQL ./api.sql

ADD $INSTALL_DB_SQL $AUTO_RUN_DIR

RUN chmod a+x $AUTO_RUN_DIR/$SQL_FILE_NAME
