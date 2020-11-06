--Database connector assumes user with privelages named "Admin" with password "Password123"
--and that database is located at "jdbc:mysql://localhost:3306/"
--Create the database
CREATE DATABASE authority;

--Create the user table
create table users (
id int primary key not null auto_increment,
fname varchar(20) not null,
lname varchar(20) not null,
username varchar(20) not null,
userpass varchar(20) not null,
email varchar(40) not null
);

--Add some test data
insert into users(fname, lname, username, userpass, email) values("Andrew", "Fairbairn", "AFairbairn", "password", "admin@test.com");
insert into users(fname, lname, username, userpass, email) values("Steve", "Jobs", "SJobs", "password", "jobs@email.com");
insert into users(fname, lname, username, userpass, email) values("admin", "user", "admin", "password", "admin@test.com");