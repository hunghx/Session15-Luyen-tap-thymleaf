create database demo_login;
use demo_login;
create table Roles(
                      id int auto_increment primary key ,
                      role_name varchar(50)
);
insert into Roles(role_name) values ('ROLE_ADMIN'),('ROLE_USER');
create table Accounts(
                         id int auto_increment primary key ,
                         username varchar(50) unique ,
                         password varchar(100) ,
                         fullname varchar(100),
                         avatar_url varchar(255),
                         status bit default 1,
                         role_id int default 2,
                         foreign key (role_id) references Roles(id)
);



