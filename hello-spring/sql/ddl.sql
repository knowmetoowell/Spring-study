// tip 중요하게 썼던 sql문들 여기에 기록해놓고 보면 나중에 편함!



drop table if exists member CASCADE;
create table member
(
 id bigint generated by default as identity,
 name varchar(255),
 primary key (id)
);