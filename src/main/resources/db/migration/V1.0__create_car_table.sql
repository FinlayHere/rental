CREATE TABLE IF NOT EXISTS `car` (
    `id` VARCHAR(16) NOT NULL,
    `model` VARCHAR(16) NOT NULL,
    `plate_number` VARCHAR(16) NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB;

INSERT INTO `car`(`id`, `model`, `plate_number`)
VALUES
('01', 'Toyota Camry', '123'),
('02', 'Toyota Camry', '124'),
('03', 'BMW 650', '125'),
('04', 'BMW 650', '126');
