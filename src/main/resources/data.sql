--=====================================================================
-- Ciclo de vida de Pedido
--=====================================================================
-- 1) Máquina de estado
INSERT INTO state_machine(id, name, description, is_active)
VALUES (RANDOM_UUID(), 'OrderLifecycle', 'Ciclo de vida de Pedido', TRUE);

-- 2) Status
INSERT INTO state_machine_status(id, state_machine_id, name, is_initial)
VALUES
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Criado',      TRUE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Confirmado',  FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Em Preparo',  FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Enviado',     FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Entregue',    FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Finalizado',  FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Cancelado',   FALSE);

-- 3) Transições normais
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Criado'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Confirmado'
WHERE sm.name='OrderLifecycle';

INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Confirmado'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Em Preparo'
WHERE sm.name='OrderLifecycle';

INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Em Preparo'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Enviado'
WHERE sm.name='OrderLifecycle';

INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Enviado'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Entregue'
WHERE sm.name='OrderLifecycle';

INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Entregue'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Finalizado'
WHERE sm.name='OrderLifecycle';

-- 4) Transições de cancelamento (antes de "Enviado")
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s.id, c.id
FROM state_machine sm
JOIN state_machine_status s ON s.state_machine_id=sm.id
  AND s.name IN ('Criado','Confirmado','Em Preparo')
JOIN state_machine_status c ON c.state_machine_id=sm.id AND c.name='Cancelado'
WHERE sm.name='OrderLifecycle';

--=====================================================================
-- Máquina de Jogo
--=====================================================================
-- 1) Máquina de Jogo
INSERT INTO state_machine(id, name, description, is_active)
VALUES (
  RANDOM_UUID(),
  'GameLifecycle',
  'Máquina de Jogo',
  TRUE
);

-- 2) Status
INSERT INTO state_machine_status(id, state_machine_id, name, is_initial)
VALUES
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'GameLifecycle'),
    'Menu',
    TRUE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'GameLifecycle'),
    'Jogando',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'GameLifecycle'),
    'Pausado',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'GameLifecycle'),
    'Game Over',
    FALSE
  );

-- 3) Transições de fluxo de jogo

-- Iniciar Jogo: Menu → Jogando
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s_menu.id,
  s_jogando.id
FROM state_machine sm
JOIN state_machine_status s_menu
  ON s_menu.state_machine_id = sm.id AND s_menu.name = 'Menu'
JOIN state_machine_status s_jogando
  ON s_jogando.state_machine_id = sm.id AND s_jogando.name = 'Jogando'
WHERE sm.name = 'GameLifecycle';

-- Pausar: Jogando → Pausado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s_jogando.id,
  s_pausado.id
FROM state_machine sm
JOIN state_machine_status s_jogando
  ON s_jogando.state_machine_id = sm.id AND s_jogando.name = 'Jogando'
JOIN state_machine_status s_pausado
  ON s_pausado.state_machine_id = sm.id AND s_pausado.name = 'Pausado'
WHERE sm.name = 'GameLifecycle';

-- Retomar (mesmo evento "Iniciar Jogo"): Pausado → Jogando
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s_pausado.id,
  s_jogando.id
FROM state_machine sm
JOIN state_machine_status s_pausado
  ON s_pausado.state_machine_id = sm.id AND s_pausado.name = 'Pausado'
JOIN state_machine_status s_jogando
  ON s_jogando.state_machine_id = sm.id AND s_jogando.name = 'Jogando'
WHERE sm.name = 'GameLifecycle';

-- Morrer: Jogando → Game Over
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s_jogando.id,
  s_gameover.id
FROM state_machine sm
JOIN state_machine_status s_jogando
  ON s_jogando.state_machine_id = sm.id AND s_jogando.name = 'Jogando'
JOIN state_machine_status s_gameover
  ON s_gameover.state_machine_id = sm.id AND s_gameover.name = 'Game Over'
