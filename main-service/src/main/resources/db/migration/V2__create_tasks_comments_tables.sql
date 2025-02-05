create table tasks
(
    id          uuid,
    title       varchar(50)                 not null,
    description varchar(255)                not null,
    state       varchar(20)                 not null,
    priority    varchar(20)                 not null,
    author_id   uuid                        not null,
    executor_id uuid                        not null,
    created_at  timestamp without time zone not null,
    CONSTRAINT tasks_pk PRIMARY KEY (id),
    CONSTRAINT tasks_author_fk foreign key (author_id) references users (id),
    CONSTRAINT tasks_executor_fk foreign key (executor_id) references users (id)
);

create table comments
(
    id          uuid,
    description varchar(255) not null,
    author_id   uuid         not null,
    task_id     uuid         not null,
    CONSTRAINT comments_pk PRIMARY KEY (id),
    CONSTRAINT comments_author_fk foreign key (author_id) references users (id),
    CONSTRAINT comments_task_fk foreign key (task_id) references tasks (id)
);
