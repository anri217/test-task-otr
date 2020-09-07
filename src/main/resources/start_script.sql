CREATE TABLE IF NOT EXISTS nodes
(
    id serial primary key,
    description VARCHAR(255),
    parent_node_id INTEGER,
    FOREIGN KEY (parent_node_id) REFERENCES nodes(id) ON DELETE CASCADE
);