WHERE sm.name = 'GameLifecycle';

-- Reiniciar: Game Over → Menu
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s_gameover.id,
  s_menu.id
FROM state_machine sm
JOIN state_machine_status s_gameover
  ON s_gameover.state_machine_id = sm.id AND s_gameover.name = 'Game Over'
JOIN state_machine_status s_menu
  ON s_menu.state_machine_id = sm.id AND s_menu.name = 'Menu'
WHERE sm.name = 'GameLifecycle';

--=====================================================================
-- Processamento de Faturas/Boletos
--=====================================================================
-- 1) Máquina de estado de Faturas/Boletos
INSERT INTO state_machine(id, name, description, is_active)
VALUES (
  RANDOM_UUID(),
  'InvoiceLifecycle',
  'Processamento de Faturas/Boletos',
  TRUE
);

-- 2) Status
INSERT INTO state_machine_status(id, state_machine_id, name, is_initial)
VALUES
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'InvoiceLifecycle'),
    'Criado',
    TRUE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'InvoiceLifecycle'),
    'Enviado',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'InvoiceLifecycle'),
    'Pago',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'InvoiceLifecycle'),
    'Confirmado',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'InvoiceLifecycle'),
    'Cancelado',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'InvoiceLifecycle'),
    'Reembolsado',
    FALSE
  );

-- 3) Transições principais

-- Criado → Enviado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s1.id,
  s2.id
FROM state_machine sm
JOIN state_machine_status s1
  ON s1.state_machine_id = sm.id AND s1.name = 'Criado'
JOIN state_machine_status s2
  ON s2.state_machine_id = sm.id AND s2.name = 'Enviado'
WHERE sm.name = 'InvoiceLifecycle';

-- Enviado → Pago
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s1.id,
  s2.id
FROM state_machine sm
JOIN state_machine_status s1
  ON s1.state_machine_id = sm.id AND s1.name = 'Enviado'
JOIN state_machine_status s2
  ON s2.state_machine_id = sm.id AND s2.name = 'Pago'
WHERE sm.name = 'InvoiceLifecycle';

-- Pago → Confirmado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s1.id,
  s2.id
FROM state_machine sm
JOIN state_machine_status s1
  ON s1.state_machine_id = sm.id AND s1.name = 'Pago'
JOIN state_machine_status s2
  ON s2.state_machine_id = sm.id AND s2.name = 'Confirmado'
WHERE sm.name = 'InvoiceLifecycle';

-- Confirmado → Cancelado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s1.id,
  s2.id
FROM state_machine sm
JOIN state_machine_status s1
  ON s1.state_machine_id = sm.id AND s1.name = 'Confirmado'
JOIN state_machine_status s2
  ON s2.state_machine_id = sm.id AND s2.name = 'Cancelado'
WHERE sm.name = 'InvoiceLifecycle';

-- Cancelado → Reembolsado
-- (só acontece se já passou pelo estado 'Pago' e foi confirmado antes de cancelar,
-- garantindo a regra de negócio de que não se reembolsa fatura nunca paga)
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s_cancel.id,
  s_refund.id
FROM state_machine sm
JOIN state_machine_status s_cancel
  ON s_cancel.state_machine_id = sm.id AND s_cancel.name = 'Cancelado'
JOIN state_machine_status s_refund
  ON s_refund.state_machine_id = sm.id AND s_refund.name = 'Reembolsado'
WHERE sm.name = 'InvoiceLifecycle';

--=====================================================================
-- Check-in de Voo
--=====================================================================
-- 1) Máquina de Estado de Check-in de Voo
INSERT INTO state_machine(id, name, description, is_active)
VALUES (
  RANDOM_UUID(),
  'FlightCheckin',
  'Check-in de Voo',
  TRUE
);

