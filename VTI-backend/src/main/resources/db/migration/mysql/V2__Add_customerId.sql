ALTER TABLE user
    ADD COLUMN customer_id INT DEFAULT NULL,
ADD CONSTRAINT FK_UserCustomer FOREIGN KEY (customer_id) REFERENCES customer(customer_id);