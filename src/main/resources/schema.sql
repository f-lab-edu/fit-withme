DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT NOT NULL,
    user_id   varchar(20) UNIQUE NULL,
    user_name   varchar(20)   NULL,
    user_password   varchar(20)   NULL,
    email   varchar(50)   NULL,
    phone   char(13)   NULL,
    leaved   tinyint(1)   NULL,

    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS center;

CREATE TABLE center (
    center_id   int   NOT NULL AUTO_INCREMENT,
    center_name   varchar(100)   NULL,
    center_location   varchar(100)   NULL,

    PRIMARY KEY (center_id)
);

DROP TABLE IF EXISTS instructor;

CREATE TABLE instructor (
    instructor_id   int   NOT NULL AUTO_INCREMENT,
    center_id   int   NULL,
    instructor_name   varchar(20)   NULL,

    PRIMARY KEY (instructor_id)
);

DROP TABLE IF EXISTS inquiry;

CREATE TABLE inquiry (
     inquiry_id   int   NOT NULL AUTO_INCREMENT,
     center_id   int   NULL,
     inquiry_name   varchar(200)   NULL,
     inquiry_contents   text   NULL,
     user_id   varchar(20)   NULL,
     regist_dt   date   NULL,
     deleted   tinyint(1)   NULL,

     PRIMARY KEY (inquiry_id)
);

DROP TABLE IF EXISTS lesson;

CREATE TABLE lesson (
    lesson_id   int   NOT NULL AUTO_INCREMENT,
    center_id   int   NULL,
    lesson_name   varchar(100)   NULL,
    instructor_id   int   NULL,
    personnel   int   NULL,
    lesson_day   varchar(10)   NULL,
    start_time   time   NULL,
    end_time   time   NULL,

    PRIMARY KEY (lesson_id)
);

DROP TABLE IF EXISTS subscription;

CREATE TABLE subscription (
    subscription_id   int   NOT NULL AUTO_INCREMENT,
    center_id   int   NULL,
    subscription_name   varchar(100)   NULL,
    subscription_price   int   NULL,
    use_month   int   NULL,

    PRIMARY KEY (subscription_id)
);

DROP TABLE IF EXISTS review;

CREATE TABLE review (
    review_id   int   NOT NULL AUTO_INCREMENT,
    instructor_id   int   NULL,
    center_id   int   NULL,
    user_id   varchar(20)   NULL,
    review_contents   text   NULL,
    regist_dt   date   NULL,
    deleted   tinyint(1)   NULL,

    PRIMARY KEY (review_id)
);

DROP TABLE IF EXISTS perchase;

CREATE TABLE perchase (
    perchase_id   int   NOT NULL AUTO_INCREMENT,
    subscription_id   int   NULL,
    user_id   varchar(20)   NULL,
    start_date   date   NULL,
    end_date   date   NULL,
    pay_date   timestamp   NULL,

    PRIMARY KEY (perchase_id)
);

DROP TABLE IF EXISTS reserve;

CREATE TABLE reserve (
    reserve_id   int   NOT NULL AUTO_INCREMENT,
    lesson_id   int   NULL,
    user_id   varchar(20)   NULL,
    reserve_date   date   NULL,
    canceled   tinyint(1)   NULL,

    PRIMARY KEY (reserve_id)
);

DROP TABLE IF EXISTS reserve_number;

CREATE TABLE reserve_number (
     lesson_id   int   NULL,
     reserve_date   date   NULL,
     current_personnel   int   NULL,
);