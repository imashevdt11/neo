create table products (
    id bigint auto_increment primary key,
    name  varchar(100) not null,
    price double not null
);

create table users (
    id bigint auto_increment primary key,
    email varchar(255) not null,
    password varchar(255) not null,
    role enum ('USER', 'ADMIN') not null,
    username varchar(255) not null
)
