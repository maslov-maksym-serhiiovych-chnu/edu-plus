CREATE TABLE comments
(
    id      INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY NOT NULL,
    content TEXT                                         NOT NULL
)