-- 2) Status
INSERT INTO state_machine_status(id, state_machine_id, name, is_initial)
VALUES
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'FlightCheckin'),
    'Agendado',
    TRUE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'FlightCheckin'),
    'Check-in aberto',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'FlightCheckin'),
    'Check-in feito',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'FlightCheckin'),
    'Embarcado',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'FlightCheckin'),
    'Concluído',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'FlightCheckin'),
    'Perdido',
    FALSE
  );

-- 3) Transições do Fluxo Principal

-- Agendado → Check-in aberto
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s1.id,
  s2.id
FROM state_machine sm
JOIN state_machine_status s1
  ON s1.state_machine_id = sm.id AND s1.name = 'Agendado'
JOIN state_machine_status s2
  ON s2.state_machine_id = sm.id AND s2.name = 'Check-in aberto'
WHERE sm.name = 'FlightCheckin';

-- Check-in aberto → Check-in feito
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s1.id,
  s2.id
FROM state_machine sm
JOIN state_machine_status s1
  ON s1.state_machine_id = sm.id AND s1.name = 'Check-in aberto'
JOIN state_machine_status s2
  ON s2.state_machine_id = sm.id AND s2.name = 'Check-in feito'
WHERE sm.name = 'FlightCheckin';

-- Check-in feito → Embarcado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s1.id,
  s2.id
FROM state_machine sm
JOIN state_machine_status s1
  ON s1.state_machine_id = sm.id AND s1.name = 'Check-in feito'
JOIN state_machine_status s2
  ON s2.state_machine_id = sm.id AND s2.name = 'Embarcado'
WHERE sm.name = 'FlightCheckin';

-- Embarcado → Concluído
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s1.id,
  s2.id
FROM state_machine sm
JOIN state_machine_status s1
  ON s1.state_machine_id = sm.id AND s1.name = 'Embarcado'
JOIN state_machine_status s2
  ON s2.state_machine_id = sm.id AND s2.name = 'Concluído'
WHERE sm.name = 'FlightCheckin';

-- Embarcado → Perdido
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s1.id,
  s2.id
FROM state_machine sm
JOIN state_machine_status s1
  ON s1.state_machine_id = sm.id AND s1.name = 'Embarcado'
JOIN state_machine_status s2
  ON s2.state_machine_id = sm.id AND s2.name = 'Perdido'
WHERE sm.name = 'FlightCheckin';

--=====================================================================
-- Login e Sessão de Usuário
--=====================================================================
-- 1) Máquina de Estado de Login/Sessão
INSERT INTO state_machine(id, name, description, is_active)
VALUES (
  RANDOM_UUID(),
  'UserSession',
  'Login e Sessão de Usuário',
  TRUE
);

-- 2) Status
INSERT INTO state_machine_status(id, state_machine_id, name, is_initial)
VALUES
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'UserSession'),
    'Deslogado',
    TRUE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'UserSession'),
    'Logando',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'UserSession'),
    'Logado',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'UserSession'),
    'Sessão expirada',
    FALSE
  );

-- 3) Transições
-- Deslogado → Logando
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Deslogado'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Logando'
WHERE sm.name='UserSession';

-- Logando → Logado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Logando'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Logado'
WHERE sm.name='UserSession';

-- Logado → Sessão expirada
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Logado'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Sessão expirada'
WHERE sm.name='UserSession';

-- Sessão expirada → Deslogado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Sessão expirada'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Deslogado'
WHERE sm.name='UserSession';

--=====================================================================
-- Workflow de Aprovação de Documento
--=====================================================================
-- 1) Máquina de Estado de Aprovação de Documento
INSERT INTO state_machine(id, name, description, is_active)
VALUES (
  RANDOM_UUID(),
  'DocumentApproval',
  'Workflow de Aprovação de Documento',
  TRUE
);

-- 2) Status
INSERT INTO state_machine_status(id, state_machine_id, name, is_initial)
VALUES
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='DocumentApproval'), 'Rascunho',   TRUE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='DocumentApproval'), 'Em revisão', FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='DocumentApproval'), 'Aprovado',   FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='DocumentApproval'), 'Publicado',  FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='DocumentApproval'), 'Arquivado',  FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='DocumentApproval'), 'Rejeitado',  FALSE);

