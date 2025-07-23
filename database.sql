create database task_management;

use task_management;

create table users (
	id BIGINT auto_increment primary key,
	username VARCHAR(50) unique not null,
	password VARCHAR(255) not null,
	email VARCHAR(100) unique not null,
	created_at TIMESTAMP default CURRENT_TIMESTAMP()
)

create table tasks (
	id BIGINT auto_increment primary key,
	title VARCHAR(100) not null,
	description TEXT,
	status ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED') not null default 'PENDING',
	user_id BIGINT not null,
	created_at TIMESTAMP default CURRENT_TIMESTAMP(),
	updated_at TIMESTAMP default CURRENT_TIMESTAMP(),
	foreign key (user_id) references users(id) on delete cascade
)