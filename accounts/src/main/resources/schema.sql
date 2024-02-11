CREATE TABLE IF NOT EXISTS `customer` (
    `costumer_id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `mobile_number` VARCHAR(10) NOT NULL,
    `email` VARCHAR(150) NOT NULL,
    `created_at` DATE NOT NULL,
    `created_by` VARCHAR(40) NOT NULL,
    `update_at` DATE DEFAULT NULL,
    `update_by` VARCHAR(40) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `accounts` (
    `customer_id` INT NOT NULL,
    `account_number` INT AUTO_INCREMENT PRIMARY KEY,
    `account_type` VARCHAR(70) NOT NULL,
    `branch_address` VARCHAR(200) NOT NULL,
    `created_at` DATE NOT NULL,
    `created_by` VARCHAR(40) NOT NULL,
    `update_at` DATE DEFAULT NULL,
    `update_by` VARCHAR(40) DEFAULT NULL
);
