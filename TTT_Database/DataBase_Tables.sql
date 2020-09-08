show databases;	
create database Gamers;
use Gamers;
select * from Signup;
desc signup;
drop tables signup;
insert into Signup (emailID,User_Pass,Username) values('Tu@gmail.com','1234','Tushar');
insert into Signup (emailID,User_Pass,Username) values('pr@gmail.com','1234','prakash');
insert into Signup (emailID,User_Pass,Username) values('subahm@gmail.com','1234','subham');
insert into Signup (emailID,User_Pass,Username) values('thunder@gmail.com','1234','thunder');
update Signup set User_pass='5467' where emailID='thunder@gmail.com';
create table Signup(
	id int auto_increment primary key,
   emailID varchar(50),
   User_Pass varchar(200),
   Username varchar(20)
);