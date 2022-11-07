CREATE TABLE tb_cidade (
    id_cidade bigint NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    qtd_habitantes bigint
);

INSERT INTO tb_cidade (id_cidade, nome, qtd_habitantes)
VALUES
     (1, 'São Paulo', 12396372),
     (2, 'Rio de Janeiro', 10000000),
     (3, 'Fortaleza', 80000000),
     (4, 'Salvador', 70000000),
     (5, 'Belo Horizonte', 6000000),
     (6, 'Porto Alegre', 7770000),
     (7, 'Porto Velho', 4948989),
     (8, 'Palmas', 78787900),
     (9, 'Recife', 23234780),
     (10, 'Natal', 78978979),
     (11, 'Brasilia', 1000000),
     (12, 'Vitoria', null),
     (13, 'Curitiba', null)
;