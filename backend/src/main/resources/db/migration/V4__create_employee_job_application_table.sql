CREATE TABLE employee_job_applications
(
    employee_id  UUID      NOT NULL,
    job_offer_id UUID      NOT NULL,
    created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (employee_id, job_offer_id),
    FOREIGN KEY (employee_id) REFERENCES employees (id),
    FOREIGN KEY (job_offer_id) REFERENCES job_offers (id)
);
