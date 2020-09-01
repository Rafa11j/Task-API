CREATE TABLE task_lists (
    id uuid PRIMARY KEY,
    name character varying(255) NOT NULL UNIQUE,
    created_at timestamp without time zone,
    updated_at timestamp without time zone
);

CREATE TABLE task_items (
    id uuid PRIMARY KEY,
    name character varying(255) NOT NULL,
    finished boolean DEFAULT false,
    task_list_id uuid NOT NULL REFERENCES task_lists(id),
    created_at timestamp without time zone,
    updated_at timestamp without time zone
);