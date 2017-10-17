create database blog;
use blog;
create table user
(
id int not null auto_increment primary key,
account varchar(15) not null,
password varchar(16) not null
)