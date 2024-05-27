CREATE TABLE IF NOT EXISTS public.types
(
    id integer,
    name character varying(100),
    CONSTRAINT types_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.licenses
(
    id integer,
    namePo character varying(255),
    idType integer,
    startDate Date,
    endDate Date,
    count integer,
    CONSTRAINT licenses_pkey PRIMARY KEY (id),
    CONSTRAINT licenses_types_id_fkey FOREIGN KEY (idType)
        REFERENCES public.types (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);