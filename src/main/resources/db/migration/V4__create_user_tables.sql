CREATE TABLE role
(
   id              BIGINT                PRIMARY KEY NOT NULL AUTO_INCREMENT,
   commerce_role   VARCHAR(255)          NOT NULL
);

CREATE TABLE commerce_user
(
   id              BIGINT                PRIMARY KEY NOT NULL AUTO_INCREMENT,
   email           VARCHAR(255)          NOT NULL UNIQUE,
   password        VARCHAR(255)          NOT NULL
);

CREATE TABLE user_roles
(
   user_id         BIGINT                NOT NULL,
   role_id         BIGINT                NOT NULL,
   PRIMARY KEY (user_id, role_id),
   FOREIGN KEY (user_id) REFERENCES commerce_user(id) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE ON UPDATE CASCADE
);