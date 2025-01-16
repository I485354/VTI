CREATE TABLE customer (
                          customer_id INT AUTO_INCREMENT PRIMARY KEY,   -- Gebruik van AUTO_INCREMENT in plaats van IDENTITY
                          name VARCHAR(255) NOT NULL,
                          company VARCHAR(255),
                          address VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          phone VARCHAR(50) NOT NULL,
                          customer_number INT NOT NULL unique
);
CREATE TABLE car (
                     car_id INT AUTO_INCREMENT PRIMARY KEY,
                     customer_id INT NOT NULL, -- Koppeling met de klant
                     plate_number VARCHAR(20) NOT NULL UNIQUE, -- Kenteken
                     brand VARCHAR(50) NOT NULL, -- Merk
                     model VARCHAR(50) NOT NULL, -- Model
                     year INT, -- Bouwjaar
                     chasi_number VARCHAR(50) UNIQUE, -- Voertuigidentificatienummer (optioneel)
                     CONSTRAINT FK_VehicleCustomer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Tabel voor facturen
CREATE TABLE invoice (
                         invoice_id INT AUTO_INCREMENT PRIMARY KEY,
                         customer_id INT NOT NULL,
                         car_id INT NULL,
                         invoice_date DATE NOT NULL,
                         due_date DATE NOT NULL,
                         total_amount DECIMAL(18, 2) NOT NULL,
                         total_btw DECIMAL(10, 2) DEFAULT 0,
                         status VARCHAR(50) NOT NULL,
                         invoice_number INT NOT NULL unique,
                         deleted VARCHAR(10) NOT NULL,
                         CONSTRAINT FK_CustomerInvoice FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
                         CONSTRAINT FK_InvoiceCar FOREIGN KEY (car_id) REFERENCES car(car_id)
);

-- Tabel voor producten/diensten
CREATE TABLE product (
                         product_id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,  -- Gebruik van TEXT in plaats van NVARCHAR(MAX)
                         price DECIMAL(18, 2) NOT NULL,
                         quantity INT NOT NULL,
                         btw INT NOT NULL
);

-- Tabel voor factuurregels (koppeling tussen facturen en producten/diensten)
CREATE TABLE invoice_item (
                              invoice_item_id INT AUTO_INCREMENT PRIMARY KEY,   -- AUTO_INCREMENT voor MySQL
                              invoice_id INT NOT NULL,
                              product_id INT NOT NULL,
                              quantity INT NOT NULL,
                              unit_price DECIMAL(18, 2) NOT NULL,
                              btw DECIMAL(5, 2) NOT NULL,
                              total DECIMAL(18, 2) NOT NULL,
                              CONSTRAINT FK_InvoiceItemInvoice FOREIGN KEY (invoice_id) REFERENCES invoice(invoice_id),
                              CONSTRAINT FK_InvoiceItemProduct FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- Tabel voor betalingen
CREATE TABLE payment (
                         payment_id INT AUTO_INCREMENT PRIMARY KEY,    -- AUTO_INCREMENT voor MySQL
                         invoice_id INT NOT NULL,
                         payment_date DATE NOT NULL,
                         amount DECIMAL(18, 2) NOT NULL,
                         payment_method VARCHAR(50),
                         CONSTRAINT FK_PaymentInvoice FOREIGN KEY (invoice_id) REFERENCES invoice(invoice_id)
);

-- Tabel voor gebruikers
CREATE TABLE user (
                      user_id BIGINT AUTO_INCREMENT PRIMARY KEY,       -- AUTO_INCREMENT voor MySQL
                      username VARCHAR(50) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      role VARCHAR(50) NOT NULL
);

-- Tabel voor boekhoudtransacties
CREATE TABLE accounting_entry (
                                  entry_id BIGINT AUTO_INCREMENT PRIMARY KEY,      -- AUTO_INCREMENT voor MySQL
                                  invoice_id INT NOT NULL,
                                  entry_date DATE NOT NULL,
                                  debit_amount DECIMAL(18, 2),
                                  credit_amount DECIMAL(18, 2),
                                  descriptions TEXT,
                                  CONSTRAINT FK_AccountingEntryInvoice FOREIGN KEY (invoice_id) REFERENCES invoice(invoice_id)
);