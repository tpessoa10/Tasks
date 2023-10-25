CREATE TABLE tasks (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(255),
  descricao VARCHAR(255),
  concluido TINYINT(1),
  data DATETIME
);