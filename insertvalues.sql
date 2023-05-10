-- Active: 1682586395608@@127.0.0.1@3306@barasserie

INSERT INTO workflow_type (code, description) VALUES ("client_transaction", "client transaction");
INSERT INTO workflow (agent, type, isClosed) VALUES (1, "client_transaction", TRUE);

INSERT INTO doc_type (code, description) VALUES ("invoice", "invoice");

INSERT INTO doc_status (code, description) VALUES ("paid", "invoice paid");
INSERT INTO doc_status (code, description) VALUES ("unpaid", "invoice unpaid");

INSERT INTO document (`docType`, id, workflow, `date`, `exclVATTotal`, `inclVATTotal`, `reminderCount`, `payementDeadline`, `docStatus`)
VALUES ("invoice", 1, 1, "2022-05-01", 42.72, 47.76, 0, "2022-05-08", "paid");

INSERT INTO document (`docType`, id, workflow, `date`, `exclVATTotal`, `inclVATTotal`, `reminderCount`, `payementDeadline`, `docStatus`)
VALUES ("invoice", 2, 2, "2022-08-01", 2.37, 3.00, 0, "2022-08-08", "paid");

INSERT INTO document (`docType`, id, workflow, `date`, `exclVATTotal`, `inclVATTotal`, `reminderCount`, `payementDeadline`, `docStatus`)
VALUES ("invoice", 3, 3, "2022-09-01", 42.72, 47.76, 0, "2022-09-08", "paid");

INSERT INTO transaction_detail (id, document, `docType`, product, quantity, `unitPrice`)
VALUES (1, 1, "invoice", 5, 24, 1.99);

INSERT INTO transaction_detail (id, document, `docType`, product, quantity, `unitPrice`)
VALUES (2, 2, "invoice", 2, 2, 1.50);

INSERT INTO transaction_detail (id, document, `docType`, product, quantity, `unitPrice`)
VALUES (3, 3, "invoice", 5, 24, 1.99);