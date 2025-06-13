CREATE DATABASE Khelmandu;
USE Khelmandu;
CREATE TABLE users(
id int auto_increment primary key,
f_name varchar(20),
l_name varchar(20),
ph_number long,
role VARCHAR(20),
password varchar(20)
);

CREATE TABLE Venue (
    id INT,
    venueName VARCHAR(255),
    venueContact VARCHAR(10),
    venueDescription TEXT,
    venueLocation VARCHAR(255),
    venuePrice INT,
    venueType VARCHAR(100),
    venueImage VARCHAR(500),
    FOREIGN KEY (id) REFERENCES users(id)
);