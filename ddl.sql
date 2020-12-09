create sequence hibernate_sequence start with 1 increment by 1;
create table commission
(
    commission_id      bigint       not null,
    commission_request varchar(200) not null,
    timestamp          timestamp    not null,
    waitlist_position  integer      not null,
    commissioner_id    bigint       not null,
    product_id         bigint,
    seller_id          bigint,
    primary key (commission_id)
);
create table image
(
    image_id          bigint       not null,
    created           timestamp    not null,
    image_description varchar(255),
    image_file_name   varchar(255) not null,
    product_id        bigint       not null,
    primary key (image_id)
);
create table placed_order
(
    order_id    bigint    not null,
    placed_date timestamp not null,
    buyer_id    bigint    not null,
    seller_id   bigint    not null,
    primary key (order_id)
);
create table product
(
    product_id   bigint       not null,
    description  varchar(255),
    product_name varchar(255) not null,
    posted_date  timestamp    not null,
    price        integer      not null,
    stock        integer      not null,
    profile_id   bigint       not null,
    primary key (product_id)
);
create table product_on_order
(
    product_on_order_id bigint  not null,
    item_quantity       integer not null,
    unit_price          integer not null,
    order_id            bigint  not null,
    product_id          bigint  not null,
    primary key (product_on_order_id)
);
create table profile
(
    profile_id bigint       not null,
    address    varchar(255),
    email      varchar(255) not null,
    oauth      varchar(255) not null,
    username   varchar(255) not null,
    primary key (profile_id)
);
create table profile_picture
(
    id           bigint       not null,
    content_type varchar(255) not null,
    created      timestamp    not null,
    name         varchar(255) not null,
    path         varchar(255) not null,
    user_id      bigint       not null,
    primary key (id)
);
create index IDXfujtkp830qt7of6ucsl110vmv on commission (waitlist_position);
alter table image
    add constraint UK_axg5ixbh7tye8lc9xwtgm1a32 unique (image_file_name);
alter table profile
    add constraint UK_9d5dpsf2ufa6rjbi3y0elkdcd unique (email);
alter table profile
    add constraint UK_msh3rg1xuru67t2npsqfs15ee unique (oauth);
alter table profile
    add constraint UK_5em4hwqp4woqsf49dru7fjo80 unique (username);
alter table commission
    add constraint FKd9e3j53kls0ybp4yq7wm5dcy foreign key (commissioner_id) references profile;
alter table commission
    add constraint FKqhgrwcqymr2ks1dh4kqmy6vbo foreign key (product_id) references product;
alter table commission
    add constraint FKc7m7nwjhiyy39v8g17m1sutpq foreign key (seller_id) references profile;
alter table image
    add constraint FKgpextbyee3uk9u6o2381m7ft1 foreign key (product_id) references product;
alter table placed_order
    add constraint FKn1oh4nkp5uo29mulkjsda0mcy foreign key (buyer_id) references profile;
alter table placed_order
    add constraint FKd83vq7n4l3pmc27abqltqq55w foreign key (seller_id) references profile;
alter table product
    add constraint FK2oc85d5p2pu4ia3tjbx4c25w4 foreign key (profile_id) references profile;
alter table product_on_order
    add constraint FKr0ss54m4f51wnykkd14ue3bv foreign key (order_id) references placed_order;
alter table product_on_order
    add constraint FKdujqh9ogotyjn2u6c4bu4g778 foreign key (product_id) references product;
alter table profile_picture
    add constraint FKe1u9y03jwhjpiebosk7hrax7d foreign key (user_id) references profile;
