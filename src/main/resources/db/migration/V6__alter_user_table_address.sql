ALTER TABLE commerce_user
ADD COLUMN full_name VARCHAR(255) DEFAULT NULL,
ADD COLUMN address1 VARCHAR(255) DEFAULT NULL,
ADD COLUMN address2 VARCHAR(255) DEFAULT NULL,
ADD COLUMN city VARCHAR(255) DEFAULT NULL,
ADD COLUMN zip_code VARCHAR(255) DEFAULT NULL,
ADD COLUMN country VARCHAR(255) DEFAULT NULL;