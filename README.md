# Spring Boot Web Application Template
Template of Web Application, so I can literally copy and paste to start a web application.
Just for the testing purpose, Embedded H2 database is used.

## Template contains
* Framework : Spring Boot - 1.5.7
* Front End : AngularJS - 2.0
* ORM : JPA / Hibernate
* Database : H2 - h2-2017-04-23
* Unit Test : jUnit + AssertJ
* Code Quality : Jacoco

## Initialization
Run the project by the following command `./gradlew bootRun` then when the Tomcat server is up, go to `http://localhost:8080/h2-console` on the browser.

#### Create Test Database
```
CREATE TABLE TEST_USER(
    USER_ID VARCHAR(10) PRIMARY KEY,
    PW VARCHAR(20) NOT NULL,
    NAME VARCHAR(20) NOT NULL,
    TITLE VARCHAR(10),
    AGE INT
);

CREATE TABLE POST(
    POST_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID VARCHAR(10),
    SUBJECT VARCHAR(50),
    CONTENT VARCHAR(200),
    CREATED_DATETIME DATETIME,
    FOREIGN KEY (USER_ID) REFERENCES TEST_USER(USER_ID)
);

```
![Alt](docs/screenshot-01.png)

#### Add some test data
```
INSERT INTO TEST_USER(USER_ID, PW, NAME, TITLE, AGE) VALUES( 'tester1', '1234', 'Brian Heo', 'Sir', 42);
INSERT INTO TEST_USER(USER_ID, PW, NAME, TITLE, AGE) VALUES('tester2', '1234', 'Phil Lee', 'Mr.', 42);
INSERT INTO TEST_USER(USER_ID, PW, NAME, AGE) VALUES('tester3', '1234', 'Gerrard Lee', 43);

INSERT INTO POST(USER_ID, SUBJECT, CONTENT, CREATED_DATETIME) VALUES( 'tester1', 'Test Subject 1', 'Contents of Test subject 1', CURRENT_TIMESTAMP());
INSERT INTO POST(USER_ID, SUBJECT, CONTENT, CREATED_DATETIME) VALUES( 'tester2', 'Test Subject 2', 'Contents of Test subject 2', CURRENT_TIMESTAMP());
INSERT INTO POST(USER_ID, SUBJECT, CONTENT, CREATED_DATETIME) VALUES( 'tester1', 'Test Subject 3', 'Contents of Test subject 3', CURRENT_TIMESTAMP());
INSERT INTO POST(USER_ID, SUBJECT, CONTENT, CREATED_DATETIME) VALUES( 'tester2', 'Test Subject 4', 'Contents of Test subject 4', CURRENT_TIMESTAMP());
INSERT INTO POST(USER_ID, SUBJECT, CONTENT, CREATED_DATETIME) VALUES( 'tester1', 'Test Subject 5', 'Contents of Test subject 5', CURRENT_TIMESTAMP());
INSERT INTO POST(USER_ID, SUBJECT, CONTENT, CREATED_DATETIME) VALUES( 'tester1', 'Test Subject 6', 'Contents of Test subject 6', CURRENT_TIMESTAMP());
INSERT INTO POST(USER_ID, SUBJECT, CONTENT, CREATED_DATETIME) VALUES( 'tester1', 'Test Subject 7', 'Contents of Test subject 7', CURRENT_TIMESTAMP());
INSERT INTO POST(USER_ID, SUBJECT, CONTENT, CREATED_DATETIME) VALUES( 'tester3', 'Test Subject 8', 'Contents of Test subject 8', CURRENT_TIMESTAMP());
INSERT INTO POST(USER_ID, SUBJECT, CONTENT, CREATED_DATETIME) VALUES( 'tester1', 'Test Subject 9', 'Contents of Test subject 9', CURRENT_TIMESTAMP());
```

## Unit Testing


## Integration Test


## Code Quality Check

