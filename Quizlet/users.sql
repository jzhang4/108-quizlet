USE c_cs108_jzhang4;

DROP TABLE IF EXISTS users;
 -- remove table if it already exists and start from scratch

CREATE TABLE users (
	id MEDIUMINT NOT NULL AUTO_INCREMENT,
	username CHAR(64) NOT NULL,
	passwordHash CHAR(64) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE requests (
	senderID BIGINT NOT NULL,
	recipientID BIGINT NOT NULL,
	accepted CHAR(64) NOT NULL
);