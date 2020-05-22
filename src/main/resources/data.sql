insert into country (name, short_code) values
('Ukraine', 'UA'),
('Germany', 'GR');

insert into users (name, email, creation_date, country_id) values
('Dmytro', 'dmytro@gmail.com', '2017-03-31 9:30:20', 1),
('Daniel', 'daniel@gmail.com', '2018-01-05 8:00:00', 1);

insert into roles (name) values ('ADMIN'), ('USER');

insert into users_roles (user_id, role_id) values (1, 1), (1, 2), (2, 2);