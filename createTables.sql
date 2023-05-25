CREATE TABLE Users (
	username varchar(20),
	password varbinary(144) NOT NULL,
	PRIMARY KEY (username)
);

CREATE TABLE Surveys (
	username varchar(20),
    firstDorm varchar(20),
    secondDorm varchar(20),
    thirdDorm varchar(20),
    roomType int CHECK (roomType >= 1 and roomType <= 6),
    genderInclusive int CHECK (genderInclusive >= 0 and genderInclusive < 2),
    studentYear int CHECK (studentYear >= 1 and studentYear <= 4),
    roommateYear int CHECK ((roommateYear >= 1 and roommateYear <= 4) or roommateYear = -1),
    drinkingPref int CHECK (drinkingPref >= 0 and drinkingPref < 2),
    wakeTime int CHECK (wakeTime >= 0 and wakeTime < 24),
    sleepTime int CHECK (sleepTime >= 0 and sleepTime < 24),
    heavySleep int CHECK (heavySleep >= 0 and heavySleep < 2),
    studentVert int CHECK (studentVert >= 0 and studentVert < 3),
    roommateVert int CHECK (roommateVert >= -1 and roommateVert < 3),
    studentFriends int CHECK (studentFriends >= 0 and studentFriends < 2),
    roommateFriends int CHECK (roommateFriends >= 0 and roommateFriends < 2),
    studentNeat int CHECK (studentNeat >= 0 and studentNeat < 2),
    roommateNeat int CHECK (roommateNeat >= 0 and roommateNeat < 2),
    hobbies varchar(500),
	PRIMARY KEY (username),
    FOREIGN KEY (username) REFERENCES Users(username)
);

CREATE TABLE Contact_Info (
	username varchar(20),
	email varbinary(2048),
    phoneNumber varbinary(2048),
	discord varbinary(2048),
	PRIMARY KEY (username),
    FOREIGN KEY (username) REFERENCES Users(username)
);

CREATE TABLE Matches (
	user1 varchar(20),
	user2 varchar(20),
	compatibility float CHECK (compatibility >= 0 AND compatibility <= 100 and compatibility is NOT NULL),
	matchStatus int CHECK (matchStatus >= -1 AND matchStatus < 4 and matchStatus is NOT NULL),
	FOREIGN KEY (user1) REFERENCES Users(username),
	FOREIGN KEY (user2) REFERENCES Users(username),
	PRIMARY KEY (user1, user2),
	CHECK (user1 < user2)
);
