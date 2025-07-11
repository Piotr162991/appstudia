CREATE EXTENSION IF NOT EXISTS "pgcrypto";
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE employer
(
    id                  UUID PRIMARY KEY      default uuid_generate_v4(),
    created_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    company_name        VARCHAR(255) NOT NULL,
    address             VARCHAR(255) NOT NULL,
    city                VARCHAR(100) NOT NULL,
    postal_code         VARCHAR(10)  NOT NULL,
    phone               VARCHAR(20)  NOT NULL,
    email               VARCHAR(255) NOT NULL,
    website             VARCHAR(255),
    company_description TEXT,
    founded_year        INTEGER      NOT NULL,
    industry            VARCHAR(100) NOT NULL,
    number_of_employees INTEGER
)