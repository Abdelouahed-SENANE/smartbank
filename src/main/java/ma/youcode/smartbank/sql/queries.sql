
create type request_status AS ENUM  ('EN_ATTENTE','APPROUVÉ','REJETÉ', 'TERMINÉ');
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
create table if not exists requests (
    request_id UUID primary key DEFAULT uuid_generate_v4(),
    project varchar(40),
    job varchar(40),
    amount double precision,
    monthly double precision,
    duration int,
    fees double precision,
    email varchar(50),
    phone varchar(50),
    firstname varchar(70),
    lastname varchar(70),
    cin varchar(20),
    birthday_date timestamp,
    hiring_date timestamp,
    income double precision
);
create table if not exists statuses(
                                       status_id UUID primary key DEFAULT uuid_generate_v4(),
                                       status_name varchar(30) not null
);

create table request_status_history(
                                       request_id uuid,
                                       status_id uuid,
                                       changed_at timestamp default current_timestamp,
                                       foreign key (request_id) references requests(request_id) ON delete cascade on update cascade,
                                       foreign key (status_id) references statuses(status_id) ON delete cascade on update cascade
);

INSERT INTO statuses (statusname)
values ('EN ATTENTE') , ('APPROUVÉ') , ('REJETÉ'),('TERMINÉ')
