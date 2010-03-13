-- multiple companies with our company
create table companies
(
 company_id varchar(10) not null,
 company_name varchar(50) not null, 
 address varchar(60) not null,
 address_2 varchar(60),
 city varchar(50) not null,
 state char(2) not null,
 zipcode varchar(10) not null,
 phone varchar(12),
 creation_date timestamp not null default now(),
 modified_date timestamp not null default now(),
 primary key (company_id)
);
 
-- Our departments
create table departments
(
 department_id varchar(10) not null, 
 company_id varchar(10) not null,
 name varchar(50) not null,
 description text not null,
 creation_date timestamp not null default now(),
 modified_date timestamp not null default now(),
 primary key(department_id,company_id),
 foreign key (company_id) references companies (company_id) on update cascade
);
 
-- Our Employees
create sequence employee_id_seq;
create table employees
(
 employee_id int not null default nextval('employee_id_seq'),
 company_id varchar(10) not null,
 department_id varchar(10) not null,
 first_name varchar(30) not null,
 last_name varchar(30) not null,
 email varchar(60),
 phone varchar(12),
 phone_ext varchar(4),
 birth_date date not null,
 start_date date not null,
 end_date date,
 employee_type char(1) not null, 
 status char(1) not null, 
 creation_date timestamp not null default now(),
 modified_date timestamp not null default now(),
 primary key(employee_id),
 foreign key (company_id) references companies (company_id) on update cascade,
 foreign key (department_id) references departments (department_id) on update cascade
);
 
-- Our clients
create table clients
(
 client_id varchar(10) not null,
 client_name varchar(50) not null,
 address varchar(60) not null,
 address_2 varchar(60),
 city varchar(50) not null,
 state char(2) not null,
 zipcode varchar(10) not null,
 phone varchar(12),
 phone_ext varchar(4),
 status char(1) not null, 
 assigned_employee_id int not null,
 creation_date timestamp not null default now(),
 modified_date timestamp not null default now(),
 primary key(client_id),
 foreign key (assigned_employee_id) references employees (employee_id)
);


-- Company and Client Linking Table
create table company_clients
(
 company_id varchar(10) not null,
 client_id varchar(10) not null,
 primary key (company_id,client_id),
 foreign key (company_id) references companies(company_id) on update cascade,
 foreign key (client_id) references clients(client_id) on update cascade
);

-- Our Client contacts
create sequence client_contact_id_seq;
create table client_contacts
(
 client_contact_id int not null default nextval('client_contact_id_seq'),
 client_id varchar(10) not null,
 department varchar(30),
 first_name varchar(30) not null,
 last_name varchar(30) not null,
 email varchar(60),
 phone varchar(12),
 phone_ext varchar(4),
 birth_date date,
 status char(1) not null,
 creation_date timestamp not null default now(),
 modified_date timestamp not null default now(),
 primary key (client_contact_id),
 foreign key (client_id) references clients (client_id) on update cascade
);

-- Our Vendors
create table vendors
(
 vendor_id varchar(10) not null, 
 vendor_name varchar(50) not null,
 address varchar(60) not null,
 address_2 varchar(60),
 city varchar(50) not null,
 state char(2) not null,
 zipcode varchar(10),
 phone varchar(12),
 status char(1) not null,
 assigned_employee_id int not null,
 creation_date timestamp not null default now(),
 modified_date timestamp not null default now(),
 primary key (vendor_id),
 foreign key (assigned_employee_id) references employees (employee_id)
);


-- Company and Vendor Linking Table
create table company_vendors
(
 company_id varchar(10) not null,
 vendor_id varchar(10) not null,
 primary key (company_id,vendor_id),
 foreign key (company_id) references companies(company_id) on update cascade,
 foreign key (vendor_id) references vendors(vendor_id) on update cascade
);


-- Our Vendor contacts
create sequence vendor_contact_id_seq;
create table vendor_contacts
(
 vendor_contact_id int not null default nextval('vendor_contact_id_seq'),
 vendor_id varchar(10) not null,
 department varchar(30),
 first_name varchar(30) not null,
 last_name varchar(30) not null,
 email varchar(60),
 phone varchar(12),
 phone_ext varchar(4),
 birth_date date,
 status char(1) not null,
 creation_date timestamp not null default now(),
 modified_date timestamp not null default now(),
 primary key (vendor_contact_id),
 foreign key (vendor_id) references vendors (vendor_id) on update cascade
 
);

 

 

