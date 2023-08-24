-- Set foreign keys to ON
SET REFERENTIAL_INTEGRITY TRUE;

-- Create COMPANY table
CREATE TABLE IF NOT EXISTS COMPANY (
                                       ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                                       NAME VARCHAR(255) NOT NULL,
                                       COUNTRY VARCHAR(255) NOT NULL,
                                       VAT VARCHAR(255) UNIQUE NOT NULL,
                                       TYPE VARCHAR(255) NOT NULL CHECK (TYPE IN ('provider', 'client')),
                                       TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create CONTACT table
CREATE TABLE IF NOT EXISTS CONTACT (
                                       ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                                       FIRSTNAME VARCHAR(255) NOT NULL,
                                       LASTNAME VARCHAR(255) NOT NULL,
                                       PHONE VARCHAR(15) NOT NULL,
                                       EMAIL VARCHAR(255) NOT NULL,
                                       TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       CONTACT_COMPANY_ID INTEGER,
                                       FOREIGN KEY (CONTACT_COMPANY_ID) REFERENCES COMPANY(ID)
);

-- Create INVOICE table
CREATE TABLE IF NOT EXISTS INVOICE (
                                       ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                                       TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       INVOICE_COMPANY_ID INTEGER,
                                       INVOICE_CONTACT_ID INTEGER,
                                       FOREIGN KEY (INVOICE_COMPANY_ID) REFERENCES COMPANY(ID),
                                       FOREIGN KEY (INVOICE_CONTACT_ID) REFERENCES CONTACT(ID)
);

-- Create USER table
CREATE TABLE IF NOT EXISTS "USER" (
                                      ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                                      USERNAME VARCHAR(255) NOT NULL,
                                      PASSWORD VARCHAR(255) NOT NULL,
                                      ROLE VARCHAR(255) NOT NULL CHECK (ROLE IN ('admin', 'accountant', 'intern'))
);

-- Insert data into COMPANY table
INSERT INTO COMPANY (NAME, COUNTRY, VAT, TYPE)
VALUES
    ('BeCode', 'Belgium', '1234567890', 'client'),
    ('ACME', 'United-States', '1234567891', 'provider');

-- Insert data into CONTACT table
INSERT INTO CONTACT (FIRSTNAME, LASTNAME, PHONE, EMAIL, CONTACT_COMPANY_ID)
VALUES
    ('John', 'Doe', '+32', 'johndoe@becode.org', 1),
    ('', '', '', '', NULL);

-- Insert data into USER table
INSERT INTO "USER" (USERNAME, PASSWORD, ROLE)
VALUES
    ('ranu', 'ranunu', 'admin'),
    ('stacy', 're_alpa55word', 'accountant'),
    ('joe', 'password', 'intern');