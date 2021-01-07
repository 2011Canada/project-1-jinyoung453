create schema project1;
set schema 'project1';

--drop table ERS_REIMBURSEMENT_STATUS;
create table ERS_REIMBURSEMENT_STATUS(
	REIMB_STATUS_ID serial,
	REIMB_STATUS	VARCHAR(10)	not null,
	constraint REIMB_STATUS_PK primary key (REIMB_STATUS_ID)
);
alter table ERS_REIMBURSEMENT_STATUS
ADD CONSTRAINT REIMB_STATUS_CHK check (REIMB_STATUS in('Pending', 'Approved', 'Denied'))

drop table ERS_REIMBURSEMENT_TYPE
create table ERS_REIMBURSEMENT_TYPE(
	REIMB_TYPE_ID serial,
	REIMB_TYPE	VARCHAR(10)	not null,
	constraint REIMB_TYPE_PK primary key (REIMB_TYPE_ID)
);

alter table ERS_REIMBURSEMENT_TYPE
ADD CONSTRAINT REIMB_TYPE_CHK check (REIMB_TYPE in('LODGING', 'TRAVEL', 'FOOD', 'OTHER'))

drop table ERS_USER_ROLES;
create table ERS_USER_ROLES(
	ERS_USER_ROLE_ID serial,
	USER_ROLE	VARCHAR(10)	not null,
	constraint ERS_USER_ROLES_PK primary key (ERS_USER_ROLE_ID)
);

create table ERS_USER_DEPARTMENT(
	USER_DEPARTMENT_ID 		serial,
	USER_DEPARTMENT_NAME 	VARCHAR(100),
	constraint ERS_USER_DEPARTMENT_PK primary key (USER_DEPARTMENT_ID)
)

insert into ERS_USER_DEPARTMENT (user_department_id , user_department_name)
values (1, 'Accounting'),
	   (2, 'Marketing'),
	   (3, 'HR'),
	   (4, 'IT')
	   
drop table ERS_USERS;
create table ERS_USERS(
	ERS_USERS_ID 	serial,
	ERS_USERNAME 	VARCHAR(50),
	ERS_PASSWORD 	VARCHAR(50),
	USER_FIRST_NAME VARCHAR(100),
	USER_LAST_NAME  VARCHAR(100),
	USER_EMAIL		VARCHAR(100),
	USER_ROLE_ID	integer,
	USER_DEPARTMENT_ID	integer,
	constraint ERS_USERS_PK primary key (ERS_USERS_ID),
	constraint ERS_USERS_UNv1 unique (ERS_USERNAME, USER_EMAIL),
	constraint USER_ROLES_FK foreign key (USER_ROLE_ID) references ERS_USER_ROLES(ERS_USER_ROLE_ID)
	constraint USER_DEPARTMENT_FK foreign key (USER_DEPARTMENT_ID) references ERS_USER_DEPARTMENT(USER_DEPARTMENT_ID)
);

drop table ERS_REIMBURSEMENT;
create table ERS_REIMBURSEMENT(
	REIMB_ID 		serial,
	REIMB_AMOUNT 	NUMERIC,
	REIMB_SUBMITTED TIMESTAMP,
	REIMB_RESOLVED 	TIMESTAMP,
	REIMB_DESCRIPTION VARCHAR(250),
	REIMB_RECEIPT   BLOB, --BLOB
	REIMB_AUTHOR	integer,
	REIMB_RESOLVER	integer,
	REIMB_STATUS_ID	integer,
	REIMB_TYPE_ID	integer,
	constraint ERS_REIMBURSEMENT_PK primary key (REIMB_ID),
	constraint ERS_USERS_FK_AUTH foreign key (REIMB_AUTHOR) references ERS_USERS(ERS_USERS_ID),
	constraint ERS_USERS_FK_RESLVR foreign key (REIMB_RESOLVER) references ERS_USERS(ERS_USERS_ID),
	constraint ERS_REIMBURSEMENT_STATUS_FK foreign key (REIMB_STATUS_ID) references ERS_REIMBURSEMENT_STATUS(REIMB_STATUS_ID),
	constraint ERS_REIMBURSEMENT_TYPE_FK foreign key (REIMB_TYPE_ID) references ERS_REIMBURSEMENT_TYPE(REIMB_TYPE_ID)
);

