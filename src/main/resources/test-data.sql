INSERT INTO Customers (username, password, email)
VALUES ('JimmyJab', 'JimmyJabGames', 'JimmyJab@gmail.com');

INSERT INTO Todo (fk_cid) 
VALUES (1);

INSERT INTO Tasks (fk_todoID, task)
VALUES (1, 'Get Chocolate');