-- Inserção de dados na tabela tutores
INSERT INTO tutores (nome, documento, tipo_documento) VALUES ('João Silva', '123456789', 'CPF');
INSERT INTO tutores (nome, documento, tipo_documento) VALUES ('Maria Oliveira', '987654321', 'CPF');
INSERT INTO tutores (nome, documento, tipo_documento) VALUES ('Carlos Souza', '456789123', 'CPF');
INSERT INTO tutores (nome, documento, tipo_documento) VALUES ('Ana Costa', '789123456', 'CPF');
INSERT INTO tutores (nome, documento, tipo_documento) VALUES ('Paula Lima', '321654987', 'CPF');


-- Inserção de dados na tabela pets
--INSERT INTO pets (id, nome, tipo, adotado, tutor_id, adocTutorEntitao_id) VALUES (1, 'Luna', 'COELHO', false, NULL, NULL);
--INSERT INTO pets (id, nome, tipo, adotado, tutor_id, adocao_id) VALUES (2, 'Bella', 'GATO', false, NULL, NULL);
--INSERT INTO pets (id, nome, tipo, adotado, tutor_id, adocao_id) VALUES (3, 'Rex', 'CACHORRO', false, NULL, NULL);
--INSERT INTO pets (id, nome, tipo, adotado, tutor_id, adocao_id) VALUES (4, 'Max', 'PEIXE', false, NULL, NULL);
--INSERT INTO pets (id, nome, tipo, adotado, tutor_id, adocao_id) VALUES (5, 'Charlie', 'HAMSTER', false, NULL, NULL);

INSERT INTO pets (nome, tipo, adotado) VALUES ('Luna', 'COELHO', false);
INSERT INTO pets (nome, tipo, adotado) VALUES ('Bella', 'GATO', false);
INSERT INTO pets (nome, tipo, adotado) VALUES ('Rex', 'CACHORRO', false);
INSERT INTO pets (nome, tipo, adotado) VALUES ('Max', 'PEIXE', false);
INSERT INTO pets (nome, tipo, adotado) VALUES ('Charlie', 'HAMSTER', false);

