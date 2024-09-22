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
user_password varchar(50) not null,
role_level int,
user_avatar varchar(max),
user_bank varchar(30),
user_bank_code varchar(100),
user_province nvarchar(100),
user_district nvarchar(100),
user_commune nvarchar(100),
specific_address nvarchar(400),
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
is_deleted bit default(0) not null
)
GO
create table news_category(
news_category_id int primary key identity(1,1),
news_category_parent_id int,
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
news_hashtag varchar(300),
updated_id int references [user]([user_id]), 
created_date varchar(500),
is_deleted bit default(0)
)
GO
create table product_category(
product_category_id int primary key identity(1,1),
product_category_parent_id int,
product_category_code varchar(50) not null unique,
product_category_name varchar(200),
product_category_detail nvarchar(500),
is_deleted bit default(0)
)
GO
create table supplier(
supplier_id int primary key identity(1,1),
supplier_code nvarchar(200),
supplier_name nvarchar(200),
supplier_country_code varchar(50),
is_deleted bit default(0)
)
GO
create table product(
product_id int primary key identity(1,1),
product_code varchar(500) unique,
product_category_id int references product_category(product_category_id),
product_country_code varchar(50),
supplier_id int references supplier(supplier_id),
product_target nvarchar(500),
product_name nvarchar(300),
dosage_form nvarchar(50),
product_specification nvarchar(50),
product_excipient nvarchar(1000),
product_desciption nvarchar(4000),
created_date bit default(0),
is_deleted bit default(0) not null
)
GO
create table price(
price_id int primary key identity(1,1),
product_id int references product(product_id) not null,
amount_money float not null,
unit_code varchar(50),
is_deleted bit default(0)
)
GO
create table product_image(
produc_image_id int primary key identity(1,1),
product_id int references product(product_id),
image_base64 varchar(max),
is_main bit default(1),
is_deleted bit default(0)
)

