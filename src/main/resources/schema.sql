DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT NOT NULL,
    user_id   varchar(20) UNIQUE NULL,
    user_name   varchar(20)   NULL,
    user_password   varchar(20)   NULL,
    email   varchar(50)   NULL,
    phone   char(13)   NULL,
    delete_at   char(1)   NULL,

    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS center;

CREATE TABLE center (
    center_sn   int   NOT NULL AUTO_INCREMENT,
    center_name   varchar(100)   NULL,
    center_location   varchar(100)   NULL,

    PRIMARY KEY (center_sn)
);

DROP TABLE IF EXISTS instructor;

CREATE TABLE instructor (
    instructor_sn   int   NOT NULL AUTO_INCREMENT,
    center_sn   int   NULL,
    instructor_name   varchar(20)   NULL,

    PRIMARY KEY (instructor_sn)
);

DROP TABLE IF EXISTS inquiry;

CREATE TABLE inquiry (
     inq_sn   int   NOT NULL AUTO_INCREMENT,
     center_sn   int   NULL,
     inq_name   varchar(200)   NULL,
     inq_contents   text   NULL,
     user_id   varchar(20)   NULL,
     regist_dt   date   NULL,
     delete_at   char(1)   NULL,

     PRIMARY KEY (inq_sn)
);

DROP TABLE IF EXISTS lesson;

CREATE TABLE lesson (
    lesson_sn   int   NOT NULL AUTO_INCREMENT,
    center_sn   int   NULL,
    lesson_name   varchar(100)   NULL,
    instructor_sn   int   NULL,
    personnel   int   NULL,
    lesson_day   varchar(10)   NULL,
    start_time   time   NULL,
    end_time   time   NULL,

    PRIMARY KEY (lesson_sn)
);

DROP TABLE IF EXISTS subscription;

CREATE TABLE subscription (
    subs_sn   int   NOT NULL AUTO_INCREMENT,
    center_sn   int   NULL,
    subs_name   varchar(100)   NULL,
    subs_price   int   NULL,
    use_month   int   NULL,

    PRIMARY KEY (subs_sn)
);

DROP TABLE IF EXISTS review;

CREATE TABLE review (
    review_sn   int   NOT NULL AUTO_INCREMENT,
    instructor_sn   int   NULL,
    center_sn   int   NULL,
    user_id   varchar(20)   NULL,
    review_contents   text   NULL,
    regist_dt   date   NULL,
    delete_at   char(1)   NULL,

    PRIMARY KEY (review_sn)
);

DROP TABLE IF EXISTS perchase;

CREATE TABLE perchase (
    pch_sn   int   NOT NULL AUTO_INCREMENT,
    subs_sn   int   NULL,
    user_id   varchar(20)   NULL,
    start_date   date   NULL,
    end_date   date   NULL,
    pay_date   timestamp   NULL,

    PRIMARY KEY (pch_sn)
);

DROP TABLE IF EXISTS reserve;

CREATE TABLE reserve (
    reserve_sn   int   NOT NULL AUTO_INCREMENT,
    lesson_sn   int   NULL,
    user_id   varchar(20)   NULL,
    reserve_date   date   NULL,
    cancel_at   char(1)   NULL,

    PRIMARY KEY (reserve_sn)
);