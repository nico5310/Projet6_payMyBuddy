INSERT INTO user(first_name, last_name, email, password, balance)
VALUES
/* code Nicolas: 1234 */
('Nicolas','Biancucci','nico@gmail.com','$2a$10$AcVsgu8Y3GZa2yvuAMl.uuD9yAiHpzQ9nX3hy1mSAjeYrTYzK5Y7.' ,1000.0),
/* code James: 007 */
('James', 'Bond', 'james@007.com', '$2a$10$K2dvPQzV66h4G6JGLgI9a.7TuXsUj1OqXsfTEZnxtS9vLZrz3DNYG', 1000.0),
/* code Anthony: 1970 */
('Tony', 'Stark', 'ironman@marvel.com','$2a$10$zjssHnw1qbbC1U5JUlpYtO8GeQNRvQiE2cBMKJp7uXUmEQt4INlci', 10000000.0 );

INSERT INTO account (iban, balance,  user_id)
VALUES
('FR4401234567890123456780000', 10000.0, 1),
('FR4401234567890123456780002', 10000.0, 2),
('FR4401234567890123456780004', 10000000, 3);

INSERT INTO user_contacts (user_contact_id, user_id)
VALUES
(2,1),
(3,1),
(2,3);

INSERT INTO transaction (date, amount, description, sender_user_id, recipient_user_id)
VALUES
('2021-06-10', 300.0, 'Aston Martin', 1, 2),
('2021-06-12', 500.0, 'Armure IronMan', 1,3);

