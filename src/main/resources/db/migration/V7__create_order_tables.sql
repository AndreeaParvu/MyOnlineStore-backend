CREATE TABLE user_order (
  id                       BIGINT        PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id                  BIGINT        NOT NULL,
  full_name                VARCHAR(255)  DEFAULT NULL,
  address1                 VARCHAR(255)  DEFAULT NULL,
  address2                 VARCHAR(255)  DEFAULT NULL,
  city                     VARCHAR(255)  DEFAULT NULL,
  zip_code                 VARCHAR(255)  DEFAULT NULL,
  country                  VARCHAR(255)  DEFAULT NULL,
  order_date               DATETIME      DEFAULT NULL,
  subtotal                 DECIMAL(10,2) NOT NULL DEFAULT 0,
  delivery_fee             DECIMAL(10,2) NOT NULL DEFAULT 0,
  FOREIGN KEY (user_id) REFERENCES commerce_user(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE order_item (
  id          BIGINT         PRIMARY KEY NOT NULL AUTO_INCREMENT,
  order_id    BIGINT         NOT NULL,
  product_id  BIGINT         NOT NULL,
  name        VARCHAR(255)   NOT NULL,
  picture_url VARCHAR(255)   DEFAULT NULL,
  price       DECIMAL(10,2)  NOT NULL DEFAULT 0,
  quantity    BIGINT         NOT NULL DEFAULT 0,
  FOREIGN KEY (order_id) REFERENCES user_order(id) ON DELETE CASCADE ON UPDATE CASCADE
);