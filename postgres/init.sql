--insert into store (id, value, tenant) values (-1, 'huhu', 'mathema');
CREATE TABLE IF NOT EXISTS store
(
    id BIGINT NOT NULL,
    tenant   VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL,
    PRIMARY KEY (id, tenant)
);

CREATE SEQUENCE Store_SEQ START WITH 1 INCREMENT BY 50;

ALTER TABLE ONLY store FORCE ROW LEVEL SECURITY;
ALTER TABLE store ENABLE ROW LEVEL SECURITY;--do not forget to enable it

CREATE POLICY tenant_isolation_policy ON
  store USING (((tenant)::text = current_setting('app.current_tenant'::text)));


INSERT INTO store (ID, VALUE, TENANT) VALUES (-1, 'Hallo MATHEMA', 'mathema');
INSERT INTO store (ID, VALUE, TENANT) VALUES (-2, 'Hallo Campus', 'campus');
INSERT INTO store (ID, VALUE, TENANT) VALUES (-3, 'Hallo Sascha', 'sascha');
INSERT INTO store (ID, VALUE, TENANT) VALUES (-4, 'Hallo JAX 2024', 'jax');


CREATE ROLE tenant WITH
    LOGIN
    NOSUPERUSER
    NOCREATEDB
    NOCREATEROLE
    INHERIT
    NOREPLICATION
    CONNECTION LIMIT -1
    PASSWORD 'tenant_geheim';

GRANT INSERT, SELECT, UPDATE, DELETE ON TABLE store TO tenant;
GRANT ALL ON SEQUENCE Store_SEQ TO tenant;



