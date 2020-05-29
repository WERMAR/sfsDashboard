create schema if not exists builderBoard;

create table builderBoard.project
(
    id           bigint not null auto_increment,
    order_number bigint not null,
    project_description varchar(255) not null,
    start date not null,
    end date not null,
    reminder boolean null,
    start_reminder int null,
    end_reminder int null,

    constraint pk__project_id primary key project(id)
);
