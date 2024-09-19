create table if not exists USERS
(
	ID int primary key,
	USER_NAME varchar(255) not null,
	EMAIL varchar(255) not null,
	LOGIN varchar(255) not null,
	BIRTHDAY date,
	constraint UNIQUE_EMAIL unique (EMAIL),
    constraint UNIQUE_LOGIN unique (LOGIN)
);
create table if not exists FILMS
(
    ID int primary key,
    FILM_NAME varchar(255) not null,
    DESCRIPTION varchar(200),
    RELEASE_DATE date,
    DURATION time,
    GENRE varchar(50)
);
create table if not exists LIKES
(
    FILM_ID int REFERENCES FILMS (FILM_ID),
    USER_ID int REFERENCES USERS (USER_ID)
);
create table if not exists RATING_MPA
(
    FILM_ID int,
    RATING_NAME varchar(50)
);
create table if not exists GENRES_FILM
(
    FILM_ID int REFERENCES FILMS (FILM_ID),
    GENRE_ID int REFERENCES GENRE (GENRE_ID)
);
create table if not exists GENRE
(
    GENRE_ID int,
    NAME_GENRE varchar(50)
);
CREATE TABLE IF NOT EXISTS FRIENDS
(
    USER_ID INT REFERENCES USERS (USER_ID),
    FRIEND_ID INT REFERENCES USERS (USER_ID),
    STATUS VARCHAR(50)
);
