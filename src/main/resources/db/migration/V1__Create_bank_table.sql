CREATE TABLE bank (
  id varchar(255) NOT NULL,
  code varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  status bool NOT NULL,
  CONSTRAINT bank_pk PRIMARY KEY (id)
)