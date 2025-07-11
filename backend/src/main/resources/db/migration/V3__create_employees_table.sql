CREATE TABLE employees
(
    id            UUID PRIMARY KEY      DEFAULT uuid_generate_v4(),
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE,
    phone         VARCHAR(20)  NOT NULL,
    date_of_birth DATE         NOT NULL,
    address       VARCHAR(255),
    city          VARCHAR(100),
    postal_code   VARCHAR(10),
    resume        VARCHAR(255),
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
