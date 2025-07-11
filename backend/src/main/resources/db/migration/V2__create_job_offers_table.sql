CREATE TABLE job_offers
(
    id               UUID PRIMARY KEY        DEFAULT uuid_generate_v4(),
    title            VARCHAR(255)   NOT NULL,
    description      TEXT           NOT NULL,
    salary_from      DECIMAL(12, 2) NOT NULL,
    salary_to        DECIMAL(12, 2),
    location         VARCHAR(255)   NOT NULL,
    employment_type  VARCHAR(50)    NOT NULL,
    publication_date TIMESTAMP      NOT NULL,
    expiration_date  TIMESTAMP,
    employer_id      UUID           NOT NULL,
    created_at       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employer_id) REFERENCES employer (id)
);
