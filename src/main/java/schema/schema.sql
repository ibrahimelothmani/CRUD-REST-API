create sequence user_id_seq start with 1 increment by 50;
create table users (
  id bigint not null default nextval('user_id_seq'),
  title varchar(200) not null,
  url varchar(500) not null,
  created_at timestamp not null default now(),
  updated_at timestamp,
  primary key (id)
);
insert into
  users(title, url, created_at)
values
  (
    'Ibrahim',
    'https://ibrahim-el-othmani.rf.gd/',
    '2024-21-11'
  ),
  (
    'Oussama',
    'https://www.youtube.com/',
    '2024-10-10'
  ),
  ('Sofien', 'https://www.youtube.com/', '2024-12-05'),
  (
    'Mohamed',
    'https://www.youtube.com/',
    '2024-08-15'
  );