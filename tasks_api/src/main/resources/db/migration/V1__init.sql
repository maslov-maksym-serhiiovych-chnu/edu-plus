CREATE TABLE tasks
(
    id          INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    name        VARCHAR(50)                                  NOT NULL,
    description TEXT
)