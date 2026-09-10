-- Dados ficticios para testes locais. IDs gerados automaticamente pelo banco.
INSERT INTO tb_escolas (nome, endereco, inep, quantidade_turmas, criado_em) VALUES ('Escola Aurora', 'Rua das Flores, 100', '99000001', 12, TIMESTAMP '2026-09-09 12:00:00');
INSERT INTO tb_escolas (nome, endereco, inep, quantidade_turmas, criado_em) VALUES ('Escola Horizonte', 'Avenida Central, 250', '99000002', 20, TIMESTAMP '2026-09-09 12:00:00');
INSERT INTO tb_escolas (nome, endereco, inep, quantidade_turmas, criado_em) VALUES ('Escola Sementinha', 'Rua dos Jardins, 45', '99000003', 8, TIMESTAMP '2026-09-09 12:00:00');
INSERT INTO tb_escolas (nome, endereco, inep, quantidade_turmas, criado_em) VALUES ('Escola Caminhos', 'Rua da Esperanca, 320', '99000004', 10, TIMESTAMP '2026-09-09 12:00:00');
INSERT INTO tb_escolas (nome, endereco, inep, quantidade_turmas, criado_em) VALUES ('Escola Novo Saber', 'Avenida do Sol, 500', '99000005', 16, TIMESTAMP '2026-09-09 12:00:00');

INSERT INTO tb_segmentos (nome, codigo, tipo) VALUES ('Educacao Infantil', 'EI', 'EDUCACAO_INFANTIL');
INSERT INTO tb_segmentos (nome, codigo, tipo) VALUES ('Anos Iniciais', 'AI', 'ENSINO_FUNDAMENTAL_ANOS_INICIAIS');
INSERT INTO tb_segmentos (nome, codigo, tipo) VALUES ('Anos Finais', 'AF', 'ENSINO_FUNDAMENTAL_ANOS_FINAIS');
INSERT INTO tb_segmentos (nome, codigo, tipo) VALUES ('Ensino Integral', 'INT', 'ENSINO_INTEGRAL');
INSERT INTO tb_segmentos (nome, codigo, tipo) VALUES ('Educacao de Jovens e Adultos', 'EJA', 'ENSINO_EJA');

-- Associacoes referentes aos IDs gerados no banco vazio do perfil dev.
INSERT INTO escola_turnos (escola_id, turnos) VALUES (1, 'MANHA');
INSERT INTO escola_turnos (escola_id, turnos) VALUES (1, 'TARDE');
INSERT INTO escola_turnos (escola_id, turnos) VALUES (2, 'MANHA');
INSERT INTO escola_turnos (escola_id, turnos) VALUES (2, 'TARDE');
INSERT INTO escola_turnos (escola_id, turnos) VALUES (3, 'MANHA');
INSERT INTO escola_turnos (escola_id, turnos) VALUES (4, 'NOITE');
INSERT INTO escola_turnos (escola_id, turnos) VALUES (5, 'INTEGRAL');

INSERT INTO tb_escolas_segmentos (escolas_id, segmentos_id) VALUES (1, 2);
INSERT INTO tb_escolas_segmentos (escolas_id, segmentos_id) VALUES (1, 3);
INSERT INTO tb_escolas_segmentos (escolas_id, segmentos_id) VALUES (2, 1);
INSERT INTO tb_escolas_segmentos (escolas_id, segmentos_id) VALUES (2, 2);
INSERT INTO tb_escolas_segmentos (escolas_id, segmentos_id) VALUES (2, 3);
INSERT INTO tb_escolas_segmentos (escolas_id, segmentos_id) VALUES (3, 1);
INSERT INTO tb_escolas_segmentos (escolas_id, segmentos_id) VALUES (4, 5);
INSERT INTO tb_escolas_segmentos (escolas_id, segmentos_id) VALUES (5, 2);
INSERT INTO tb_escolas_segmentos (escolas_id, segmentos_id) VALUES (5, 4);

-- Disciplinas e professores ficticios para testes locais.
INSERT INTO tb_disciplina (nome, ativo, criado_em) VALUES ('Matematica', TRUE, TIMESTAMP '2026-09-09 12:00:00');
INSERT INTO tb_disciplina (nome, ativo, criado_em) VALUES ('Portugues', TRUE, TIMESTAMP '2026-09-09 12:00:00');
INSERT INTO tb_disciplina (nome, ativo, criado_em) VALUES ('Historia', TRUE, TIMESTAMP '2026-09-09 12:00:00');
INSERT INTO tb_disciplina (nome, ativo, criado_em) VALUES ('Geografia', TRUE, TIMESTAMP '2026-09-09 12:00:00');
INSERT INTO tb_disciplina (nome, ativo, criado_em) VALUES ('Ciencias', TRUE, TIMESTAMP '2026-09-09 12:00:00');

INSERT INTO tb_professor (nome, cpf, matricula, carga_horaria, formacao, pos_graduacao, contato, email, criado_em) VALUES ('Ana Oliveira', '00000000001', 'PROF001', 40, 'Licenciatura em Matematica', 'Educacao Matematica', '00000000001', 'ana.oliveira@example.com', TIMESTAMP '2026-09-09 12:00:00');
INSERT INTO tb_professor (nome, cpf, matricula, carga_horaria, formacao, pos_graduacao, contato, email, criado_em) VALUES ('Bruno Santos', '00000000002', 'PROF002', 30, 'Licenciatura em Letras', 'Literatura Brasileira', '00000000002', 'bruno.santos@example.com', TIMESTAMP '2026-09-09 12:00:00');
INSERT INTO tb_professor (nome, cpf, matricula, carga_horaria, formacao, pos_graduacao, contato, email, criado_em) VALUES ('Carla Souza', '00000000003', 'PROF003', 40, 'Licenciatura em Historia e Geografia', NULL, '00000000003', 'carla.souza@example.com', TIMESTAMP '2026-09-09 12:00:00');
INSERT INTO tb_professor (nome, cpf, matricula, carga_horaria, formacao, pos_graduacao, contato, email, criado_em) VALUES ('Diego Lima', '00000000004', 'PROF004', 20, 'Licenciatura em Ciencias Biologicas', 'Ensino de Ciencias', '00000000004', 'diego.lima@example.com', TIMESTAMP '2026-09-09 12:00:00');
INSERT INTO tb_professor (nome, cpf, matricula, carga_horaria, formacao, pos_graduacao, contato, email, criado_em) VALUES ('Elisa Costa', '00000000005', 'PROF005', 40, 'Licenciatura em Matematica e Ciencias', NULL, '00000000005', 'elisa.costa@example.com', TIMESTAMP '2026-09-09 12:00:00');

-- IDs gerados no banco vazio: professores e disciplinas de 1 a 5.
INSERT INTO tb_professor_disciplina (professor_id, disciplina_id) VALUES (1, 1);
INSERT INTO tb_professor_disciplina (professor_id, disciplina_id) VALUES (2, 2);
INSERT INTO tb_professor_disciplina (professor_id, disciplina_id) VALUES (3, 3);
INSERT INTO tb_professor_disciplina (professor_id, disciplina_id) VALUES (3, 4);
INSERT INTO tb_professor_disciplina (professor_id, disciplina_id) VALUES (4, 5);
INSERT INTO tb_professor_disciplina (professor_id, disciplina_id) VALUES (5, 1);
INSERT INTO tb_professor_disciplina (professor_id, disciplina_id) VALUES (5, 5);
