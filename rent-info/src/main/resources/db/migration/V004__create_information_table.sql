CREATE TABLE IF NOT EXISTS info_data
(
    id        INT8 PRIMARY KEY NOT NULL,
    key_value VARCHAR(100),
    path_api  VARCHAR(300)
);



INSERT INTO info_data (id, key_value, path_api)
VALUES (1, 'Mzk5YzExY2NhMjA2NDAwOGJhNDJjMzAxYWY5OGM5MDY=',
        'aHR0cHM6Ly9hcGkub3BlbmNhZ2VkYXRhLmNvbS9nZW9jb2RlL3YxL2pzb24/cT0lcyslcyZub19hbm5vdGF0aW9ucz0xJmtleT0lcw==');