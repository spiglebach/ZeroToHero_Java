CREATE TABLE IF NOT EXISTS location (
    id bigint not null,
    name varchar(100) not null,
    address varchar(100) not null,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS equipment (
    id bigint not null,
    name varchar(100),
    type varchar(50) not null,
    located_at_id bigint
);