CREATE TABLE public.records
(
    id SERIAL PRIMARY KEY NOT NULL,
    data VARCHAR(255),
    read INTEGER
);
CREATE UNIQUE INDEX record_id_uindex ON public.records (id);