-- Employees
INSERT INTO employee (id, employee_code, full_name, email, is_active, created_at)
VALUES
    (1, 'EMP001', 'Ivan Petrov',   'ivan.petrov@factory.local',   TRUE, CURRENT_TIMESTAMP),
    (2, 'EMP002', 'Anna Smirnova', 'anna.smirnova@factory.local', TRUE, CURRENT_TIMESTAMP),
    (3, 'EMP003', 'Marko Tamm',    'marko.tamm@factory.local',    TRUE, CURRENT_TIMESTAMP);

-- Workstations (WS1..WS5)
INSERT INTO workstation (id, code, name, area, is_active, created_at)
VALUES
    (1, 'WS1', 'Cutting',        'Production', TRUE, CURRENT_TIMESTAMP),
    (2, 'WS2', 'Stripping',      'Production', TRUE, CURRENT_TIMESTAMP),
    (3, 'WS3', 'Crimping',       'Production', TRUE, CURRENT_TIMESTAMP),
    (4, 'WS4', 'Assembly',       'Production', TRUE, CURRENT_TIMESTAMP),
    (5, 'WS5', 'Quality Check',  'Quality',    TRUE, CURRENT_TIMESTAMP);

-- Tasks
INSERT INTO task (id, task_code, title, description, status, priority, due_date, employee_id, workstation_id, created_at, updated_at)
VALUES
    (1, 'TASK-0001',
     'Prepare BOM for new cable harness',
     'Create BOM and verify lengths/strip parameters according to drawing revision.',
     'TODO', 'HIGH', DATE '2026-01-10', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    (2, 'TASK-0002',
     'Crimp validation for batch #A9069xx',
     'Run crimp pull-force test and record results. Attach photos to report.',
     'IN_PROGRESS', 'HIGH', DATE '2026-01-08', 2, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    (3, 'TASK-0003',
     'Final QA inspection - 10 pcs',
     'Perform final inspection by THR checklist, confirm marking positions.',
     'TODO', 'MEDIUM', DATE '2026-01-12', 3, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    (4, 'TASK-0004',
     'Update assembly instruction',
     'Update assembly instruction: half strip requirement and label placement.',
     'DONE', 'LOW', DATE '2026-01-05', 1, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    (5, 'TASK-0005',
     'Prepare workstation setup for new order',
     'Check tools, labels and materials availability for upcoming production order.',
     'TODO', 'MEDIUM', DATE '2026-01-09', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Task logs
INSERT INTO task_log (id, task_id, event_type, old_status, new_status, message, created_by_employee_id, created_at)
VALUES
    (1, 1, 'CREATED', NULL, 'TODO', 'Task created from production request.', 1, CURRENT_TIMESTAMP),

    (2, 2, 'CREATED', NULL, 'TODO', 'Task created for validation batch.', 2, CURRENT_TIMESTAMP),
    (3, 2, 'STATUS_CHANGE', 'TODO', 'IN_PROGRESS', 'Started pull-force tests on first samples.', 2, CURRENT_TIMESTAMP),

    (4, 4, 'CREATED', NULL, 'TODO', 'Instruction update requested.', 1, CURRENT_TIMESTAMP),
    (5, 4, 'STATUS_CHANGE', 'TODO', 'DONE', 'Instruction updated and approved.', 1, CURRENT_TIMESTAMP),

    (6, 3, 'CREATED', NULL, 'TODO', 'QA inspection task created for final batch.', 3, CURRENT_TIMESTAMP),

    (7, 5, 'CREATED', NULL, 'TODO', 'Workstation preparation task created.', 2, CURRENT_TIMESTAMP),
    (8, 5, 'COMMENT', NULL, NULL, 'Waiting for materials confirmation from warehouse.', 2, CURRENT_TIMESTAMP);