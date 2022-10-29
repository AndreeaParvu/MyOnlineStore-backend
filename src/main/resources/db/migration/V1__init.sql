CREATE TABLE product
(
   id BIGINT                PRIMARY KEY NOT NULL AUTO_INCREMENT,
   name                     VARCHAR(255) NOT NULL,
   description              VARCHAR(255),
   price                    DECIMAL(10,2) NOT NULL,
   picture_url              VARCHAR(255),
   type                     VARCHAR(255) NOT NULL,
   brand                    VARCHAR(255) NOT NULL,
   quantity_in_stock BIGINT NOT NULL
);