create table products
(
    id         bigserial primary key,
    title      varchar(255),
    price      numeric(8, 2) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into products (title, price)
values ('Молоко', 100.20),
       ('Хлеб', 80.20),
       ('Сыр', 190.20),
       ('Икра Красная', 690.00),
       ('Яйца', 65.00),
       ('Мука', 44.00),
       ('Вода минеральная', 69.99),
       ('Вино игристое', 745.00),
       ('Гречневая крупа', 84.00),
       ('Пиво', 99.00),
       ('Хлопья овсяные', 71.00);
       ('Зонт', 731.00);
       ('Бумага', 485.90);
       ('Кетчуп', 58.00);
       ('Кузуруза', 71.00);
       ('Шоколад', 89.00);
       ('Плащ-дождевик', 299.00);
       ('Ботинки', 999.00);
       ('Черный перец', 5.00);
       ('Ремень', 525.10);
       ('Соль', 75.00);
       ('Майка', 875.90);

create table orders
(
    id          bigserial primary key,
    username    varchar(255)  not null,
    total_price numeric(8, 2)  not null,
    address     varchar(255),
    phone       varchar(255),
    status      int,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product numeric(8, 2)    not null,
    price             numeric(8, 2)    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

insert into orders (username, total_price, address, phone, status)
values ('bob', 2960.40, '190000, Россия, Санкт-Петербург, Невский пр-кт, д.1', '+79000000000', 0);

insert into order_items (product_id, order_id, quantity, price_per_product, price)
values (1, 1, 2, 100.20, 200.40), (4, 1, 4, 690.00, 2760.00);









