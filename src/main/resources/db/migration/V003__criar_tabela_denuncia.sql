CREATE TABLE denuncia (
                          id SERIAL PRIMARY KEY,
                          identificacao VARCHAR(255),
                          nome VARCHAR(255) NOT NULL,
                          cpf VARCHAR(14),
                          telefone VARCHAR(11) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          tipo VARCHAR(255) NOT NULL,
                          hora_ocorrencia TIME,
                          data_ocorrencia DATE NOT NULL,
                          assunto VARCHAR(255),
                          relato TEXT NOT NULL,
                          latitude DOUBLE PRECISION NOT NULL,
                          longitude DOUBLE PRECISION NOT NULL,
                          imagem BYTEA
);