drop schema if exists csfcontacts;
create schema csfcontacts;

use csfcontacts;

create table contacts(
    user_id int auto_increment primary key,
    name varchar(32) not null,
    email varchar(128) not null,
    mobile varchar(128) not null
);