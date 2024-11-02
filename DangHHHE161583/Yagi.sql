use master
GO
drop database if exists yagi_pharmacy
GO
create database yagi_pharmacy
GO
use yagi_pharmacy
GO
create table [user](
[user_id] int primary key identity(1,1),
[user_name] nvarchar(50) not null,
user_phone varchar(50) not null unique,
user_email varchar(50) unique,
user_password varchar(100) not null,
role_level int,
user_avatar varchar(max),
user_province nvarchar(100),
user_district nvarchar(100),
user_commune nvarchar(100),
specific_address nvarchar(400),
date_of_birth varchar(500),
created_date varchar(500) not null,
active_code varchar(10) not null,
is_active bit default(0) not null,
is_deleted bit default(0) not null
)
GO
create table [staff](
staff_id int primary key identity(1,1),
[user_id] int references [user]([user_id]) unique, 
staff_major varchar(500),
staff_education nvarchar(4000),
staff_experience nvarchar(4000),
staff_description nvarchar(4000),
gender bit default(1),
is_deleted bit default(0) not null
)
GO
create table news_category(
news_category_id int primary key identity(1,1),
news_category_parent_id int,
news_category_level int,
news_category_name nvarchar(200),
news_category_detail nvarchar(500),
is_delete bit default(0)
)
GO
create table [news](
news_id int primary key identity(1,1),
news_category_id int references news_category(news_category_id),
creator_id int references [user]([user_id]),
news_title nvarchar(200),
news_content nvarchar(max),
news_image varchar(max),
news_hashtag nvarchar(300),
updated_id int references [user]([user_id]), 
created_date varchar(500),
is_rejected int,
rejected_reason nvarchar(2000),
is_deleted bit default(0)
)
GO
create table product_category(
product_category_id int primary key identity(1,1),
product_category_parent_id int,
product_category_level int,
product_category_code varchar(50) not null unique,
product_category_name nvarchar(200),
product_category_detail nvarchar(500),
is_deleted bit default(0)
)
GO
create table supplier(
supplier_id int primary key identity(1,1),
supplier_code nvarchar(200),
supplier_name nvarchar(200),
supplier_country_code varchar(50),
supplier_phone varchar(50),
supplier_email varchar(50),
is_deleted bit default(0)
)

GO
create table [product](
product_id int primary key identity(1,1),
product_code varchar(500) unique,
product_category_id int references product_category(product_category_id),
product_country_code varchar(50),
brand nvarchar(200),
product_target nvarchar(500),
product_name nvarchar(300),
dosage_form nvarchar(300),
product_specification nvarchar(300),
product_desciption nvarchar(4000),
created_date varchar(500),
is_prescription bit default(0),
is_deleted bit default(0)
)
GO
create table excipient(
excipient_id int primary key identity(1,1),
excipient_name varchar(300),
is_deleted bit default(0)
)
GO
create table unit(
unit_id int primary key identity(1,1),
unit_name nvarchar(200)
)
GO
create table product_excipient(
product_excipient_id int primary key identity(1,1),
product_id int references [product](product_id),
excipient_id int references excipient(excipient_id),
quantity float,
unit_measurement nvarchar(400)
)
GO
create table product_unit(
product_unit_id int primary key identity(1,1),
parent_id int,
product_id int references [product](product_id),
unit_id int references unit(unit_id),
sell_price float,
quantity_per_unit int,
product_unit_name nvarchar(200),
can_be_sold bit default(0),
is_deleted bit default(0)
)
GO
create table product_image(
product_image_id int primary key identity(1,1),
product_id int references [product](product_id),
image_base64 varchar(max),
is_main bit default(1),
is_deleted bit default(0)
)
GO
create table import_order(
import_order_id int primary key identity(1,1),
import_order_code varchar(50),
created_by int references [user]([user_id]),
created_date varchar(500),
approved_by int references [user]([user_id]),
approved_date varchar(500),
import_expected_date varchar(500),
is_accepted bit default(0),
rejected_reason nvarchar(2000),
is_deleted bit default(0)
)
GO
create table import_order_detail(
import_order_detail_id int primary key identity(1,1),
batch_code varchar(50),
import_order_id int references import_order(import_order_id),
product_id int references [product](product_id),
unit_id int references unit(unit_id),
quantity int,
import_price float,
import_date varchar(500),
supplier_id int references supplier(supplier_id),
processing int,
is_deleted bit default(0)
)
GO
create table stock(
stock_id int primary key identity(1,1),
batch_code varchar(50),
quantity int,
product_id int references [product](product_id),
unit_id int references unit(unit_id),
MFG_date varchar(500),
EXP_date varchar(500),
is_deleted bit default(0)
)
GO
create table sale_order(
sale_order_id int primary key identity(1,1),
order_by int references [user]([user_id]),
receiver_name nvarchar(50),
receiver_phone varchar(50),
receiver_email varchar(100),
province nvarchar(100),
district nvarchar(100),
commune nvarchar(100),
specific_address nvarchar(400),
total_price float,
created_date varchar(500),
is_paid bit default(0),
is_deleted bit default(0)
)
GO 
create table sale_order_detail(
order_detail_id int primary key identity(1,1),
sale_order_id int references sale_order(sale_order_id),
product_id int references [product](product_id),
unit_id int references unit(unit_id),
quantity int,
batch_code varchar(50),
is_deleted bit default(0)
)
GO
create table sale_revenue(
sale_revenue_id int primary key identity(1,1),
product_id int references [product](product_id),
total_order int,
total_revenue float,
is_deleted bit default(0),
)


