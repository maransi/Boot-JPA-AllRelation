CREATE TABLE supplier( id                   INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        name                VARCHAR(50));

CREATE TABLE unit( id 		                INTEGER 	NOT NULL AUTO_INCREMENT PRIMARY KEY,
					name	                VARCHAR(50) );

CREATE TABLE product( id 	                INTEGER 	NOT NULL AUTO_INCREMENT PRIMARY KEY,
					description             VARCHAR(100),
                    alias                   VARCHAR(30),
					unit_id	                INTEGER,
					unit_price               DECIMAL(10,2),
                    supplier_id             INTEGER);

CREATE TABLE stock( id                      INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
                    name                    VARCHAR(50),
                    address                 VARCHAR(50),
                    city                    VARCHAR(50),
                    zip_code                 VARCHAR(9),
                    phone_number             VARCHAR(15));

CREATE TABLE stock_product( stock_id        INTEGER     NOT NULL,
                            product_id      INTEGER     NOT NULL,
                            quantity        DECIMAL(10,2),
                            CONSTRAINT pkStockProduct PRIMARY KEY( stock_id, product_id) );

CREATE TABLE customer( 	id 	                INTEGER 	NOT NULL AUTO_INCREMENT PRIMARY KEY,
						name	            VARCHAR(50) NOT NULL,
                        address                 VARCHAR(50),
                        city                    VARCHAR(50),
                        zip_code                 VARCHAR(9),
                        phone_number             VARCHAR(15));

CREATE TABLE purchase_order( 	id 	                INTEGER 	NOT NULL AUTO_INCREMENT,
                        order_number        VARCHAR(10),
						order_date	        DATE,
						customer_id	        INTEGER NOT NULL,
                        total_amount        DECIMAL(10,2),
						CONSTRAINT pkPurchaseOrder PRIMARY KEY( id ) );

CREATE TABLE itens_purchase_order(	purchase_order_id		INTEGER 	NOT NULL,
							id			    INTEGER     NOT NULL AUTO_INCREMENT,
							product_id	    INTEGER,
							quantity	    DECIMAL(10,2),
							CONSTRAINT pkItensPurchaseOrder PRIMARY KEY( purchase_order_id, id ),
                            CONSTRAINT fkItensPurchaseOrder_PurchaseOrder FOREIGN KEY( purchase_order_id ) REFERENCES purchase_order );

CREATE TABLE form_payment(  id              INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            description     VARCHAR(50) );

CREATE TABLE payment(       id              INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            purchase_order_id        INTEGER,
                            total_amount    DECIMAL(10,2),
                            form_payment_id INTEGER,
                            invoice_due_date    DATE,
                            payment_date        DATE);

create table employee (
       id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name varchar(50));

create table active_employee (
       experience integer not null AUTO_INCREMENT PRIMARY KEY,
        salary DECIMAL(11,2) not null,
        id bigint not null);

create table retired_employee (
       pension DECIMAL(11,2) not null,
        id bigint not null,
        primary key (id)
);


create table topic (
       topic_type VARCHAR(1) not null,
        id bigint not null AUTO_INCREMENT PRIMARY KEY,
        created_on timestamp,
        owner varchar(100),
        title varchar(100),
        valid_until timestamp,
        content varchar(255)
    );

   create table company_person (
       id bigint not null AUTO_INCREMENT,
        name varchar(50),
        ein varchar(15),
        foundation_date date,
        primary key (id)
    );

    create table contributor (
       id bigint not null AUTO_INCREMENT,
        name varchar(50),
        birthday_date date,
        social_security_number varchar(15),
        primary key (id)
    );
/*    
    create table person (
       id bigint not null,
        name varchar(10),
        primary key (id)
    );
*/


CREATE TABLE article (
  id bigint NOT NULL AUTO_INCREMENT,
  title varchar(100) NOT NULL,
  category varchar(50) NOT NULL,
  PRIMARY KEY (id)
);
