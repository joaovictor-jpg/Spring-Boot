CREATE TABLE tb_user (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    lest_name VARCHAR(50) NOT NULL,
    email VARCHAR(55) NOT NULL,
    password VARCHAR(255) NOT NULL,
    birth_data TIMESTAMP NOT NULL
);

CREATE TABLE tasks (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(55) NOT NULL,
    description VARCHAR NOT NULL,
    date TIMESTAMP NOT NULL,
    user_id INTEGER REFERENCES tb_user (id)
);