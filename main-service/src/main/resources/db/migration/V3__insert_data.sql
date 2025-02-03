INSERT INTO users (name, email, password, role)
VALUES ('Admin', 'admin@admin.ru', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_ADMIN'),
       ('User 1', 'user1@example.com', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_USER'),
       ('User 2', 'user2@example.com', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_USER'),
       ('User 3', 'user3@example.com', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_USER'),
       ('User 4', 'user4@example.com', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_USER'),
       ('User 5', 'user5@example.com', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_USER'),
       ('User 6', 'user6@example.com', '$2a$05$8vDFAY5LQ3KZdLW5dDlnLOzuMSSWG.iVEk29PXziAErTfN0fNnjey', 'ROLE_USER');


INSERT INTO tasks (title, description, state, priority, author_id, executor_id, created_at)
VALUES
    ('Задача 1', 'Описание задачи 1', 'IN_PROGRESS', 'HIGH', 1, 2, '2024-02-01 10:15:30'),
    ('Задача 2', 'Описание задачи 2', 'COMPLETED', 'MEDIUM', 3, 1, '2024-01-30 08:45:00'),
    ('Задача 3', 'Описание задачи 3', 'PENDING', 'LOW', 2, 4, '2024-02-02 14:20:15'),
    ('Задача 4', 'Описание задачи 4', 'IN_PROGRESS', 'HIGH', 1, 5, '2024-01-28 12:05:50'),
    ('Задача 5', 'Описание задачи 5', 'COMPLETED', 'MEDIUM', 4, 3, '2024-02-03 09:30:25'),
    ('Задача 6', 'Описание задачи 6', 'PENDING', 'LOW', 5, 2, '2024-01-27 16:40:10'),
    ('Задача 7', 'Описание задачи 7', 'IN_PROGRESS', 'HIGH', 2, 1, '2024-02-01 11:55:45'),
    ('Задача 8', 'Описание задачи 8', 'COMPLETED', 'MEDIUM', 1, 4, '2024-01-29 07:25:30'),
    ('Задача 9', 'Описание задачи 9', 'PENDING', 'LOW', 3, 5, '2024-02-02 17:10:20'),
    ('Задача 10', 'Описание задачи 10', 'IN_PROGRESS', 'HIGH', 4, 2, '2024-01-31 14:35:00'),
    ('Задача 11', 'Описание задачи 11', 'COMPLETED', 'MEDIUM', 5, 1, '2024-02-03 13:20:50'),
    ('Задача 12', 'Описание задачи 12', 'PENDING', 'LOW', 1, 3, '2024-01-26 18:15:40'),
    ('Задача 13', 'Описание задачи 13', 'IN_PROGRESS', 'HIGH', 2, 4, '2024-02-02 10:05:25'),
    ('Задача 14', 'Описание задачи 14', 'COMPLETED', 'HIGH', 3, 5, '2024-01-29 15:50:35'),
    ('Задача 15', 'Описание задачи 15', 'PENDING', 'LOW', 4, 2, '2024-02-01 09:45:10'),
    ('Задача 16', 'Описание задачи 16', 'IN_PROGRESS', 'HIGH', 5, 1, '2024-01-30 11:25:20'),
    ('Задача 17', 'Описание задачи 17', 'COMPLETED', 'MEDIUM', 1, 3, '2024-02-03 16:30:15'),
    ('Задача 18', 'Описание задачи 18', 'PENDING', 'LOW', 2, 4, '2024-01-27 20:05:45'),
    ('Задача 19', 'Описание задачи 19', 'IN_PROGRESS', 'HIGH', 3, 5, '2024-02-01 13:55:30'),
    ('Задача 20', 'Описание задачи 20', 'COMPLETED', 'MEDIUM', 4, 1, '2024-01-28 08:30:00');

DO $$
    DECLARE
        task_id BIGINT;
        comment_count INT;
    BEGIN
        FOR task_id IN SELECT id FROM tasks LOOP
                FOR comment_count IN 1..3 LOOP
                        INSERT INTO comments (description, author_id, task_id)
                        VALUES (
                                   CONCAT('Комментарий ', comment_count, ' к задаче ', task_id),
                                   FLOOR(RANDOM() * 5) + 1,
                                   task_id
                               );
                    END LOOP;
            END LOOP;
    END $$;