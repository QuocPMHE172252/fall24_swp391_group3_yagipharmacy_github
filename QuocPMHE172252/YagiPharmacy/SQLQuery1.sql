use master
drop database if exists yagi_pharmacy
create database yagi_pharmacy
use yagi_pharmacy
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
create table [staff](
staff_id int primary key identity(1,1),
[user_id] int references [user]([user_id]) unique, 
staff_major varchar(500),
staff_degree varchar(200),
staff_education nvarchar(4000),
staff_experience nvarchar(4000),
staff_description nvarchar(4000),
gender bit default(1),
is_deleted bit default(0) not null
)
create table news_category(
news_category_id int primary key identity(1,1),
news_category_parent_id int,
news_category_level int,
news_category_name nvarchar(200),
news_category_detail nvarchar(500),
news_img ntext,
is_delete bit default(0)
)
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
view_count int,
is_deleted bit default(0)
)
create table product_category(
product_category_id int primary key identity(1,1),
product_category_parent_id int,
product_category_level int,
product_category_code varchar(50) not null unique,
product_category_name nvarchar(200),
product_category_detail nvarchar(500),
is_deleted bit default(0)
)
create table supplier(
supplier_id int primary key identity(1,1),
supplier_code nvarchar(200),
supplier_name nvarchar(200),
supplier_country_code varchar(50),
supplier_phone varchar(50),
supplier_email varchar(50),
is_deleted bit default(0)
)
create table [product](
product_id int primary key identity(1,1),
product_code varchar(500) unique,
authorId int references [user]([user_id]),
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
create table excipient(
excipient_id int primary key identity(1,1),
excipient_name nvarchar(300),
is_deleted bit default(0)
)
create table unit(
unit_id int primary key identity(1,1),
unit_name nvarchar(200)
)
create table product_excipient(
product_excipient_id int primary key identity(1,1),
product_id int references [product](product_id),
excipient_id int references excipient(excipient_id),
quantity float,
unit_measurement nvarchar(400)
)
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
create table product_image(
product_image_id int primary key identity(1,1),
product_id int references [product](product_id),
image_base64 varchar(max),
is_main bit default(1),
is_deleted bit default(0)
)
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
create table sale_order_detail(
order_detail_id int primary key identity(1,1),
sale_order_id int references sale_order(sale_order_id),
product_id int references [product](product_id),
unit_id int references unit(unit_id),
quantity int,
batch_code varchar(50),
is_deleted bit default(0)
)
create table sale_revenue(
sale_revenue_id int primary key identity(1,1),
product_id int references [product](product_id),
total_order int,
total_revenue float,
is_deleted bit default(0),
)
-- Thêm các thành phần thuốc
INSERT INTO excipient (excipient_name) VALUES
(N'Cellulose vi tinh thể'),
(N'Lactose'),
(N'Tinh bột ngô'),
(N'Magnesi stearat'),
(N'Silica keo'),
(N'Talc'),
(N'Gelatin'),
(N'Croscarmellose sodium'),
(N'Polyvinylpyrrolidon (PVP)'),
(N'Hydroxypropyl methylcellulose (HPMC)'),
(N'Ethylcellulose'),
(N'Dextrin'),
(N'Mannitol'),
(N'Sodium lauryl sulfate'),
(N'Dicalcium phosphate'),
(N'Sucrose'),
(N'Polyethylene glycol (PEG)'),
(N'Povidone'),
(N'Acid citric'),
(N'Sodium bicarbonate'),
(N'Paracetamol'),
(N'Ibuprofen'),
(N'Amoxicillin'),
(N'Aspirin'),
(N'Ciprofloxacin'),
(N'Atorvastatin'),
(N'Metformin'),
(N'Omeprazole'),
(N'Simvastatin'),
(N'Prednisone'),
(N'Lisinopril'),
(N'Amlodipine'),
(N'Dexamethasone'),
(N'Cetirizine'),
(N'Salbutamol (Albuterol)'),
(N'Metronidazole'),
(N'Clopidogrel'),
(N'Furosemide'),
(N'Losartan'),
(N'Levothyroxine');
-- Thêm các danh mục cấp 1 (cấp cha)
INSERT INTO product_category (product_category_parent_id, product_category_level, product_category_code, product_category_name, product_category_detail) VALUES
(NULL, 1, 'MED001', N'Thuốc', N'Danh mục các loại thuốc điều trị và hỗ trợ sức khỏe'),
(NULL, 1, 'SUPP001', N'Thực phẩm chức năng', N'Danh mục các loại thực phẩm chức năng hỗ trợ sức khỏe'),
(NULL, 1, 'CARE001', N'Sản phẩm chăm sóc cơ thể', N'Danh mục các sản phẩm chăm sóc cá nhân và cơ thể');
-- Thêm các danh mục cấp 2 (con của các danh mục cấp 1)
INSERT INTO product_category (product_category_parent_id, product_category_level, product_category_code, product_category_name, product_category_detail) VALUES
(1, 2, 'MED001001', N'Thuốc giảm đau', N'Danh mục các loại thuốc giảm đau và kháng viêm'),
(1, 2, 'MED001002', N'Thuốc kháng sinh', N'Danh mục các loại thuốc kháng sinh và chống nhiễm khuẩn'),
(1, 2, 'MED001003', N'Thuốc hạ sốt', N'Danh mục các loại thuốc hạ sốt'),
(1, 2, 'MED001004', N'Thuốc điều trị huyết áp', N'Danh mục các loại thuốc điều trị huyết áp'),
(1, 2, 'MED001005', N'Thuốc dạ dày', N'Danh mục các loại thuốc điều trị bệnh dạ dày'),
(2, 2, 'SUPP001001', N'Tăng cường hệ miễn dịch', N'Danh mục thực phẩm chức năng tăng cường hệ miễn dịch'),
(2, 2, 'SUPP001002', N'Hỗ trợ tiêu hóa', N'Danh mục thực phẩm chức năng hỗ trợ tiêu hóa'),
(2, 2, 'SUPP001003', N'Hỗ trợ xương khớp', N'Danh mục thực phẩm chức năng hỗ trợ xương khớp'),
(2, 2, 'SUPP001004', N'Hỗ trợ tim mạch', N'Danh mục thực phẩm chức năng hỗ trợ tim mạch'),
(3, 2, 'CARE001001', N'Sản phẩm chăm sóc da', N'Danh mục các sản phẩm chăm sóc và bảo vệ da'),
(3, 2, 'CARE001002', N'Sản phẩm chăm sóc tóc', N'Danh mục các sản phẩm chăm sóc tóc'),
(3, 2, 'CARE001003', N'Sản phẩm vệ sinh cá nhân', N'Danh mục các sản phẩm vệ sinh cá nhân');
-- Thêm các danh mục cấp 3 (con của các danh mục cấp 2)
INSERT INTO product_category (product_category_parent_id, product_category_level, product_category_code, product_category_name, product_category_detail) VALUES
(4, 3, 'MED001001001', N'Thuốc giảm đau đầu', N'Thuốc giảm đau đầu và giảm căng thẳng'),
(4, 3, 'MED001001002', N'Thuốc giảm đau khớp', N'Thuốc giảm đau khớp và giảm viêm'),
(7, 3, 'SUPP001002001', N'Men vi sinh', N'Thực phẩm chức năng bổ sung men vi sinh cho đường ruột'),
(7, 3, 'SUPP001002002', N'Chất xơ', N'Thực phẩm chức năng bổ sung chất xơ hỗ trợ tiêu hóa'),
(10, 3, 'CARE001001001', N'Kem dưỡng ẩm', N'Các loại kem dưỡng ẩm cho da'),
(10, 3, 'CARE001001002', N'Kem chống nắng', N'Các sản phẩm kem chống nắng bảo vệ da');
-- Thêm danh sách đơn vị
INSERT INTO unit (unit_name) VALUES 
(N'Thùng'),
(N'Hộp'),
(N'Lọ'),
(N'Vỉ'),
(N'Viên'),
(N'Ống');
-- Thêm nhà cung cấp
INSERT INTO supplier (supplier_code, supplier_name, supplier_country_code, supplier_phone, supplier_email, is_deleted) VALUES
('SUP001', N'Công ty Dược Á Đông', 'VN', '0912345678', 'abc@duoc.com', 0),
('SUP002', N'Công ty Dược Việt Đức', 'VN', '0923456789', 'xyz@duoc.com', 0),
('SUP003', N'Công ty Dược Thiên Tân', 'US', '0934567890', 'def@duoc.com', 0),
('SUP004', N'Công ty Dược Chida Pharma', 'JP', '0945678901', 'ghi@duoc.com', 0),
('SUP005', N'Công ty Dược Shiu Chen Yang', 'CN', '0956789012', 'jkl@duoc.com', 0);


SELECT DISTINCT product_target FROM product;
