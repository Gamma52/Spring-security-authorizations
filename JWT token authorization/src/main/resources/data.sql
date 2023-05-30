--CREATE TABLE users
--(
--	supplier_id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
--	login varchar(50) NOT NULL,
-- 	password varchar(50)NOT NULL,
-- 	position varchar(50),
-- 	role varchar(50) 	
--);


insert into Person(login, position, password, role) values('user', '1', 'password', 'USER');
insert into Person(login, position, password, role) values('admin', '2', 'password', 'ADMIN');