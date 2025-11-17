CREATE TABLE tasks (
    id bigserial primary key,
    name text not null,
    description text
    status TEXT NOT NULL DEFAULT 'new' CHECK (
            status IN ('new', 'in_progress', 'done')
        ),
    assignee BIGINT,
    CONSTRAINT fk_assignee
        FOREIGN KEY (assignee) REFERENCES users(id)
);