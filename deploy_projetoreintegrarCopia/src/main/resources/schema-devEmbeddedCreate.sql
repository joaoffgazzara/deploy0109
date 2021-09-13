INSERT INTO usuario(id, nome, genero, email, telefone, senha,
pretensao_salarial, foto, tipo)
VALUES (1, 'Jose Santos', 'masculino', 'email@email.com', '(11) 99999-9999', '12345678',
2000.00, 'http://imgur.com/imagem', 'ROLE_USER');

INSERT INTO usuario(id, nome, genero, email, telefone, senha,
pretensao_salarial, foto, tipo)
VALUES (2, 'Maria', 'feminino', 'email2@email.com', '(11) 99999-9999', '12345678',
2500.00, 'http://imgur.com/imagem', 'ROLE_USER');

INSERT INTO tema(id, nome) VALUES(1, 'apresentacao');
INSERT INTO tema(id, nome) VALUES(2, 'tecnologia');

INSERT INTO postagem(id, titulo, corpo, usuario_id, tema_id)
VALUES (1, 'Olá mundo', 'fala galera, como vai?', 1, 1);

INSERT INTO postagem(id, titulo, corpo, usuario_id, tema_id)
VALUES (2, 'Java', 'gente, como faz para uma classe herdar da outra?', 2, 2);