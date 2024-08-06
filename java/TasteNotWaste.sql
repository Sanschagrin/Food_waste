DROP DATABASE IF EXISTS foodwaste;
CREATE DATABASE foodwaste;
USE foodwaste;

CREATE TABLE charitable (
    charitable_id INT AUTO_INCREMENT PRIMARY KEY,
    charitable_name VARCHAR(100),
    charitable_email VARCHAR(100),
    charitable_password VARCHAR(20),
    charitable_description VARCHAR(2000),
    charitable_event_history INT
);

CREATE TABLE events (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100),
    event_description VARCHAR(2000),
    event_attendees INT,
    charitable_id INT,
    FOREIGN KEY (charitable_id) REFERENCES charitable(charitable_id)
);

CREATE TABLE consumers (
    consumer_id INT AUTO_INCREMENT PRIMARY KEY,
    consumer_name VARCHAR(50),
    consumer_email VARCHAR(100),
    consumer_password VARCHAR(20),
    subscriber BOOLEAN
);

CREATE TABLE retailers (
    retailer_id INT AUTO_INCREMENT PRIMARY KEY,
    retailer_name VARCHAR(50),
    retailer_description VARCHAR(2000),
    retailer_email VARCHAR(100),
    retailer_password VARCHAR(20)
);

CREATE TABLE inventory (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    retailer_id INT,
    item_name VARCHAR(100),
    item_description VARCHAR(200),
    standard_price DOUBLE,
    item_sale BOOLEAN,
    expiry_date DATE,
    quantity INT,
    FOREIGN KEY (retailer_id) REFERENCES retailers(retailer_id)
);

CREATE TABLE claims (
    claim_id INT AUTO_INCREMENT PRIMARY KEY,
    charitable_id INT,
    FOREIGN KEY (charitable_id) REFERENCES charitable(charitable_id)
);

CREATE TABLE purchases (
    purchase_id INT AUTO_INCREMENT PRIMARY KEY,
    consumer_id INT,
    FOREIGN KEY (consumer_id) REFERENCES consumers(consumer_id)
);

CREATE TABLE claim_items (
    claim_id INT,
    item_id INT,
    PRIMARY KEY (claim_id, item_id),
    FOREIGN KEY (claim_id) REFERENCES claims(claim_id),
    FOREIGN KEY (item_id) REFERENCES inventory(item_id)
);

CREATE TABLE purchase_items (
    purchase_id INT,
    item_id INT,
    PRIMARY KEY (purchase_id, item_id),
    FOREIGN KEY (purchase_id) REFERENCES purchases(purchase_id),
    FOREIGN KEY (item_id) REFERENCES inventory(item_id)
);

CREATE TABLE newsletter (
    newsletter_id INT AUTO_INCREMENT PRIMARY KEY,
    newsletter_name VARCHAR(100),
    newsletter_article VARCHAR(3000),
    item_id INT,
    sale_price DOUBLE,
    FOREIGN KEY (item_id) REFERENCES inventory(item_id)
);