CREATE TABLE IF NOT EXISTS public.types
(
    id serial NOT NULL,
    name character varying(100) NOT NULL,
    CONSTRAINT types_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.licenses
(
    id serial NOT NULL,
    namePo character varying(255) NOT NULL,
    idType integer NOT NULL,
    startDate Date NOT NULL,
    endDate Date NOT NULL,
    count integer NOT NULL,
    CONSTRAINT licenses_pkey PRIMARY KEY (id),
    CONSTRAINT idType FOREIGN KEY (idType)
        REFERENCES public.types (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);