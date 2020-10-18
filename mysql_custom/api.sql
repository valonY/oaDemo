CREATE DATABASE IF NOT EXISTS oa default charset utf8 COLLATE utf8_general_ci;
USE oa;
DROP TABLE IF EXISTS `apis`;
CREATE TABLE `apis` (
	id BIGINT ( 10 ) NOT NULL AUTO_INCREMENT COMMENT 'user ID',
	group_name VARCHAR ( 30 ) DEFAULT NULL COMMENT 'group name',
	env_name VARCHAR ( 20 ) DEFAULT NULL COMMENT 'env name',
	api_url VARCHAR ( 300 ) NOT NULL COMMENT 'api address',
	api_name VARCHAR ( 50 ) DEFAULT NULL COMMENT 'api name',
	http_method ENUM ( 'GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'HEAD', 'OPTIONS' ) DEFAULT 'GET' COMMENT 'http method',
PRIMARY KEY ( id ) 
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin;