-- -- Drop table if it exists
-- DROP TABLE IF EXISTS accounts;

-- -- Create table with manual ID control
-- CREATE TABLE accounts (
--     id BIGINT PRIMARY KEY,
--     account_holder_name VARCHAR(255) NOT NULL,
--     balance DOUBLE NOT NULL
-- );

-- -- Create sequence table
-- DROP TABLE IF EXISTS account_sequence;
-- CREATE TABLE account_sequence (
--     next_id BIGINT NOT NULL
-- );

-- -- Initialize sequence with 100001
-- INSERT INTO account_sequence (next_id) VALUES (100001);

-- -- Procedure to get next ID
-- DELIMITER //

-- CREATE PROCEDURE GetNextAccountID(OUT new_id BIGINT)
-- BEGIN
--     -- Get next ID from sequence
--     SELECT next_id INTO new_id FROM account_sequence;
    
--     -- Increment for next use
--     UPDATE account_sequence SET next_id = next_id + 1;
-- END //

-- DELIMITER ;
--
-- -- Insert a new account
-- CALL GetNextAccountID(@nextID);
-- INSERT INTO accounts (id, account_holder_name, balance) VALUES (@nextID, 'jaya', 5000.00);
ALTER TABLE accounts AUTO_INCREMENT = (SELECT COALESCE(MAX(id), 100000) + 1 FROM accounts);