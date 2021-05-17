-- create the core tables
use builderBoard;

create table user
(
    id        bigint(20)  not null,
    username  varchar(10) not null,
    firstname varchar(15) not null,
    lastname  varchar(15) not null,
    user_role bigint(20)  not null,

    -- add primary key
    constraint pk__user__id primary key user (id)
);

create table user_role
(
    id              bigint(20)  not null,
    name            varchar(15) not null,
    underlying_role bigint(20)  null,

    -- add primary key
    constraint pk__user_role__id primary key user_role (id)
);

create table user_details
(
    id            bigint(20) not null,
    user          bigint(20) not null,
    layout_config bigint(20) not null,

    -- add primary key
    constraint pk__user_details__id primary key user_details (id)
);

create table layout_config
(
    id                    bigint(20) not null,
    service_display       boolean    not null,
    project_display       boolean    not null,
    newsfeed_display      boolean    not null,
    personalLinks_display boolean    not null,
    cal_clock_display     boolean    not null,

    -- add primary key
    constraint pk__layout_config__id primary key layout_config (id)
);

create table quick_links
(
    id            bigint(20)  not null,
    name          varchar(30) not null,
    link          varchar(40) not null,
    standard_link boolean     not null,

    -- add primary key
    constraint pk__quick_links__id primary key quick_links (id)
);

create table project_details
(
    id           bigint(20)  not null,
    project      bigint(20)  not null,
    building     bigint(20)  not null,
    name         varchar(30) not null,
    order_number varchar(50) not null,
    start        date        not null,
    end          date        not null,
    description  varchar(255),

    -- add primary key
    constraint pk__project_details__id primary key project_details (id)
);

create table pd_2_hotelresv
(
    project_details   bigint(20) not null,
    hotel_reservation bigint(20) not null,

    -- add combined primary key
    constraint pk__pd_2_hotelresv__id primary key pd_2_hotelresv (project_details, hotel_reservation)
);

create table project
(
    id                 bigint(20) not null,
    responsible_person bigint(20) not null,
    is_service         boolean    not null,

    -- add primary key
    constraint pk__project__id primary key project (id)
);

create table customer
(
    id       bigint(20)   not null,
    name     varchar(30)  not null,
    logo     blob         null,
    logo_url varchar(255) null,

    -- add primary key
    constraint pk__customer__id primary key customer (id)
);

create table building
(
    id       bigint(20)  not null,
    customer bigint(20)  not null,
    name     varchar(30) not null,

    -- add primary key
    constraint pk__building__id primary key building (id)
);

create table hotel_reservation
(
    id            bigint(20)     not null,
    booking_file  bigint(20)     not null,
    name_of_hotel varchar(50)    not null,
    nLat          decimal(11, 8) not null,
    eLong         decimal(11, 8) not null,

    -- add primary key
    constraint pk__hotel_reservation__id primary key hotel_reservation (id)
);

create table service_details
(
    id      bigint(20)  not null,
    project bigint(20)  not null,
    name    varchar(30) not null,

    -- add primary key
    constraint pk__serviceDetails__id primary key service_details (id)
);

create table contract_details
(
    id              bigint(20)  not null,
    service_details bigint(20)  not null,
    contract_number varchar(30) not null,
    technician      bigint(20)  not null,

    -- add primary key
    constraint pk__contract_details__id primary key contract_details (id)
);

create table system_types
(
    id               bigint(20)  not null,
    contract_details bigint(20)  not null,
    name             varchar(30) not null,

    -- add primary key
    constraint pk__system_types__id primary key system_types (id)
);

create table planned_month_to_systems
(
    st_id bigint(20) not null,
    pq_id bigint(20) not null,

    -- add combined primary key
    constraint pk__planned_month_to_systems__id primary key planned_month_to_systems (st_id, pq_id)
);

create table processing_month
(
    id         bigint(20)  not null,
    month_name varchar(35) not null,
    month_num  int(2)      not null,

    -- add primary key
    constraint pk__processing_month__id primary key processing_month (id)
);

create table material
(
    id                bigint(20)  not null,
    material_number   varchar(40) not null,
    name              varchar(30) not null,
    current_available int(10)     not null,

    -- add primary key
    constraint pk__material__id primary key material (id)
);

create table files
(
    id            bigint(20)   not null,
    file_category bigint(20)   not null,
    file_name     varchar(512) not null,

    -- add primary key
    constraint pk__files__id primary key files (id)
);

create table file_category
(
    id           bigint(20)   not null,
    display_name varchar(512) not null,
    folder_name  varchar(512) not null,

    -- add primary key
    constraint pk__file_category__id primary key file_category (id)
);

