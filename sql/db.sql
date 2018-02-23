CREATE DATABASE krks02_warsztat2
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;


-- Table user_group schema
CREATE TABLE user_group(
id INT AUTO_INCREMENT,
name VARCHAR(255) UNIQUE,
PRIMARY KEY(id));

-- Table users schema
CREATE TABLE users(
id INT AUTO_INCREMENT,
username VARCHAR(255) UNIQUE,
email VARCHAR(255) UNIQUE,
password VARCHAR(255),
person_group_id INT,
PRIMARY KEY(id),
FOREIGN KEY(person_group_id) REFERENCES user_group(id));
  
-- Table excercise schema  
CREATE TABLE excercise(
id INT AUTO_INCREMENT,
title VARCHAR(255),
description TEXT,
PRIMARY KEY(id));

-- Table solution schema
CREATE TABLE solution(
id INT AUTO_INCREMENT,
created DATETIME,
updated DATETIME,
description TEXT,
excercise_id INT,
users_id INT,
PRIMARY KEY(id),
FOREIGN KEY(excercise_id) REFERENCES excercise(id),
FOREIGN KEY(users_id) REFERENCES users(id));
    