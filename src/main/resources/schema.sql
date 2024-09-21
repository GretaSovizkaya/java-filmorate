create table if not exists USERS (
    ID int primary key,
    USER_NAME varchar(255) not null,
    EMAIL varchar(255) not null,
    LOGIN varchar(255) not null,
    BIRTHDAY date,
    constraint UNIQUE_EMAIL unique (EMAIL),
    constraint UNIQUE_LOGIN unique (LOGIN)
);

create table if not exists FILMS (
    ID int primary key,
    FILM_NAME varchar(255) not null,
    DESCRIPTION varchar(200),
    RELEASE_DATE date,
    DURATION time,
    GENRE varchar(50)
);

create table if not exists LIKES (
    FILM_ID int references FILMS (ID),
    USER_ID int references USERS (ID)
);

create table if not exists RATING (
    FILM_ID int references FILMS (ID),
    RATING_NAME varchar(50)
);

create table if not exists GENRES (
    GENRE_ID int primary key,
    NAME_GENRE varchar(255) not null
);

create table if not exists GENRES_FILM (
    FILM_ID int references FILMS (ID),
    GENRE_ID int references GENRES (GENRE_ID)
);

create table if not exists FRIENDS (
    USER_ID int references USERS (ID),
    FRIEND_ID int references USERS (ID),
    STATUS varchar(50)
);

