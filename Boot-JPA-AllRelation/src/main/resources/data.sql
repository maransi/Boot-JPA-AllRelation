INSERT INTO supplier(id, name)
VALUES(1001, 'ALCOA'),
        (1002, 'GERDAL'),
        (1003, 'USIMINAS'),
        (1004, 'CSN');

INSERT INTO unit( name )
VALUES( 'METRO' ),
        ('QUILO'),
        ('LITRO');

INSERT INTO product( description, alias, unit_id, unit_price, supplier_id )
VALUES( 'CHAPA DE ACO GALVANIZADA DE 3"', 'CHAPA DE ACO', 1, 10.09, 1001),
        ('BARRA DE ACO GALVANIZADA DE 3 1/4', 'BARRA DE ACO', 1, 20.5, 1002),
        ('SOLDA MIG/MAG (GMAW)','SOLDA' ,2, 30.50, 1003);

INSERT INTO stock( name, address, city, zip_code, phone_number )
VALUES( 'CENTRAL', 'RUA 25 DE MARCO 100', 'SAO PAULO', '01009-907', '(11) 2034-1234'),
        ('GRANDE SP', 'AV DO ESTADO 10500', 'SANTO ANDRE','05001-000', '(11) 2345-4321'),
        ('INTERIOR SP', 'AV ALÇAPAO 10','BRODOSKI','04030-100', '(18) 4365-0786'),
        ('DEMAIS REGIOES', 'ROD PRESIDENTE DUTRA','CATAGUASES','20324-200','(21) 3254-8743');


INSERT INTO stock_product( stock_id, product_id )
VALUES(1,1),
        (1,2),
        (1,3),
        (2,1),
        (3,2);

INSERT INTO customer( name, address, city, zip_code, phone_number )        
VALUES( 'ANTUNES ALVES SOUZA', 'R CANTA GALO 300', 'SAO PAULO', '09746-100', '(11) 2376-1050'),
        ('GERALDO LOMBARDI', 'R 7 DE SETEMBRO 345', 'RECIFE','58978-000','(45) 3487-0912'),
        ('PETER JUNIOR', 'PCA 14 BIS 76', 'BELO HORIZONTE', '36120-100', '(31) 95418-1050');

INSERT INTO purchase_order( order_number, order_date, customer_id, total_amount )
VALUES( '1000', '2020-01-10', 1, 10.50),
        ('1001', '2020-02-15',2, 100.40),
        ('1003', '2020-02-25', 2, 50.40);

INSERT INTO itens_purchase_order( purchase_order_id, product_id, quantity )
VALUES( 1, 1, 10),
        (1, 2, 100),
        (2, 3, 500),
        (3, 1, 10),
        (3, 3, 1000);

INSERT INTO form_payment( description )
VALUES( 'A VISTA'),
        ('PARCELADO PARA 30 DIAS'),
        ('PARCELADO EM 2 VEZES'),
        ('PARCELADO EM 3 VEZES');


INSERT INTO payment( purchase_order_id, total_amount, form_payment_id, invoice_due_date, payment_date )
VALUES( 1, 6, 1, '2020-01-31', null ),
        ( 1, 4.5, 1,'2020-02-29', null),
        (2, 100.40, 3, '2020-02-10',null),
        (3, 50.40, 4, '2020-03-01',null);


INSERT INTO employee( name ) 
VALUES('PETER'),
        ('MICHAEL'),
        ('JENIFFER'),
        ('ROSENA');

INSERT INTO active_employee( id, experience, salary )
VALUES(1,2, 5000),
        (3,5,8500);

INSERT INTO retired_employee( id, pension )
VALUES(2, 2500),
        (4, 3000);

INSERT INTO topic( topic_type, id, created_on, owner, title, content )
VALUES( 'P', 1, '2020-01-31', 'JOSEPH','SUBJECT TEXT','WHO DEAD CELSO DANIEL' ),
( 'P', 2, '2020-01-31', 'JOSEPH','SUBJECT TEXT','WHO DEAD CELSO DANIEL' ),
( 'P', 3, '2020-01-31', 'MARY','FINANCIAL','ASSET SCORE BALANCE' ),
( 'P', 4, '2020-01-31', 'EDMOND','TECHNOLOGY','PLAN SYSTEM' );

INSERT INTO topic( topic_type, id, created_on, owner, title, valid_until )
VALUES( 'A', 5, '2020-01-31', 'MARGARETH','INFORM', '2020-01-01' ),
( 'A', 6, '2020-01-31', 'SOPHIA','TAX','2019-05-30' ),
( 'A', 7, '2020-01-31', 'ANTONY','ACCOUNT','2018-02-28' ),
( 'A', 8, '2020-01-31', 'JAMES','DESIGNER','2017-10-31' );

CREATE SEQUENCE pes_seq START WITH 1 INCREMENT BY 1;
INSERT INTO company_person( id, name, ein, foundation_date )
VALUES(pes_seq.nextval, 'SAMSUNG', '1234567','2000-01-01' ),
        (pes_seq.nextval, 'SADIA', '87654321','2001-01-01');

INSERT INTO contributor( id, name, birthday_date, social_security_number )
VALUES( pes_seq.nextval,'JOSEPH','1960-01-01','123456'),
        (pes_seq.nextval,'MARIAH','1980-01-01','654321');

INSERT INTO article (title, category) VALUES
	('Java Concurrency', 'Java'),
	('Spring Boot Getting Started', 'Spring Boot'),
	('Lambda Expressions Java 8 Example', 'Java 8'), 
	('Spring Boot Advanced', 'Spring Boot'); 

/*
INSERT INTO company_person( name, ein, foundation_date )
VALUES( 'SAMSUNG', '1234567','2000-01-01' ),
        ( 'SADIA', '87654321','2001-01-01');

INSERT INTO contributor( name, birthday_date, social_security_number )
VALUES( 'JOSEPH','1960-01-01','123456'),
        ('MARIAH','1980-01-01','654321');
*/

