CREATE TABLE IF NOT EXISTS `car` (
    `id` VARCHAR(16) NOT NULL,
    `model` VARCHAR(16) NOT NULL,
    `plate_number` VARCHAR(16) NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB;

INSERT INTO `car`(`id`, `model`, `plate_number`)
VALUES
('01', 'Toyota Camry', '123'),
('01', 'Toyota Camry', '124'),
('02', 'BMW 650', '125'),
('02', 'BMW 650', '126');
