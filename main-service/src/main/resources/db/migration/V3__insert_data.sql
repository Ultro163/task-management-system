-- Вставка пользователей с UUID
INSERT INTO users (id, name, email, password, role)
VALUES
    (gen_random_uuid(), 'Admin', 'admin@admin.ru', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_ADMIN'),
    (gen_random_uuid(), 'User 1', 'user1@example.com', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_USER'),
    (gen_random_uuid(), 'User 2', 'user2@example.com', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_USER'),
    (gen_random_uuid(), 'User 3', 'user3@example.com', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_USER'),
    (gen_random_uuid(), 'User 4', 'user4@example.com', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_USER'),
    (gen_random_uuid(), 'User 5', 'user5@example.com', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_USER'),
    (gen_random_uuid(), 'User 6', 'user6@example.com', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_USER');

-- Вставка задач с UUID и связями через пользователей
INSERT INTO tasks (id, title, description, state, priority, author_id, executor_id, created_at)
VALUES
    (gen_random_uuid(), 'Задача 1', 'Описание задачи 1', 'IN_PROGRESS', 'HIGH', (SELECT id FROM users OFFSET 0 LIMIT 1), (SELECT id FROM users OFFSET 1 LIMIT 1), '2024-02-01 10:15:30'),
    (gen_random_uuid(), 'Задача 2', 'Описание задачи 2', 'COMPLETED', 'MEDIUM', (SELECT id FROM users OFFSET 2 LIMIT 1), (SELECT id FROM users OFFSET 0 LIMIT 1), '2024-01-30 08:45:00'),
    (gen_random_uuid(), 'Задача 3', 'Описание задачи 3', 'PENDING', 'LOW', (SELECT id FROM users OFFSET 1 LIMIT 1), (SELECT id FROM users OFFSET 3 LIMIT 1), '2024-02-02 14:20:15'),
    (gen_random_uuid(), 'Задача 4', 'Описание задачи 4', 'IN_PROGRESS', 'HIGH', (SELECT id FROM users OFFSET 0 LIMIT 1), (SELECT id FROM users OFFSET 4 LIMIT 1), '2024-01-28 12:05:50'),
    (gen_random_uuid(), 'Задача 5', 'Описание задачи 5', 'COMPLETED', 'MEDIUM', (SELECT id FROM users OFFSET 3 LIMIT 1), (SELECT id FROM users OFFSET 2 LIMIT 1), '2024-02-03 09:30:25'),
    (gen_random_uuid(), 'Задача 6', 'Описание задачи 6', 'PENDING', 'LOW', (SELECT id FROM users OFFSET 4 LIMIT 1), (SELECT id FROM users OFFSET 1 LIMIT 1), '2024-01-27 16:40:10'),
    (gen_random_uuid(), 'Задача 7', 'Описание задачи 7', 'IN_PROGRESS', 'HIGH', (SELECT id FROM users OFFSET 1 LIMIT 1), (SELECT id FROM users OFFSET 0 LIMIT 1), '2024-02-01 11:55:45'),
    (gen_random_uuid(), 'Задача 8', 'Описание задачи 8', 'COMPLETED', 'MEDIUM', (SELECT id FROM users OFFSET 0 LIMIT 1), (SELECT id FROM users OFFSET 3 LIMIT 1), '2024-01-29 07:25:30'),
    (gen_random_uuid(), 'Задача 9', 'Описание задачи 9', 'PENDING', 'LOW', (SELECT id FROM users OFFSET 2 LIMIT 1), (SELECT id FROM users OFFSET 4 LIMIT 1), '2024-02-02 17:10:20'),
    (gen_random_uuid(), 'Задача 10', 'Описание задачи 10', 'IN_PROGRESS', 'HIGH', (SELECT id FROM users OFFSET 3 LIMIT 1), (SELECT id FROM users OFFSET 1 LIMIT 1), '2024-01-31 14:35:00'),
    (gen_random_uuid(), 'Задача 11', 'Описание задачи 11', 'COMPLETED', 'MEDIUM', (SELECT id FROM users OFFSET 4 LIMIT 1), (SELECT id FROM users OFFSET 0 LIMIT 1), '2024-02-03 13:20:50'),
    (gen_random_uuid(), 'Задача 12', 'Описание задачи 12', 'PENDING', 'LOW', (SELECT id FROM users OFFSET 0 LIMIT 1), (SELECT id FROM users OFFSET 2 LIMIT 1), '2024-01-26 18:15:40'),
    (gen_random_uuid(), 'Задача 13', 'Описание задачи 13', 'IN_PROGRESS', 'HIGH', (SELECT id FROM users OFFSET 1 LIMIT 1), (SELECT id FROM users OFFSET 3 LIMIT 1), '2024-02-02 10:05:25'),
    (gen_random_uuid(), 'Задача 14', 'Описание задачи 14', 'COMPLETED', 'HIGH', (SELECT id FROM users OFFSET 2 LIMIT 1), (SELECT id FROM users OFFSET 4 LIMIT 1), '2024-01-29 15:50:35'),
    (gen_random_uuid(), 'Задача 15', 'Описание задачи 15', 'PENDING', 'LOW', (SELECT id FROM users OFFSET 3 LIMIT 1), (SELECT id FROM users OFFSET 1 LIMIT 1), '2024-02-01 09:45:10'),
    (gen_random_uuid(), 'Задача 16', 'Описание задачи 16', 'IN_PROGRESS', 'HIGH', (SELECT id FROM users OFFSET 4 LIMIT 1), (SELECT id FROM users OFFSET 0 LIMIT 1), '2024-01-30 11:25:20'),
    (gen_random_uuid(), 'Задача 17', 'Описание задачи 17', 'COMPLETED', 'MEDIUM', (SELECT id FROM users OFFSET 0 LIMIT 1), (SELECT id FROM users OFFSET 2 LIMIT 1), '2024-02-03 16:30:15'),
    (gen_random_uuid(), 'Задача 18', 'Описание задачи 18', 'PENDING', 'LOW', (SELECT id FROM users OFFSET 1 LIMIT 1), (SELECT id FROM users OFFSET 3 LIMIT 1), '2024-01-27 20:05:45'),
    (gen_random_uuid(), 'Задача 19', 'Описание задачи 19', 'IN_PROGRESS', 'HIGH', (SELECT id FROM users OFFSET 2 LIMIT 1), (SELECT id FROM users OFFSET 4 LIMIT 1), '2024-02-01 13:55:30'),
    (gen_random_uuid(), 'Задача 20', 'Описание задачи 20', 'COMPLETED', 'MEDIUM', (SELECT id FROM users OFFSET 3 LIMIT 1), (SELECT id FROM users OFFSET 0 LIMIT 1), '2024-01-28 08:30:00');

-- Вставка комментариев
DO $$
    DECLARE
        task_id UUID;
        comment_count INT;
    BEGIN
        FOR task_id IN (SELECT id FROM tasks) LOOP
                FOR comment_count IN 1..3 LOOP
                        INSERT INTO comments (id, description, author_id, task_id)
                        VALUES (
                                   gen_random_uuid(),
                                   CONCAT('Комментарий ', comment_count, ' к задаче ', task_id),
                                   (SELECT id FROM users ORDER BY RANDOM() LIMIT 1),
                                   task_id
                               );
                    END LOOP;
            END LOOP;
    END $$;
