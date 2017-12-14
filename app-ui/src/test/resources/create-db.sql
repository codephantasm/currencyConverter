-- Create the Database. If this fails, try creating manually.
CREATE DATABASE "Currency";

-- Create the Schema. If this fails, try creating manually.
CREATE SCHEMA "phantasm";

-- Create the table.
CREATE TABLE phantasm."Currencies"(
    Abbrev VARCHAR(3) PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Rate REAL NOT NULL
);

-- Populate DB with a sample value
INSERT INTO phantasm."Currencies" VALUES('TST', 'Test', 1.097)