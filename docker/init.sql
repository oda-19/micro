DROP TABLE IF EXISTS licenses;

CREATE TABLE IF NOT EXISTS public.licenses
(
    license_id character varying(30) NOT NULL,
    organization_id character varying(30) NOT NULL,
    description character varying(250),
    product_name character varying(150) NOT NULL,
    license_type character varying(30) NOT NULL,
    comment character varying(250),
    CONSTRAINT licenses_pkey PRIMARY KEY (license_id)
);