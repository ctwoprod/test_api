CREATE TABLE branch (
  id varchar(255) NOT NULL,
  code varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  status bool NOT NULL,
  CONSTRAINT branch_pk PRIMARY KEY (id)
)