-- 3) Transições
-- Rascunho → Em revisão
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s_r.id, s_rev.id
FROM state_machine sm
JOIN state_machine_status s_r   ON s_r.state_machine_id=sm.id AND s_r.name='Rascunho'
JOIN state_machine_status s_rev ON s_rev.state_machine_id=sm.id AND s_rev.name='Em revisão'
WHERE sm.name='DocumentApproval';

-- Em revisão → Aprovado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s_rev.id, s_apr.id
FROM state_machine sm
JOIN state_machine_status s_rev ON s_rev.state_machine_id=sm.id AND s_rev.name='Em revisão'
JOIN state_machine_status s_apr ON s_apr.state_machine_id=sm.id AND s_apr.name='Aprovado'
WHERE sm.name='DocumentApproval';

-- Em revisão → Rejeitado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s_rev.id, s_rej.id
FROM state_machine sm
JOIN state_machine_status s_rev ON s_rev.state_machine_id=sm.id AND s_rev.name='Em revisão'
JOIN state_machine_status s_rej ON s_rej.state_machine_id=sm.id AND s_rej.name='Rejeitado'
WHERE sm.name='DocumentApproval';

-- Aprovado → Publicado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s_apr.id, s_pub.id
FROM state_machine sm
JOIN state_machine_status s_apr ON s_apr.state_machine_id=sm.id AND s_apr.name='Aprovado'
JOIN state_machine_status s_pub ON s_pub.state_machine_id=sm.id AND s_pub.name='Publicado'
WHERE sm.name='DocumentApproval';

-- Publicado → Arquivado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s_pub.id, s_arch.id
FROM state_machine sm
JOIN state_machine_status s_pub  ON s_pub.state_machine_id=sm.id AND s_pub.name='Publicado'
JOIN state_machine_status s_arch ON s_arch.state_machine_id=sm.id AND s_arch.name='Arquivado'
WHERE sm.name='DocumentApproval';

--=====================================================================
-- Ordem de Serviço / Manutenção
--=====================================================================
-- 1) Máquina de Estado de Ordem de Serviço
INSERT INTO state_machine(id, name, description, is_active)
VALUES (
  RANDOM_UUID(),
  'ServiceOrder',
  'Ordem de Serviço / Manutenção',
  TRUE
);

-- 2) Status
INSERT INTO state_machine_status(id, state_machine_id, name, is_initial)
VALUES
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='ServiceOrder'), 'Aberta',            TRUE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='ServiceOrder'), 'Em andamento',     FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='ServiceOrder'), 'Aguardando peças', FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='ServiceOrder'), 'Concluída',        FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='ServiceOrder'), 'Cancelada',        FALSE);

-- 3) Transições
-- Aberta → Em andamento
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Aberta'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Em andamento'
WHERE sm.name='ServiceOrder';

-- Em andamento → Aguardando peças
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s2.id, s3.id
FROM state_machine sm
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Em andamento'
JOIN state_machine_status s3 ON s3.state_machine_id=sm.id AND s3.name='Aguardando peças'
WHERE sm.name='ServiceOrder';

-- Em andamento → Concluída
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s2.id, s4.id
FROM state_machine sm
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Em andamento'
JOIN state_machine_status s4 ON s4.state_machine_id=sm.id AND s4.name='Concluída'
WHERE sm.name='ServiceOrder';

-- Aberta, Em andamento, Aguardando peças → Cancelada
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s.id, c.id
FROM state_machine sm
JOIN state_machine_status s ON s.state_machine_id=sm.id
  AND s.name IN ('Aberta','Em andamento','Aguardando peças')
JOIN state_machine_status c ON c.state_machine_id=sm.id AND c.name='Cancelada'
WHERE sm.name='ServiceOrder';

