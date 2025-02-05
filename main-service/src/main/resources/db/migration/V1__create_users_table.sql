CREATE TABLE users
(
    id       uuid,
    name     varchar(250)        not null,
    email    varchar(254) unique not null,
    password varchar(254)        not null,
    role     varchar(254)        not null,
    CONSTRAINT users_pk PRIMARY KEY (id),
    CONSTRAINT name_min_length CHECK (char_length(name) >= 2),
    CONSTRAINT email_min_length CHECK (char_length(email) >= 6)
);



