--CREATE DATABASE eshop WITH OWNER = postgres TABLESPACE = pg_default CONNECTION LIMIT = -1;

--
-- clients клиенты сервера авторизации
--
DROP TABLE if exists public.oauth_access_token cascade;
DROP TABLE if exists public.oauth_client_details cascade;
DROP TABLE if exists public.oauth_client_token cascade;
DROP TABLE if exists public.oauth_code cascade;
DROP TABLE if exists public.oauth_refresh_token cascade;


DROP TABLE if exists public.authorities cascade;
DROP TABLE if exists public.users cascade;

DROP TABLE if exists public.goods cascade;
DROP TABLE if exists public.goods_spb cascade;
DROP TABLE if exists public.goods_msk cascade;
DROP TABLE if exists public.brands cascade;
DROP TABLE if exists public.categories cascade;


DROP TABLE if exists public.pricelist cascade;
DROP TABLE if exists public.typeprice cascade;

drop table if exists public.goods_on_warehouses cascade;

DROP TABLE if exists public.order_items cascade;
DROP TABLE if exists public.orders cascade;
DROP TABLE if exists public.payment_method cascade;
DROP TABLE if exists public.status_history cascade;
DROP TABLE if exists public.delivery_method cascade;
DROP TABLE if exists public.warehouses cascade;
DROP TABLE if exists public.statuses cascade;

CREATE TABLE IF NOT EXISTS oauth_client_details
(
    client_id               VARCHAR(256) PRIMARY KEY,
    resource_ids            VARCHAR(256),
    client_secret           VARCHAR(256) NOT NULL,
    scope                   VARCHAR(256),
    authorized_grant_types  VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities             VARCHAR(256),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4000),
    autoapprove             VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_client_token
(
    token_id          VARCHAR(256),
    token             bytea,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_access_token
(
    token_id          VARCHAR(256),
    token             bytea,
    authentication_id VARCHAR(256),
    user_name         VARCHAR(256),
    client_id         VARCHAR(256),
    authentication    bytea,
    refresh_token     VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token
(
    token_id       VARCHAR(256),
    token          bytea,
    authentication bytea
);

CREATE TABLE IF NOT EXISTS oauth_code
(
    code           VARCHAR(256),
    authentication bytea
);



CREATE TABLE IF NOT EXISTS authorities
(
    username  VARCHAR(256) NOT NULL,
    authority VARCHAR(256) NOT NULL,
    PRIMARY KEY (username, authority)
);

create table statuses
(
    id   bigserial not null,
    code varchar(255),
    name varchar(255),
    primary key (id)
);
create table brands
(
    id          bigserial not null,
    description varchar(255),
    logo        varchar(255),
    name        varchar(255),
    code        varchar(255),
    primary key (id)
);
create table categories
(
    id   bigserial not null,
    code varchar(255),
    name varchar(255),
    primary key (id)
);
create table delivery_method
(
    id   bigserial not null,
    code varchar(255),
    name varchar(255),
    primary key (id)
);

create table goods
(
    id              bigserial not null,
    description     varchar(255),
    largepictureurl varchar(255),
    name            varchar(255),
    skud            int8,
    smallpictureurl varchar(255),
    brand_id        int8,
    category_id     int8,
    primary key (id)
);
create table goods_spb
(
    id              bigserial not null,
    description     varchar(255),
    largepictureurl varchar(255),
    name            varchar(255),
    skud            int8,
    smallpictureurl varchar(255),
    brand_id        int8,
    category_id     int8,
    primary key (id)
);
create table goods_msk
(
    id              bigserial not null,
    description     varchar(255),
    largepictureurl varchar(255),
    name            varchar(255),
    skud            int8,
    smallpictureurl varchar(255),
    brand_id        int8,
    category_id     int8,
    primary key (id)
);
create table goods_oth
(
    id              bigserial not null,
    description     varchar(255),
    largepictureurl varchar(255),
    name            varchar(255),
    skud            int8,
    smallpictureurl varchar(255),
    brand_id        int8,
    category_id     int8,
    primary key (id)
);
create table goods_on_warehouses
(
    id           int8      not null,
    good_id      bigserial not null,
    qnty         int8,
    warehouse_id int8      not null,
    primary key (good_id, warehouse_id)
);

create table order_items
(
    id       bigserial not null,
    price    float8,
    quantity int4,
    good_id  int8,
    order_id int8,
    primary key (id)
);
create table orders
(
    id                 bigserial not null,
    delivery_address   varchar(255),
    delivery_method_id int8,
    payment_method_id  int8,
    user_id            int8,
    primary key (id)
);
create table payment_method
(
    id          bigserial not null,
    code        varchar(255),
    name        varchar(255),
    payment_url varchar(255),
    primary key (id)
);
create table pricelist
(
    id            bigserial not null,
    datefrom      date,
    date_to       date,
    price         float8,
    good_id       int8,
    typeprice_id  int8,
    ware_house_id int8,
    primary key (id)
);
create table status_history
(
    id        bigserial not null,
    date_from timestamp,
    date_to   timestamp,
    address   varchar(255),
    order_id  int8,
    primary key (id)
);
create table typeprice
(
    id   bigserial not null,
    name varchar(255),
    primary key (id)
);
create table users
(
    id       bigserial not null,
    enabled  boolean,
    username varchar(255),
    fio      varchar(255),
    password varchar(255),
    phone    varchar(255),
    role     varchar(255),
    address  varchar(255),
    primary key (id)
);
create table warehouses
(
    id       bigserial not null,
    contacts varchar(255),
    name     varchar(255),
    schedule varchar(255),
    address  varchar(255),
    primary key (id)
);
alter table goods
    add constraint FKq0mwjy71tw44tcqnj2hb056hm foreign key (brand_id) references brands;
alter table goods
    add constraint FKm164hdre8y3lew7lvtu0sneqe foreign key (category_id) references categories;
alter table goods_on_warehouses
    add constraint FK4knv1caau2vyslmfoyrj8kj4m foreign key (warehouse_id) references warehouses;

--
-- отключаем constraint и надеемся на лучшее
--
--  alter table goods_on_warehouses add constraint FK6k4lwrcgo8ukesbhfpbwr4o5i foreign key (good_id) references goods;


alter table order_items
    add constraint FKmvm09ockbbse56dmhmlnj5rri foreign key (good_id) references goods;

alter table order_items
    add constraint FKbioxgbv59vetrxe0ejfubep1w foreign key (order_id) references orders;
alter table orders
    add constraint FKq2m2xkvrir0eftwpixx46v9l5 foreign key (delivery_method_id) references delivery_method;
alter table orders
    add constraint FKgeqwl6x0iadp9e2459uh3o8fv foreign key (payment_method_id) references payment_method;
alter table orders
    add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users;
alter table pricelist
    add constraint FKa8ick0q1tg7wfadh5h3ri1drw foreign key (good_id) references goods;
alter table pricelist
    add constraint FK4kmsdbifq9lvgt0jk2t0tlg9s foreign key (typeprice_id) references typeprice;
alter table pricelist
    add constraint FKmtoaefqjcsoms58i34y0x9jgc foreign key (ware_house_id) references warehouses;
alter table status_history
    add constraint FKov2onvtb867gv4uumrwu623vp foreign key (order_id) references orders;

commit;