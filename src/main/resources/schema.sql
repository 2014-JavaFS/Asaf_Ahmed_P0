CREATE TABLE Client (
    client_id INT PRIMARY KEY AUTO_INCREMENT,
    client_name VARCHAR(255) NOT NULL UNIQUE,
    client_email VARCHAR(255) NOT NULL UNIQUE,
    client_password VARCHAR(255) NOT NULL,
);
CREATE TABLE Account (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    account_type Account_Type default "CHECKING" ,
    account_balance DOUBLE PRECISION not null check (account_balance>0),
    primary_client_id int not null,
    FOREIGN KEY (primary_client_id) REFERENCES Client(client_id) ON DELETE CASCADE
);
insert into client(client_name, client_email, client_password) values('john','john@gmail.com','5691');
insert into client(client_name, client_email, client_password) values('jimmy','jimmy@gmail.com','5691');
insert into client(client_name, client_email, client_password) values('tom','tom@gmail.com','5691');
insert into account(account_type,account_balance,primary_client_id) values('CHECKING'::public."account_type",5000,1);
insert into account(account_type,account_balance,primary_client_id) values('SAVING'::public."account_type",10000,2);
insert into account(account_type,account_balance,primary_client_id) values('CHECKING'::public."account_type",50000,3);
