drop table if exists Country, Users, Roles, Users_Roles cascade;

create table Country (
    id              serial          primary key,
    name            varchar(50)     not null,
    short_code      varchar(10)     not null
);

create table Users (
    id              serial          primary key,
    name            varchar(50)     not null,
    email           varchar(50)     not null,
    creation_date   timestamp       not null,
    country_id      int             not null,
    foreign key (country_id)        references Country(id) on delete cascade
);

create table Roles (
    id              serial          primary key,
    name            varchar(50)     not null
);

create table Users_Roles (
    user_id         int             not null,
    role_id         int             not null,
	foreign key (user_id)	references Users(id) on update cascade,
	foreign key (role_Id)	references Roles(id) on update cascade

);