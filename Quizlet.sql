USE c_cs108_jzhang4
DROP TABLE IF EXISTS siteVisits;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS announcements;
DROP TABLE IF EXISTS achievements;
DROP TABLE IF EXISTS friends;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS requests;


CREATE TABLE siteVisits(
	pageName CHAR(64),
	day1 BIGINT,
	day2 BIGINT,
	day3 BIGINT,
	day4 BIGINT,
	day5 BIGINT,
	day6 BIGINT,
	day7 BIGINT
);

INSERT INTO siteVisits VALUES
	("Home Page",0,3,4,0,2,2,9),
	("Terms and Conditions",5,8,10,12,8,4,7),
	("About Us",1,7,9,2,3,2,0);



CREATE TABLE users(
	id BIGINT AUTO_INCREMENT,
	username Char(64),
	passwordHash char(64),
	admin BIGINT,
	picKey BIGINT,
	privacy TINYINT DEFAULT 0,
	PRIMARY KEY (id)
);

INSERT INTO users VALUES
	(1,"liamNeath","86f7e437faa5a7fce15d1ddcb9eaeaea377667b8",1,0,0),
	(2,"jessicaZhang","86f7e437faa5a7fce15d1ddcb9eaeaea377667b8",0,3,0),
	(3,"Molly","86f7e437faa5a7fce15d1ddcb9eaeaea377667b8",0,3,1),
	(4,"HBU","86f7e437faa5a7fce15d1ddcb9eaeaea377667b8",0,4,0);


CREATE TABLE friends(
	user1 BIGINT,
	user2 BIGINT
);

INSERT INTO friends VALUES
	(1, 2),
	(1, 3);


CREATE TABLE messages(
	id BIGINT AUTO_INCREMENT,
	type char(64),
	sender char(64),
	recipient char(64),
	subject char (128),
	message LONGTEXT,
	quiz char(64),
	score MEDIUMINT,
	recipientRead TINYINT DEFAULT 0,
	PRIMARY KEY (id)
);

CREATE TABLE requests(
	senderID BIGINT,
	recipientID BIGINT,
	accepted char(64)
);

CREATE TABLE announcements(
	textOfNotice text,
	dateOfNotice char(100)
);

INSERT INTO announcements VALUES
	("HELLO! Welcome to Quizlett, I hope you have a great day!","06/03/2010"),
	("Please be advized, We have just added an administrator page","08/09/2010");

CREATE TABLE achievements(
	userName Char(64),
	achieve1 BIGINT,
	achieve2 BIGINT,
	achieve3 BIGINT,
	achieve4 BIGINT,
	achieve5 BIGINT,
	achieve6 BIGINT,
	achieve7 BIGINT
);

INSERT INTO achievements VALUES
("liamNeath",1,0,0,0,0,0,0),
("jessicaZhang",1,0,0,0,0,0,0),
("Molly",1,0,0,0,0,0,0);
