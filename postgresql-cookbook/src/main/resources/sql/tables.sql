create table users(
  id varchar primary key,
  name varchar(64)
);

create table logs(
  id varchar primary key,
  data text,
  create_time timestamp,
  level varchar(10),
  user_id varchar,
  constraint user_id_logs_fk foreign key(user_id) references users(id)
);

create index create_time_logs_idx on logs (create_time);
