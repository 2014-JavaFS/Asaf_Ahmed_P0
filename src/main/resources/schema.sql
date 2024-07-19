CREATE TABLE Client (
    client_id INT PRIMARY KEY AUTO_INCREMENT,
    client_name VARCHAR(255) NOT NULL UNIQUE,
    client_email VARCHAR(255) NOT NULL UNIQUE,
    client_password VARCHAR(255) NOT NULL,
);
CREATE TABLE Account (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    account_type Account_Type ,
    account_balance DOUBLE PRECISION not null,
    primary_client_id int not null,
    FOREIGN KEY (primary_client_id) REFERENCES Client(client_id)
);
