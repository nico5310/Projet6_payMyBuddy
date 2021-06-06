use
    paymybuddy;

CREATE TABLE user
(
    id        INT AUTO_INCREMENT NOT NULL,
    firstname VARCHAR(40)        NOT NULL,
    lastname  VARCHAR(40)        NOT NULL,
    email     VARCHAR(50)        NOT NULL,
    password  VARCHAR(50)        NOT NULL,
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


