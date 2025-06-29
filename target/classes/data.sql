-- 1) Máquina de estado
INSERT INTO state_machine(id, name, is_active)
VALUES (RANDOM_UUID(), 'OrderLifecycle', TRUE);

-- 2) Status
INSERT INTO state_machine_status(id, state_machine_id, name, is_initial, is_active)
VALUES
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Criado',     TRUE,  TRUE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Confirmado', FALSE, TRUE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Em Preparo', FALSE, TRUE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Enviado',     FALSE, TRUE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Entregue',    FALSE, TRUE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Finalizado',  FALSE, TRUE),
  (RANDOM_UUID(), (SELECT id FROM state_machine WHERE name='OrderLifecycle'), 'Cancelado',   FALSE, TRUE);

-- 3) Transições normais
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id, is_active)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id, TRUE
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Criado'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Confirmado'
WHERE sm.name='OrderLifecycle';

INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id, is_active)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id, TRUE
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Confirmado'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Em Preparo'
WHERE sm.name='OrderLifecycle';

INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id, is_active)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id, TRUE
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Em Preparo'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Enviado'
WHERE sm.name='OrderLifecycle';

INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id, is_active)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id, TRUE
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Enviado'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Entregue'
WHERE sm.name='OrderLifecycle';

INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id, is_active)
SELECT RANDOM_UUID(), sm.id, s1.id, s2.id, TRUE
FROM state_machine sm
JOIN state_machine_status s1 ON s1.state_machine_id=sm.id AND s1.name='Entregue'
JOIN state_machine_status s2 ON s2.state_machine_id=sm.id AND s2.name='Finalizado'
WHERE sm.name='OrderLifecycle';

-- 4) Transições de cancelamento (antes de "Enviado")
INSERT INTO state_machine_transition(id, state_machine_id, source_status_id, target_status_id, is_active)
SELECT RANDOM_UUID(), sm.id, s.id, c.id, TRUE
FROM state_machine sm
JOIN state_machine_status s ON s.state_machine_id=sm.id
  AND s.name IN ('Criado','Confirmado','Em Preparo')
JOIN state_machine_status c ON c.state_machine_id=sm.id AND c.name='Cancelado'
WHERE sm.name='OrderLifecycle';
