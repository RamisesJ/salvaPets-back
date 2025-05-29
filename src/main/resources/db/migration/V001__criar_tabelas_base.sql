CREATE TABLE contato (
                         id SERIAL PRIMARY KEY,
                         telefone VARCHAR(20),
                         whatsapp VARCHAR(20),
                         endereco VARCHAR(255)

);

CREATE TABLE ong (
                     id SERIAL PRIMARY KEY,
                     nome VARCHAR(100) NOT NULL,
                     cnpj VARCHAR(20) UNIQUE NOT NULL,
                     razao_social VARCHAR(100) NOT NULL,
                     descricao varchar(255),
                     site VARCHAR(100),
                     email VARCHAR(100) UNIQUE NOT NULL,
                     contato_id INTEGER REFERENCES contato(id)
);

CREATE TABLE usuario (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         cpf VARCHAR(100) UNIQUE NOT NULL,
                         status VARCHAR(2),
                         email VARCHAR(100) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         contato_id INTEGER REFERENCES contato(id),
                         perfil VARCHAR(20) NOT NULL CHECK (perfil IN ('ADMIN', 'COMUM'))
);

CREATE TABLE pet (
                     id SERIAL PRIMARY KEY,
                     nome VARCHAR(100) NOT NULL,
                     raca VARCHAR(100),
                     porte VARCHAR(50),
                     idade VARCHAR(50),
                     descricao VARCHAR(255),
                     usuario_id INTEGER REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE foto_pet (
                          id SERIAL PRIMARY KEY,
                          imagem_base64 TEXT NOT NULL,
                          pet_id INTEGER REFERENCES pet(id) ON DELETE CASCADE
);

CREATE TABLE usuario_ong (
                             id SERIAL PRIMARY KEY,
                             usuario_id INTEGER REFERENCES usuario(id) ON DELETE CASCADE,
                             ong_id INTEGER REFERENCES ong(id) ON DELETE CASCADE,
                             funcao VARCHAR(50),
                             UNIQUE (usuario_id, ong_id)
);
