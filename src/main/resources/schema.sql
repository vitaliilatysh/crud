drop table if exists Country, Users;

create table Country (
    id              serial          primary key,
    name            varchar(50)     not null,
    short_code      varchar(10)     not null
);

create table Users (
    id              serial          primary key,
    name            varchar(50)     not null,
    email           varchar(50)     not null,
    roles           varchar(255)    not null,
    creation_date   timestamp       not null,
    country_id      int             not null,
    foreign key (country_id)        references Country(id) on delete cascade
);