create table ql_2_ud
(
    ql_id bigint(20) not null,
    ud_id bigint(20) not null,

    -- add combined primary key
    constraint pk__ql_2_ud__id primary key ql_2_ud (ql_id, ud_id)
);

create table user_2_project
(
    project_id bigint(20) not null,
    user_id    bigint(20) not null,

    -- add combined primary key
    constraint pk__user_2_project__id primary key user_2_project (project_id, user_id)
);

create table files_2_project_details
(
    file_id            bigint(20) not null,
    project_details_id bigint(20) not null,

    -- add combined primary key
    constraint pk__files_2_project_details__id primary key files_2_project_details (file_id, project_details_id)
);

create table files_2_hotel_resv
(
    file_id       bigint(20) not null,
    hotel_resv_id bigint(20) not null,

    -- add combined primary key
    constraint pk__files_2_hotel_resv__id primary key files_2_hotel_resv (file_id, hotel_resv_id)
);

create table material_2_service
(
    material        bigint(20) not null,
    service_details bigint(20) not null,

    -- add combined primary key
    constraint pk__material_2_services__id primary key material_2_service (material, service_details)
);

create table material_2_project
(
    material        bigint(20) not null,
    project_details bigint(20) not null,
    number          int(10)    not null,

    -- add combined primary key
    constraint pk__material_2_project__id primary key material_2_project (material, project_details)
);

create table files_2_service_details
(
    file_id            bigint(20) not null,
    service_details_id bigint(20) not null,

    -- add combined primary key
    constraint pk__files_2_service_details__id primary key files_2_service_details (file_id, service_details_id)
);

-- setup foreign keys
alter table user_role
    add foreign key user_role (underlying_role) references user_role (id);

alter table user
    add foreign key user (user_role) references user_role (id);

alter table user_details
    add foreign key user_details_u (user) references user (id),
    add foreign key user_details_lc (layout_config) references layout_config (id);

alter table ql_2_ud
    add foreign key ql_2_ud_u (ud_id) references user_details (id),
    add foreign key ql_2_ud_q (ql_id) references quick_links (id);

alter table user_2_project
    add foreign key user_2_project_p (project_id) references project (id),
    add foreign key user_2_project_u (user_id) references user (id);

alter table project
    add foreign key project (responsible_person) references user (id);

alter table project_details
    add foreign key project_details_p (project) references project (id),
    add foreign key project_details_b (building) references building (id);

alter table pd_2_hotelresv
    add foreign key pd_2_hotelresv_p (project_details) references project_details (id),
    add foreign key pd_2_hotelresv_h (hotel_reservation) references hotel_reservation (id);

alter table files_2_hotel_resv
    add foreign key files_2_hotel_resv_f (file_id) references files (id),
    add foreign key files_2_hotel_resv_h (hotel_resv_id) references hotel_reservation (id);

alter table files_2_project_details
    add foreign key files_2_project_details_f (file_id) references files (id),
    add foreign key files_2_project_details_p (project_details_id) references project_details (id);

alter table files
    add foreign key files (file_category) references file_category (id);

alter table material_2_project
    add foreign key material_2_project_m (material) references material (id),
    add foreign key material_2_project_p (project_details) references project_details (id);

alter table material_2_service
    add foreign key material_2_service_m (material) references material (id),
    add foreign key material_2_service_s (service_details) references service_details (id);

alter table building
    add foreign key building (customer) references customer (id);

alter table files_2_service_details
    add foreign key files_2_service_details_f (file_id) references files (id),
    add foreign key files_2_service_details_s (service_details_id) references service_details (id);

alter table planned_month_to_systems
    add foreign key planned_month_to_systems_st (st_id) references system_types (id),
    add foreign key planned_month_to_systems_pq (pq_id) references processing_month (id);

alter table system_types
    add foreign key system_types (contract_details) references contract_details (id);

alter table contract_details
    add foreign key contract_details_sd (service_details) references service_details (id),
    add foreign key contract_details_t (technician) references user (id);

alter table service_details
    add foreign key service_details (project) references project (id);



-- initial data setup --
-- first add user_roles: super_admin, admin, user
-- second add user: super_admin
-- third add user_details for super_admin
-- fourth add layout_config for super_admin
-- fifth add processing_month
insert into user_role
values (1, 'user', null),
       (2, 'admin', 1),
       (3, 'superAdmin', 2);

insert into user
values (1, 'superAdmin', 'super', 'admin', 3);

insert into layout_config
values (1, true, true, true, true, true);

insert into user_details value (1, 1, 1);