-- src/main/resources/schema.sql

CREATE TABLE IF NOT EXISTS tickets (
                                       id SERIAL PRIMARY KEY,
                                       title VARCHAR(255) NOT NULL,
                                       shortDescription VARCHAR(255) NOT NULL,
                                       content TEXT,
                                       createdOn TIMESTAMP
);