--=====================================================================
-- Máquina de Semáforo
--=====================================================================
-- 1) Máquina de Estado de Semáforo
INSERT INTO state_machine(id, name, description, is_active)
VALUES (
  RANDOM_UUID(),
  'TrafficLight',
  'Máquina de Semáforo',
  TRUE
);

-- 2) Status
INSERT INTO state_machine_status(id, state_machine_id, name, is_initial)
VALUES
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='TrafficLight'), 'Verde',         TRUE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='TrafficLight'), 'Amarelo',        FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='TrafficLight'), 'Vermelho',       FALSE);

-- 3) Transições em Loop
-- Verde → Amarelo
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Verde'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Amarelo'
WHERE sm.name='TrafficLight';

-- Amarelo → Vermelho
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s2.id, s3.id
FROM state_machine sm
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Amarelo'
JOIN state_machine_status s3 ON s3.state_machine_id=sm.id AND s3.name='Vermelho'
WHERE sm.name='TrafficLight';

-- Vermelho → Verde
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s3.id, s1.id
FROM state_machine sm
JOIN state_machine_status s3 ON s3.state_machine_id=sm.id AND s3.name='Vermelho'
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Verde'
WHERE sm.name='TrafficLight';

--=====================================================================
-- Fluxo de Cadastro / Wizard
--=====================================================================
-- 1) Máquina de Estado do Wizard
INSERT INTO state_machine(id, name, description, is_active)
VALUES (
  RANDOM_UUID(),
  'WizardFlow',
  'Fluxo de Cadastro / Wizard',
  TRUE
);

-- 2) Status
INSERT INTO state_machine_status(id, state_machine_id, name, is_initial)
VALUES
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='WizardFlow'), 'Etapa 1',    TRUE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='WizardFlow'), 'Etapa 2',    FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='WizardFlow'), 'Etapa 3',    FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='WizardFlow'), 'Cancelado',  FALSE);

-- 3) Transições
-- Etapa 1 → Etapa 2
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Etapa 1'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Etapa 2'
WHERE sm.name='WizardFlow';

-- Etapa 2 → Etapa 1
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s2.id, s1.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Etapa 1'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Etapa 2'
WHERE sm.name='WizardFlow';

-- Etapa 2 → Etapa 3
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s2.id, s3.id
FROM state_machine sm
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Etapa 2'
JOIN state_machine_status s3 ON s3.state_machine_id=sm.id AND s3.name='Etapa 3'
WHERE sm.name='WizardFlow';

-- Qualquer etapa → Cancelado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s.id, s_cancel.id
FROM state_machine sm
JOIN state_machine_status s        ON s.state_machine_id=sm.id AND s.name IN ('Etapa 1','Etapa 2','Etapa 3')
JOIN state_machine_status s_cancel ON s_cancel.state_machine_id=sm.id AND s_cancel.name='Cancelado'
WHERE sm.name='WizardFlow';

--=====================================================================
-- Chatbot com Fluxo Guiado
--=====================================================================
-- 1) Máquina de Estado do Chatbot
INSERT INTO state_machine(id, name, description, is_active)
VALUES (
  RANDOM_UUID(),
  'GuidedChatbot',
  'Chatbot com Fluxo Guiado',
  TRUE
);

-- 2) Status
INSERT INTO state_machine_status(id, state_machine_id, name, is_initial)
VALUES
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='GuidedChatbot'), 'Saudação',     TRUE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='GuidedChatbot'), 'Pergunta',      FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='GuidedChatbot'), 'Resposta',      FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='GuidedChatbot'), 'Ação tomada',   FALSE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='GuidedChatbot'), 'Encerrado',     FALSE);

-- 3) Transições
-- Saudação → Pergunta
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Saudação'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Pergunta'
WHERE sm.name='GuidedChatbot';

-- Pergunta → Resposta
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s2.id, s3.id
FROM state_machine sm
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Pergunta'
JOIN state_machine_status s3 ON s3.state_machine_id=sm.id AND s3.name='Resposta'
WHERE sm.name='GuidedChatbot';

