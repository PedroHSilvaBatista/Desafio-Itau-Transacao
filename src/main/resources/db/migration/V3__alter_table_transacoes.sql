ALTER TABLE transacoes
    ADD COLUMN usuario_id BIGINT NOT NULL,
    ADD CONSTRAINT fk_transacoes_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id);