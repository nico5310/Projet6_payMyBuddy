drop database if exists paymybuddy;

create database paymybuddy;
use
    paymybuddy;
CREATE TABLE user
(
    id        INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(40)        NOT NULL,
    last_name  VARCHAR(40)        NOT NULL,
    email     VARCHAR(50)        NOT NULL,
    password  VARCHAR(200)        NOT NULL,
    balance   DECIMAL(10, 2)     NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;

CREATE TABLE account
(
    id      INT AUTO_INCREMENT NOT NULL,
    iban    VARCHAR(34)        NOT NULL,
    user_id INT                NOT NULL,
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
    type_operation VARCHAR(24)        NOT NULL,
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
    fee               DECIMAL(10, 2)     NOT NULL,
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
            on UPDATE no action ;

ALTER TABLE user_contacts
    ADD CONSTRAINT user_user_contacts_fk1
        FOREIGN KEY (user_contact_id)
            references user (id)
            on DELETE no action
            on UPDATE no action ;

ALTER TABLE account
    ADD CONSTRAINT user_account_fk
        FOREIGN KEY (user_id)
            references user (id)
            on DELETE no action
            on UPDATE no action ;

ALTER TABLE transaction
    ADD CONSTRAINT user_transaction_fk
        FOREIGN KEY (sender_user_id)
            references user (id)
            on DELETE no action
            on UPDATE no action ;

ALTER TABLE transaction
    ADD CONSTRAINT user_transaction_fk2
        FOREIGN KEY (recipient_user_id)
            references user (id)
            on DELETE no action
            on UPDATE no action ;

ALTER TABLE movement
    ADD CONSTRAINT user_movement_fk
        FOREIGN KEY (account_id)
            references user (id)
            on DELETE no action
            on UPDATE no action ;

ALTER TABLE movement
    ADD CONSTRAINT user_transaction_fk1
        FOREIGN KEY (user_id)
            references user (id)
            on DELETE no action
            on UPDATE no action ;