-- Resposta → Ação tomada
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s3.id, s4.id
FROM state_machine sm
JOIN state_machine_status s3 ON s3.state_machine_id=sm.id AND s3.name='Resposta'
JOIN state_machine_status s4 ON s4.state_machine_id=sm.id AND s4.name='Ação tomada'
WHERE sm.name='GuidedChatbot';

-- Ação tomada → Encerrado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT RANDOM_UUID(), sm.id, s4.id, s5.id
FROM state_machine sm
JOIN state_machine_status s4 ON s4.state_machine_id=sm.id AND s4.name='Ação tomada'
JOIN state_machine_status s5 ON s5.state_machine_id=sm.id AND s5.name='Encerrado'
WHERE sm.name='GuidedChatbot';

--=====================================================================
-- Processo de Preparação de Bolo
--=====================================================================
-- 1) Máquina de Estado de Receita de Bolo
INSERT INTO state_machine(id, name, description, is_active)
VALUES (
  RANDOM_UUID(),
  'CakeRecipe',
  'Processo de Preparação de Bolo',
  TRUE
);

-- 2) Status
INSERT INTO state_machine_status(id, state_machine_id, name, is_initial)
VALUES
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'CakeRecipe'),
    'Ingredientes Separados',
    TRUE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'CakeRecipe'),
    'Massa Misturada',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'CakeRecipe'),
    'Assando',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'CakeRecipe'),
    'Resfriando',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'CakeRecipe'),
    'Decorado',
    FALSE
  ),
  (
    RANDOM_UUID(),
    (SELECT id FROM state_machine WHERE name = 'CakeRecipe'),
    'Pronto',
    FALSE
  );

-- 3) Transições

-- Ingredientes Separados → Massa Misturada
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s_ing.id,
  s_mix.id
FROM state_machine sm
JOIN state_machine_status s_ing
  ON s_ing.state_machine_id = sm.id AND s_ing.name = 'Ingredientes Separados'
JOIN state_machine_status s_mix
  ON s_mix.state_machine_id = sm.id AND s_mix.name = 'Massa Misturada'
WHERE sm.name = 'CakeRecipe';

-- Massa Misturada → Assando
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s_mix.id,
  s_bake.id
FROM state_machine sm
JOIN state_machine_status s_mix
  ON s_mix.state_machine_id = sm.id AND s_mix.name = 'Massa Misturada'
JOIN state_machine_status s_bake
  ON s_bake.state_machine_id = sm.id AND s_bake.name = 'Assando'
WHERE sm.name = 'CakeRecipe';

-- Assando → Resfriando
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s_bake.id,
  s_cool.id
FROM state_machine sm
JOIN state_machine_status s_bake
  ON s_bake.state_machine_id = sm.id AND s_bake.name = 'Assando'
JOIN state_machine_status s_cool
  ON s_cool.state_machine_id = sm.id AND s_cool.name = 'Resfriando'
WHERE sm.name = 'CakeRecipe';

-- Resfriando → Decorado
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s_cool.id,
  s_decor.id
FROM state_machine sm
JOIN state_machine_status s_cool
  ON s_cool.state_machine_id = sm.id AND s_cool.name = 'Resfriando'
JOIN state_machine_status s_decor
  ON s_decor.state_machine_id = sm.id AND s_decor.name = 'Decorado'
WHERE sm.name = 'CakeRecipe';

-- Decorado → Pronto
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id)
SELECT
  RANDOM_UUID(),
  sm.id,
  s_decor.id,
  s_ready.id
FROM state_machine sm
JOIN state_machine_status s_decor
  ON s_decor.state_machine_id = sm.id AND s_decor.name = 'Decorado'
JOIN state_machine_status s_ready
  ON s_ready.state_machine_id = sm.id AND s_ready.name = 'Pronto'
WHERE sm.name = 'CakeRecipe';
