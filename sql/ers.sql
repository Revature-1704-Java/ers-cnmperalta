-- Drop database if it exists
drop user ersuser cascade;

-- Create database
create user ersuser
identified by p4ssw0rd
default tablespace users
temporary tablespace temp
quota 10M on users;

grant connect to ersuser;
grant resource to ersuser;
grant create session to ersuser;
grant create table to ersuser;
grant create view to ersuser;

conn ersuser/p4ssw0rd

create table Employee (
	EmployeeID number not null,
	FirstName varchar2(50),
	LastName varchar2(50),
	ManagerID number,
	EmailAddress varchar2(50),
	Password varchar2(64),
	EmployeeType varchar2(20),
	constraint PK_Employee primary key (EmployeeID)
);

create table Reimbursement (
	ReimbursementID number not null,
	Amount number(10, 2),
	EmployeeID number,
	ManagerID number,
	Justification varchar2(500),
	Approved number(1),
	constraint PK_Reimbursement primary key (ReimbursementID)
);

alter table Employee add constraint FK_Employee_ManagerID
	foreign key (ManagerID) references Employee (EmployeeID);

alter table Reimbursement add constraint FK_Reimbursement_EmployeeID
	foreign key (EmployeeID) references Employee (EmployeeID);

alter table Reimbursement add constraint FK_Reimbursement_ManagerID
	foreign key (ManagerID) references Employee (EmployeeID);

commit;
exit;