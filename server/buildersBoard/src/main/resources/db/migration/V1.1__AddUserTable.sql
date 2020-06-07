create table builderBoard.user
(
    id         bigint         not null auto_increment,
    last_name  varchar(255) not null,
    first_name varchar(255) not null,

    constraint pk__user_id primary key user (id)
);

alter table builderboard.project
    add column responsible_person bigint not null;

alter table builderboard.project
    add constraint fk__project_responsiblePerson__user foreign key project (responsible_person) references builderboard.user (id);
