TRUNCATE TABLE tasks RESTART IDENTITY CASCADE;
TRUNCATE TABLE users RESTART IDENTITY CASCADE;

-- users
INSERT INTO users (id, name) VALUES (1, 'a');
INSERT INTO users (id, name) VALUES (2, 'b');

-- tasks
INSERT INTO tasks (name, description, status, assignee)
VALUES ('a', 'task', 'NEW', 1);

INSERT INTO tasks (name, description, status, assignee)
VALUES ('b', 'not task', 'IN_PROGRESS', 1);

INSERT INTO tasks (name, description, status, assignee)
VALUES ('c', 'task', 'DONE', 2);
