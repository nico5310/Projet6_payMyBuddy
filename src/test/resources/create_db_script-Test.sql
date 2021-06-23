drop database if exists paymybuddytest;
create database paymybuddytest;
use paymybuddytest;

CREATE TABLE user
(
    id         INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(40)        NOT NULL,
    last_name  VARCHAR(40)        NOT NULL,
    email      VARCHAR(100)       NOT NULL UNIQUE,
    password   VARCHAR(200)       NOT NULL,
    balance    DECIMAL(10, 2)     NOT NULL,
    enabled    BOOLEAN DEFAULT 1,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB;

CREATE TABLE account
(
    id      INT AUTO_INCREMENT NOT NULL,
    iban    VARCHAR(34)        NOT NULL UNIQUE,
    user_id INT                NOT NULL,
    balance INT                NOT NULL,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB;

CREATE TABLE user_contacts
(
    id              INT AUTO_INCREMENT NOT NULL,
    user_contact_id INT                NOT NULL,
    user_id         INT                NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;

CREATE TABLE movement
(
    id             INT AUTO_INCREMENT NOT NULL,
    amount         DECIMAL(10, 2)     NOT NULL,
    date           DATETIME           NOT NULL,
    user_id        INT                NOT NULL,
    account_id     INT                NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;

CREATE TABLE transaction
(
    id                INT AUTO_INCREMENT NOT NULL,
    date              DATETIME           NOT NULL,
    amount            DECIMAL(10, 2)     NOT NULL,
    description       VARCHAR(60),
    sender_user_id    INT,
    recipient_user_id INT,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;


ALTER TABLE user_contacts
    ADD CONSTRAINT user_user_contacts_fk
        FOREIGN KEY (user_id)
            references user (id)
            on DELETE no action
            on UPDATE no action;

ALTER TABLE user_contacts
    ADD CONSTRAINT user_user_contacts_fk1
        FOREIGN KEY (user_contact_id)
            references user (id)
            on DELETE no action
            on UPDATE no action;
