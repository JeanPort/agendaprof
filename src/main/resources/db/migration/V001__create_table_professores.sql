
CREATE TABLE `professores` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `idade` INT NOT NULL,
    `descricao` TEXT NOT NULL,
    `valor_hora` DECIMAL(5,2) NOT NULL,
    `foto_perfil` VARCHAR(255),
    `password` VARCHAR(255) NOT NULL,
    `created_at`DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL
);
