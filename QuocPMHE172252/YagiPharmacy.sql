USE [master]
GO
/****** Object:  Database [yagi_pharmacy]    Script Date: 9/22/2024 11:21:41 PM ******/
CREATE DATABASE [yagi_pharmacy]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'yagi_pharmacy', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.QUOCPM250203\MSSQL\DATA\yagi_pharmacy.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'yagi_pharmacy_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.QUOCPM250203\MSSQL\DATA\yagi_pharmacy_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [yagi_pharmacy] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [yagi_pharmacy].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [yagi_pharmacy] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET ARITHABORT OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [yagi_pharmacy] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [yagi_pharmacy] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET  ENABLE_BROKER 
GO
ALTER DATABASE [yagi_pharmacy] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [yagi_pharmacy] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET RECOVERY FULL 
GO
ALTER DATABASE [yagi_pharmacy] SET  MULTI_USER 
GO
ALTER DATABASE [yagi_pharmacy] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [yagi_pharmacy] SET DB_CHAINING OFF 
GO
ALTER DATABASE [yagi_pharmacy] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [yagi_pharmacy] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [yagi_pharmacy] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [yagi_pharmacy] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'yagi_pharmacy', N'ON'
GO
ALTER DATABASE [yagi_pharmacy] SET QUERY_STORE = ON
GO
ALTER DATABASE [yagi_pharmacy] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [yagi_pharmacy]
GO
/****** Object:  Table [dbo].[news]    Script Date: 9/22/2024 11:21:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[news](
	[news_id] [int] IDENTITY(1,1) NOT NULL,
	[news_category_id] [int] NULL,
	[creator_id] [int] NULL,
	[news_title] [nvarchar](200) NULL,
	[news_content] [nvarchar](max) NULL,
	[news_image] [varchar](max) NULL,
	[news_hashtag] [varchar](300) NULL,
	[updated_id] [int] NULL,
	[created_date] [varchar](500) NULL,
	[is_deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[news_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[news_category]    Script Date: 9/22/2024 11:21:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[news_category](
	[news_category_id] [int] IDENTITY(1,1) NOT NULL,
	[news_category_parent_id] [int] NULL,
	[news_category_level] [int] NULL,
	[news_category_name] [nvarchar](200) NULL,
	[news_category_detail] [nvarchar](500) NULL,
	[is_delete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[news_category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[price]    Script Date: 9/22/2024 11:21:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[price](
	[price_id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NOT NULL,
	[amount_money] [float] NOT NULL,
	[unit_code] [varchar](50) NULL,
	[is_deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[price_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 9/22/2024 11:21:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[product_id] [int] IDENTITY(1,1) NOT NULL,
	[product_code] [varchar](500) NULL,
	[product_category_id] [int] NULL,
	[product_country_code] [varchar](50) NULL,
	[supplier_id] [int] NULL,
	[product_target] [nvarchar](500) NULL,
	[product_name] [nvarchar](300) NULL,
	[dosage_form] [nvarchar](50) NULL,
	[product_specification] [nvarchar](50) NULL,
	[product_excipient] [nvarchar](1000) NULL,
	[product_desciption] [nvarchar](4000) NULL,
	[created_date] [bit] NULL,
	[is_deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[product_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_category]    Script Date: 9/22/2024 11:21:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_category](
	[product_category_id] [int] IDENTITY(1,1) NOT NULL,
	[product_category_parent_id] [int] NULL,
	[product_category_level] [int] NULL,
	[product_category_code] [varchar](50) NOT NULL,
	[product_category_name] [varchar](200) NULL,
	[product_category_detail] [nvarchar](500) NULL,
	[is_deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[product_category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product_image]    Script Date: 9/22/2024 11:21:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product_image](
	[produc_image_id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NULL,
	[image_base64] [varchar](max) NULL,
	[is_main] [bit] NULL,
	[is_deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[produc_image_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[staff]    Script Date: 9/22/2024 11:21:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[staff](
	[staff_id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[staff_major] [varchar](500) NULL,
	[staff_education] [nvarchar](4000) NULL,
	[staff_experience] [nvarchar](4000) NULL,
	[staff_description] [nvarchar](4000) NULL,
	[is_deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[staff_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[supplier]    Script Date: 9/22/2024 11:21:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[supplier](
	[supplier_id] [int] IDENTITY(1,1) NOT NULL,
	[supplier_code] [nvarchar](200) NULL,
	[supplier_name] [nvarchar](200) NULL,
	[supplier_country_code] [varchar](50) NULL,
	[is_deleted] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[supplier_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user]    Script Date: 9/22/2024 11:21:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
	[user_id] [int] IDENTITY(1,1) NOT NULL,
	[user_name] [nvarchar](50) NOT NULL,
	[user_phone] [varchar](50) NOT NULL,
	[user_email] [varchar](50) NULL,
	[user_password] [varchar](50) NOT NULL,
	[role_level] [int] NULL,
	[user_avatar] [varchar](max) NULL,
	[user_bank] [varchar](30) NULL,
	[user_bank_code] [varchar](100) NULL,
	[user_province] [nvarchar](100) NULL,
	[user_district] [nvarchar](100) NULL,
	[user_commune] [nvarchar](100) NULL,
	[specific_address] [nvarchar](400) NULL,
	[date_of_birth] [varchar](500) NULL,
	[created_date] [varchar](500) NOT NULL,
	[active_code] [varchar](10) NOT NULL,
	[is_active] [bit] NOT NULL,
	[is_deleted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[news] ON 
GO
INSERT [dbo].[news] ([news_id], [news_category_id], [creator_id], [news_title], [news_content], [news_image], [news_hashtag], [updated_id], [created_date], [is_deleted]) VALUES (4, 1, 1, N'Global Summit 2024: Leaders Discuss Climate Action', N'<div class="inner "><div class="ingredient" id="detail-content-0"><h2 class="css-8uyn92">Thành phần của Thuốc bột Smecta Beaufour</h2><div class="mt-2 md:mt-4"><table class="w-full max-w-[700px] border-spacing-1"><thead class="border-spacing-1"><tr class="rounded"><th class="bg-gray-4 w-[60%] border-[2px] border-[#fff] p-2 text-left" style="border-bottom-left-radius: 10px; border-top-left-radius: 10px;"><p class="css-15sc8tc text-gray-10">Thông tin thành phần</p></th><th class="bg-gray-4 w-[40%] border-[2px] border-[#fff] p-2 text-right" style="border-bottom-right-radius: 10px; border-top-right-radius: 10px;"><p class="css-15sc8tc text-gray-10">Hàm lượng</p></th></tr></thead><tbody class="border-spacing-1"><tr class="rounded"><td class="border-[2px] border-[#fff] bg-[#edf0f3] p-2 text-left" style="border-bottom-left-radius: 10px; border-top-left-radius: 10px;"><p class="css-pqr9s7 text-gray-10">Diosmectite</p></td><td class="border-[2px] border-[#fff] bg-[#edf0f3] p-2 text-right" style="border-bottom-right-radius: 10px; border-top-right-radius: 10px;"><p class="css-pqr9s7 text-gray-10">3g</p></td></tr></tbody></table></div><div></div></div><div class="usage" id="detail-content-1"><h2 class="css-8uyn92">Công dụng của Thuốc bột Smecta Beaufour</h2><div><h3>Chỉ định</h3><p>Dung dịch uống Smecta chỉ định điều trị trong các trường hợp sau:</p><ul><li>Điều trị các triệu chứng trong tiêu chảy cấp ở trẻ em trên 2 tuổi và người lớn, kết hợp với việc bổ sung nước và các chất điện giải đường uống.</li><li>Điều trị các triệu chứng trong tiêu chảy mạn tính.</li><li>Điều trị triệu chứng các chứng đau liên quan tới rối loạn chức năng ruột ở người lớn.</li></ul><h3>Dược lực học</h3><p>Diosmectite đã được chứng minh hấp phụ hơi trong đường ruột ở người lớn, phục hồi tính thấm của niêm mạc đường tiêu hóa trở về bình thường ở trẻ em bị tiêu chảy cấp.</p><p>Do cấu trúc từng lớp với độ nhầy cao, có khả năng bao phủ mạnh nên Smecta có tác dụng bảo vệ niêm mạc đường tiêu hóa.</p><p>Kết quả phối hợp của 2 nghiên cứu ngẫu nhiên mù đôi so sánh hiệu quả của Smecta với giả dược trên 602 bệnh nhân từ 1 - 36 tháng tuổi bị tiêu chảy cấp cho thấy lượng phân trong 72 giờ đầu giảm đáng kể ở nhóm điều trị bằng Smecta cùng với bù nước đường uống.</p><h3>Dược động học</h3><p>Smecta không bị hấp thu hay chuyển hóa, được đào thải qua phân theo nhu động bình thường của ruột.</p></div></div><div class="dosage" id="detail-content-2"><h2 class="css-8uyn92">Cách dùng Thuốc bột Smecta Beaufour</h2><div><h3>Cách dùng</h3><p>Dung dịch uống Smecta có thể pha với nước thành hỗn dịch đựng trong bình để trẻ uống dần trong ngày, hoặc trộn với thức ăn lỏng, như nước dùng, rau củ nghiền, mứt quả, thức ăn trẻ em...</p><h3>Liều dùng</h3><p><strong>Điều trị tiêu chảy cấp:</strong></p><p><i>Trẻ em trên 2 tuổi:</i></p><p>4 gói/ngày trong 3 ngày đầu. Sau đó 2 gói/ngày trong 4 ngày.</p><p><i>Người lớn:</i></p><p>Trung bình 3 gói/ngày trong 7 ngày. Trên thực tế, liều hàng ngày có thể tăng gấp đôi trong những ngày đầu điều trị.</p><p><strong>Chỉ định khác:</strong></p><p><i>Người lớn:</i></p><p>Trung bình 3 gói/ngày.</p><p>Lưu ý: Liều dùng trên chỉ mang tính chất tham khảo. Liều dùng cụ thể tùy thuộc vào thể trạng và mức độ diễn tiến của bệnh. Để có liều dùng phù hợp, bạn cần tham khảo ý kiến bác sĩ hoặc chuyên viên y tế.</p><h3>Làm gì khi dùng quá liều?</h3><p>Quá liều có thể dẫn đến táo bón nặng hoặc dị vật dạ dày.</p><p>Trong trường hợp khẩn cấp, hãy gọi ngay cho Trung tâm cấp cứu 115 hoặc đến trạm Y tế địa phương gần nhất.</p><h3>Làm gì khi quên 1 liều?</h3><p>Nếu quên dùng một liều, hãy uống càng sớm càng tốt khi nhớ ra. Tuy nhiên, nếu gần với liều kế tiếp, hãy bỏ qua liều đã quên và uống liều kế tiếp vào thời điểm như kế hoạch. Không uống gấp đôi liều đã quy định.</p></div></div><div class="adverseEffect" id="detail-content-3"><h2 class="css-8uyn92">Tác dụng phụ </h2><div><p>Phản ứng bất lợi thường gặp nhất trong khi điều trị là táo bón, với tỷ lệ khoảng 7% ở người lớn và 1% ở trẻ em. Trong trường hợp táo bón xảy ra, nên ngừng dùng diosmectite. Nếu xét thấy việc dùng diosmectite là cần thiết thì nên khởi đầu lại với liều thấp.</p><p>Dưới đây là bảng liệt kê các phản ứng bất lợi của thuốc đã được báo cáo từ các nghiên cứu lâm sàng và sau khi lưu hành ngoài thị trường. Tần suất được xác định dựa trên phân loại sau: Rất thường xuyên (≥1/10); thường xuyên (≥1/100 đến &lt;1/10); không thường xuyên (≥1/1.000 đến &lt;1/100); hiếm (≥1/10.000 đến &lt;1/1.000); rất hiếm (&lt;1/10.000); không rõ (không thể ước lượng từ các số liệu sẵn có).</p><p>Phản ứng bất lợi được ghi nhận từ các nghiên cứu lâm sàng &amp; sau khi lưu hành ngoài thị trường.</p><figure style="width:100%;" class="table"><table class="ck-table-resized"><colgroup><col style="width:25.22%;"><col style="width:37.73%;"><col style="width:37.05%;"></colgroup><thead><tr><th>Hệ thống cơ quan</th><th>Tần suất</th><th>Phản ứng bất lợi</th></tr></thead><tbody><tr><td rowspan="2">Rối loạn đường tiêu hóa</td><td>Thường xuyên</td><td>Táo bón</td></tr><tr><td>Không thường xuyên</td><td>Nôn</td></tr><tr><td rowspan="3">Rối loạn da và mô dưới da</td><td>Không thường xuyên</td><td>Nổi ban</td></tr><tr><td>Hiếm</td><td>Mày đay</td></tr><tr><td>Không rõ</td><td>Phù mạch, ngứa</td></tr><tr><td>Rối loạn hệ thống miễn dịch</td><td>Không rõ</td><td>Quá mẫn</td></tr></tbody></table></figure></div></div><div class="careful" id="detail-content-4"><h2 class="css-8uyn92">Lưu ý </h2><div><p>Trước khi sử dụng thuốc bạn cần đọc kỹ hướng dẫn sử dụng và tham khảo thông tin bên dưới.</p><h3>Chống chỉ định</h3><p>Thuốc Smecta chống chỉ định trong trường hợp sau:</p><ul><li>Dị ứng với Diosmectite hay bất kỳ thành phần nào của thuốc.</li></ul><h3>Thận trọng khi sử dụng</h3><p>Diosmectite phải được dùng thận trọng ở bệnh nhân có tiền sử táo bón nặng.</p><p>Ở trẻ nhũ nhi và trẻ dưới 2 tuổi, nên tránh dùng Smecta. Điều trị khuyến cáo trong tiêu chảy là bù nước và điện giải đường uống (ORS).</p><p>Ở trẻ em trên 2 tuổi, tiêu chảy cấp phải được điều trị phối hợp với việc dùng sớm dung dịch bù nước, điện giải đường uống (ORS) nhằm tránh mất nước và các chất điện giải. Nên tránh dùng Smecta lâu dài.</p><p>Ở người lớn, điều trị không được bỏ qua việc bù nước và các chất điện giải nếu điều này là cần thiết.</p><p>Lượng dịch cần bù, bằng đường uống hoặc đường tĩnh mạch, phải được điều chỉnh phù hợp với mức độ tiêu chảy, tuổi và đặc điểm của bệnh nhân.</p><p>Bệnh nhân nên được thông báo về việc cần thiết phải:</p><ul><li>Uống nhiều dịch mặn hoặc ngọt, để bồi hoàn lượng dịch mất do tiêu chảy (nhu cầu lượng dịch trung bình hàng ngày ở người lớn là 2 lít).</li></ul><p>Chế độ ăn khi bị tiêu chảy:</p><ul><li>Nên tránh: Rau sống, trái cây, rau xanh, các món ăn nhiều gia vị, thức ăn đông lạnh.</li><li>Món ăn thích hợp là thịt nướng và cơm.</li></ul><p>Thuốc chứa glucose và saccharose khuyến cáo không nên dùng cho bệnh nhân có rối loạn dung nạp fructose, hấp thu kém glucose và galactose hoặc những bệnh nhân thiếu enzym tiêu hóa sucrose và isomaltase.</p><p>Thuốc chứa một lượng nhỏ ethanol (cồn), với lượng thấp hơn 100 mg/liều hàng ngày.</p><h3>Khả năng lái xe và vận hành máy móc</h3><p>Chưa có nghiên cứu trên khả năng lái xe và vận hành máy móc của thuốc này. Tuy nhiên thuốc được cho là không có tác động hoặc tác động không đáng kể lên khả năng lái xe và vận hành máy móc.</p><h3>Thời kỳ mang thai</h3><p>Không có dữ liệu hoặc có dữ liệu có giới hạn (dưới 300 phụ nữ có thai) dùng Smecta trong quá trình mang thai. Các nghiên cứu trên động vật không đủ để kết luận độc tính sinh sản. Smecta không khuyến cáo sử dụng cho phụ nữ có thai.</p><h3>Thời kỳ cho con bú</h3><p>Dữ liệu giới hạn về việc dùng Smecta trên phụ nữ cho con bú. Smecta không khuyến cáo sử dụng cho phụ nữ cho con bú.</p><h3>Tương tác thuốc</h3><p>Đặc tính hấp phụ của Smecta có thể tác động vào thời gian và/hoặc tỉ lệ hấp thu các chất khác, vì vậy khuyến cáo không nên dùng cùng lúc với các thuốc khác (nên dùng cách xa 2 giờ).</p></div></div><div class="preservation" id="detail-content-5"><h2 class="css-8uyn92">Bảo quản </h2><div><p>Bảo quản ở nhiệt độ không quá 30<sup>o</sup>C</p></div></div></div>', N'https://nonprod-cdn.nhathuoclongchau.com.vn/unsafe/https://cms-nonprod.s3-sgn09.fptcloud.com/449166525_987399509618032_3266935831396834348_n_58116f8cb1.png', N'#ClimateChange #GlobalSummit2024 #Sustainability', 2, N'2024-09-20', 0)
GO
INSERT [dbo].[news] ([news_id], [news_category_id], [creator_id], [news_title], [news_content], [news_image], [news_hashtag], [updated_id], [created_date], [is_deleted]) VALUES (5, 5, 1, N'AI Revolution: How Robotics are Transforming Healthcare', N'<div class="inner "><div class="ingredient" id="detail-content-0"><h2 class="css-8uyn92">Thành phần của Thuốc bột Smecta Beaufour</h2><div class="mt-2 md:mt-4"><table class="w-full max-w-[700px] border-spacing-1"><thead class="border-spacing-1"><tr class="rounded"><th class="bg-gray-4 w-[60%] border-[2px] border-[#fff] p-2 text-left" style="border-bottom-left-radius: 10px; border-top-left-radius: 10px;"><p class="css-15sc8tc text-gray-10">Thông tin thành phần</p></th><th class="bg-gray-4 w-[40%] border-[2px] border-[#fff] p-2 text-right" style="border-bottom-right-radius: 10px; border-top-right-radius: 10px;"><p class="css-15sc8tc text-gray-10">Hàm lượng</p></th></tr></thead><tbody class="border-spacing-1"><tr class="rounded"><td class="border-[2px] border-[#fff] bg-[#edf0f3] p-2 text-left" style="border-bottom-left-radius: 10px; border-top-left-radius: 10px;"><p class="css-pqr9s7 text-gray-10">Diosmectite</p></td><td class="border-[2px] border-[#fff] bg-[#edf0f3] p-2 text-right" style="border-bottom-right-radius: 10px; border-top-right-radius: 10px;"><p class="css-pqr9s7 text-gray-10">3g</p></td></tr></tbody></table></div><div></div></div><div class="usage" id="detail-content-1"><h2 class="css-8uyn92">Công dụng của Thuốc bột Smecta Beaufour</h2><div><h3>Chỉ định</h3><p>Dung dịch uống Smecta chỉ định điều trị trong các trường hợp sau:</p><ul><li>Điều trị các triệu chứng trong tiêu chảy cấp ở trẻ em trên 2 tuổi và người lớn, kết hợp với việc bổ sung nước và các chất điện giải đường uống.</li><li>Điều trị các triệu chứng trong tiêu chảy mạn tính.</li><li>Điều trị triệu chứng các chứng đau liên quan tới rối loạn chức năng ruột ở người lớn.</li></ul><h3>Dược lực học</h3><p>Diosmectite đã được chứng minh hấp phụ hơi trong đường ruột ở người lớn, phục hồi tính thấm của niêm mạc đường tiêu hóa trở về bình thường ở trẻ em bị tiêu chảy cấp.</p><p>Do cấu trúc từng lớp với độ nhầy cao, có khả năng bao phủ mạnh nên Smecta có tác dụng bảo vệ niêm mạc đường tiêu hóa.</p><p>Kết quả phối hợp của 2 nghiên cứu ngẫu nhiên mù đôi so sánh hiệu quả của Smecta với giả dược trên 602 bệnh nhân từ 1 - 36 tháng tuổi bị tiêu chảy cấp cho thấy lượng phân trong 72 giờ đầu giảm đáng kể ở nhóm điều trị bằng Smecta cùng với bù nước đường uống.</p><h3>Dược động học</h3><p>Smecta không bị hấp thu hay chuyển hóa, được đào thải qua phân theo nhu động bình thường của ruột.</p></div></div><div class="dosage" id="detail-content-2"><h2 class="css-8uyn92">Cách dùng Thuốc bột Smecta Beaufour</h2><div><h3>Cách dùng</h3><p>Dung dịch uống Smecta có thể pha với nước thành hỗn dịch đựng trong bình để trẻ uống dần trong ngày, hoặc trộn với thức ăn lỏng, như nước dùng, rau củ nghiền, mứt quả, thức ăn trẻ em...</p><h3>Liều dùng</h3><p><strong>Điều trị tiêu chảy cấp:</strong></p><p><i>Trẻ em trên 2 tuổi:</i></p><p>4 gói/ngày trong 3 ngày đầu. Sau đó 2 gói/ngày trong 4 ngày.</p><p><i>Người lớn:</i></p><p>Trung bình 3 gói/ngày trong 7 ngày. Trên thực tế, liều hàng ngày có thể tăng gấp đôi trong những ngày đầu điều trị.</p><p><strong>Chỉ định khác:</strong></p><p><i>Người lớn:</i></p><p>Trung bình 3 gói/ngày.</p><p>Lưu ý: Liều dùng trên chỉ mang tính chất tham khảo. Liều dùng cụ thể tùy thuộc vào thể trạng và mức độ diễn tiến của bệnh. Để có liều dùng phù hợp, bạn cần tham khảo ý kiến bác sĩ hoặc chuyên viên y tế.</p><h3>Làm gì khi dùng quá liều?</h3><p>Quá liều có thể dẫn đến táo bón nặng hoặc dị vật dạ dày.</p><p>Trong trường hợp khẩn cấp, hãy gọi ngay cho Trung tâm cấp cứu 115 hoặc đến trạm Y tế địa phương gần nhất.</p><h3>Làm gì khi quên 1 liều?</h3><p>Nếu quên dùng một liều, hãy uống càng sớm càng tốt khi nhớ ra. Tuy nhiên, nếu gần với liều kế tiếp, hãy bỏ qua liều đã quên và uống liều kế tiếp vào thời điểm như kế hoạch. Không uống gấp đôi liều đã quy định.</p></div></div><div class="adverseEffect" id="detail-content-3"><h2 class="css-8uyn92">Tác dụng phụ </h2><div><p>Phản ứng bất lợi thường gặp nhất trong khi điều trị là táo bón, với tỷ lệ khoảng 7% ở người lớn và 1% ở trẻ em. Trong trường hợp táo bón xảy ra, nên ngừng dùng diosmectite. Nếu xét thấy việc dùng diosmectite là cần thiết thì nên khởi đầu lại với liều thấp.</p><p>Dưới đây là bảng liệt kê các phản ứng bất lợi của thuốc đã được báo cáo từ các nghiên cứu lâm sàng và sau khi lưu hành ngoài thị trường. Tần suất được xác định dựa trên phân loại sau: Rất thường xuyên (≥1/10); thường xuyên (≥1/100 đến &lt;1/10); không thường xuyên (≥1/1.000 đến &lt;1/100); hiếm (≥1/10.000 đến &lt;1/1.000); rất hiếm (&lt;1/10.000); không rõ (không thể ước lượng từ các số liệu sẵn có).</p><p>Phản ứng bất lợi được ghi nhận từ các nghiên cứu lâm sàng &amp; sau khi lưu hành ngoài thị trường.</p><figure style="width:100%;" class="table"><table class="ck-table-resized"><colgroup><col style="width:25.22%;"><col style="width:37.73%;"><col style="width:37.05%;"></colgroup><thead><tr><th>Hệ thống cơ quan</th><th>Tần suất</th><th>Phản ứng bất lợi</th></tr></thead><tbody><tr><td rowspan="2">Rối loạn đường tiêu hóa</td><td>Thường xuyên</td><td>Táo bón</td></tr><tr><td>Không thường xuyên</td><td>Nôn</td></tr><tr><td rowspan="3">Rối loạn da và mô dưới da</td><td>Không thường xuyên</td><td>Nổi ban</td></tr><tr><td>Hiếm</td><td>Mày đay</td></tr><tr><td>Không rõ</td><td>Phù mạch, ngứa</td></tr><tr><td>Rối loạn hệ thống miễn dịch</td><td>Không rõ</td><td>Quá mẫn</td></tr></tbody></table></figure></div></div><div class="careful" id="detail-content-4"><h2 class="css-8uyn92">Lưu ý </h2><div><p>Trước khi sử dụng thuốc bạn cần đọc kỹ hướng dẫn sử dụng và tham khảo thông tin bên dưới.</p><h3>Chống chỉ định</h3><p>Thuốc Smecta chống chỉ định trong trường hợp sau:</p><ul><li>Dị ứng với Diosmectite hay bất kỳ thành phần nào của thuốc.</li></ul><h3>Thận trọng khi sử dụng</h3><p>Diosmectite phải được dùng thận trọng ở bệnh nhân có tiền sử táo bón nặng.</p><p>Ở trẻ nhũ nhi và trẻ dưới 2 tuổi, nên tránh dùng Smecta. Điều trị khuyến cáo trong tiêu chảy là bù nước và điện giải đường uống (ORS).</p><p>Ở trẻ em trên 2 tuổi, tiêu chảy cấp phải được điều trị phối hợp với việc dùng sớm dung dịch bù nước, điện giải đường uống (ORS) nhằm tránh mất nước và các chất điện giải. Nên tránh dùng Smecta lâu dài.</p><p>Ở người lớn, điều trị không được bỏ qua việc bù nước và các chất điện giải nếu điều này là cần thiết.</p><p>Lượng dịch cần bù, bằng đường uống hoặc đường tĩnh mạch, phải được điều chỉnh phù hợp với mức độ tiêu chảy, tuổi và đặc điểm của bệnh nhân.</p><p>Bệnh nhân nên được thông báo về việc cần thiết phải:</p><ul><li>Uống nhiều dịch mặn hoặc ngọt, để bồi hoàn lượng dịch mất do tiêu chảy (nhu cầu lượng dịch trung bình hàng ngày ở người lớn là 2 lít).</li></ul><p>Chế độ ăn khi bị tiêu chảy:</p><ul><li>Nên tránh: Rau sống, trái cây, rau xanh, các món ăn nhiều gia vị, thức ăn đông lạnh.</li><li>Món ăn thích hợp là thịt nướng và cơm.</li></ul><p>Thuốc chứa glucose và saccharose khuyến cáo không nên dùng cho bệnh nhân có rối loạn dung nạp fructose, hấp thu kém glucose và galactose hoặc những bệnh nhân thiếu enzym tiêu hóa sucrose và isomaltase.</p><p>Thuốc chứa một lượng nhỏ ethanol (cồn), với lượng thấp hơn 100 mg/liều hàng ngày.</p><h3>Khả năng lái xe và vận hành máy móc</h3><p>Chưa có nghiên cứu trên khả năng lái xe và vận hành máy móc của thuốc này. Tuy nhiên thuốc được cho là không có tác động hoặc tác động không đáng kể lên khả năng lái xe và vận hành máy móc.</p><h3>Thời kỳ mang thai</h3><p>Không có dữ liệu hoặc có dữ liệu có giới hạn (dưới 300 phụ nữ có thai) dùng Smecta trong quá trình mang thai. Các nghiên cứu trên động vật không đủ để kết luận độc tính sinh sản. Smecta không khuyến cáo sử dụng cho phụ nữ có thai.</p><h3>Thời kỳ cho con bú</h3><p>Dữ liệu giới hạn về việc dùng Smecta trên phụ nữ cho con bú. Smecta không khuyến cáo sử dụng cho phụ nữ cho con bú.</p><h3>Tương tác thuốc</h3><p>Đặc tính hấp phụ của Smecta có thể tác động vào thời gian và/hoặc tỉ lệ hấp thu các chất khác, vì vậy khuyến cáo không nên dùng cùng lúc với các thuốc khác (nên dùng cách xa 2 giờ).</p></div></div><div class="preservation" id="detail-content-5"><h2 class="css-8uyn92">Bảo quản </h2><div><p>Bảo quản ở nhiệt độ không quá 30<sup>o</sup>C</p></div></div></div>', N'https://nonprod-cdn.nhathuoclongchau.com.vn/unsafe/https://cms-nonprod.s3-sgn09.fptcloud.com/449166525_987399509618032_3266935831396834348_n_58116f8cb1.png', N'#AI #Robotics #HealthcareInnovation', 2, N'2024-09-18', 0)
GO
INSERT [dbo].[news] ([news_id], [news_category_id], [creator_id], [news_title], [news_content], [news_image], [news_hashtag], [updated_id], [created_date], [is_deleted]) VALUES (6, 8, 1, N'Mental Health Awareness Month: Breaking the Stigma', N'<div class="inner "><div class="ingredient" id="detail-content-0"><h2 class="css-8uyn92">Thành phần của Thuốc bột Smecta Beaufour</h2><div class="mt-2 md:mt-4"><table class="w-full max-w-[700px] border-spacing-1"><thead class="border-spacing-1"><tr class="rounded"><th class="bg-gray-4 w-[60%] border-[2px] border-[#fff] p-2 text-left" style="border-bottom-left-radius: 10px; border-top-left-radius: 10px;"><p class="css-15sc8tc text-gray-10">Thông tin thành phần</p></th><th class="bg-gray-4 w-[40%] border-[2px] border-[#fff] p-2 text-right" style="border-bottom-right-radius: 10px; border-top-right-radius: 10px;"><p class="css-15sc8tc text-gray-10">Hàm lượng</p></th></tr></thead><tbody class="border-spacing-1"><tr class="rounded"><td class="border-[2px] border-[#fff] bg-[#edf0f3] p-2 text-left" style="border-bottom-left-radius: 10px; border-top-left-radius: 10px;"><p class="css-pqr9s7 text-gray-10">Diosmectite</p></td><td class="border-[2px] border-[#fff] bg-[#edf0f3] p-2 text-right" style="border-bottom-right-radius: 10px; border-top-right-radius: 10px;"><p class="css-pqr9s7 text-gray-10">3g</p></td></tr></tbody></table></div><div></div></div><div class="usage" id="detail-content-1"><h2 class="css-8uyn92">Công dụng của Thuốc bột Smecta Beaufour</h2><div><h3>Chỉ định</h3><p>Dung dịch uống Smecta chỉ định điều trị trong các trường hợp sau:</p><ul><li>Điều trị các triệu chứng trong tiêu chảy cấp ở trẻ em trên 2 tuổi và người lớn, kết hợp với việc bổ sung nước và các chất điện giải đường uống.</li><li>Điều trị các triệu chứng trong tiêu chảy mạn tính.</li><li>Điều trị triệu chứng các chứng đau liên quan tới rối loạn chức năng ruột ở người lớn.</li></ul><h3>Dược lực học</h3><p>Diosmectite đã được chứng minh hấp phụ hơi trong đường ruột ở người lớn, phục hồi tính thấm của niêm mạc đường tiêu hóa trở về bình thường ở trẻ em bị tiêu chảy cấp.</p><p>Do cấu trúc từng lớp với độ nhầy cao, có khả năng bao phủ mạnh nên Smecta có tác dụng bảo vệ niêm mạc đường tiêu hóa.</p><p>Kết quả phối hợp của 2 nghiên cứu ngẫu nhiên mù đôi so sánh hiệu quả của Smecta với giả dược trên 602 bệnh nhân từ 1 - 36 tháng tuổi bị tiêu chảy cấp cho thấy lượng phân trong 72 giờ đầu giảm đáng kể ở nhóm điều trị bằng Smecta cùng với bù nước đường uống.</p><h3>Dược động học</h3><p>Smecta không bị hấp thu hay chuyển hóa, được đào thải qua phân theo nhu động bình thường của ruột.</p></div></div><div class="dosage" id="detail-content-2"><h2 class="css-8uyn92">Cách dùng Thuốc bột Smecta Beaufour</h2><div><h3>Cách dùng</h3><p>Dung dịch uống Smecta có thể pha với nước thành hỗn dịch đựng trong bình để trẻ uống dần trong ngày, hoặc trộn với thức ăn lỏng, như nước dùng, rau củ nghiền, mứt quả, thức ăn trẻ em...</p><h3>Liều dùng</h3><p><strong>Điều trị tiêu chảy cấp:</strong></p><p><i>Trẻ em trên 2 tuổi:</i></p><p>4 gói/ngày trong 3 ngày đầu. Sau đó 2 gói/ngày trong 4 ngày.</p><p><i>Người lớn:</i></p><p>Trung bình 3 gói/ngày trong 7 ngày. Trên thực tế, liều hàng ngày có thể tăng gấp đôi trong những ngày đầu điều trị.</p><p><strong>Chỉ định khác:</strong></p><p><i>Người lớn:</i></p><p>Trung bình 3 gói/ngày.</p><p>Lưu ý: Liều dùng trên chỉ mang tính chất tham khảo. Liều dùng cụ thể tùy thuộc vào thể trạng và mức độ diễn tiến của bệnh. Để có liều dùng phù hợp, bạn cần tham khảo ý kiến bác sĩ hoặc chuyên viên y tế.</p><h3>Làm gì khi dùng quá liều?</h3><p>Quá liều có thể dẫn đến táo bón nặng hoặc dị vật dạ dày.</p><p>Trong trường hợp khẩn cấp, hãy gọi ngay cho Trung tâm cấp cứu 115 hoặc đến trạm Y tế địa phương gần nhất.</p><h3>Làm gì khi quên 1 liều?</h3><p>Nếu quên dùng một liều, hãy uống càng sớm càng tốt khi nhớ ra. Tuy nhiên, nếu gần với liều kế tiếp, hãy bỏ qua liều đã quên và uống liều kế tiếp vào thời điểm như kế hoạch. Không uống gấp đôi liều đã quy định.</p></div></div><div class="adverseEffect" id="detail-content-3"><h2 class="css-8uyn92">Tác dụng phụ </h2><div><p>Phản ứng bất lợi thường gặp nhất trong khi điều trị là táo bón, với tỷ lệ khoảng 7% ở người lớn và 1% ở trẻ em. Trong trường hợp táo bón xảy ra, nên ngừng dùng diosmectite. Nếu xét thấy việc dùng diosmectite là cần thiết thì nên khởi đầu lại với liều thấp.</p><p>Dưới đây là bảng liệt kê các phản ứng bất lợi của thuốc đã được báo cáo từ các nghiên cứu lâm sàng và sau khi lưu hành ngoài thị trường. Tần suất được xác định dựa trên phân loại sau: Rất thường xuyên (≥1/10); thường xuyên (≥1/100 đến &lt;1/10); không thường xuyên (≥1/1.000 đến &lt;1/100); hiếm (≥1/10.000 đến &lt;1/1.000); rất hiếm (&lt;1/10.000); không rõ (không thể ước lượng từ các số liệu sẵn có).</p><p>Phản ứng bất lợi được ghi nhận từ các nghiên cứu lâm sàng &amp; sau khi lưu hành ngoài thị trường.</p><figure style="width:100%;" class="table"><table class="ck-table-resized"><colgroup><col style="width:25.22%;"><col style="width:37.73%;"><col style="width:37.05%;"></colgroup><thead><tr><th>Hệ thống cơ quan</th><th>Tần suất</th><th>Phản ứng bất lợi</th></tr></thead><tbody><tr><td rowspan="2">Rối loạn đường tiêu hóa</td><td>Thường xuyên</td><td>Táo bón</td></tr><tr><td>Không thường xuyên</td><td>Nôn</td></tr><tr><td rowspan="3">Rối loạn da và mô dưới da</td><td>Không thường xuyên</td><td>Nổi ban</td></tr><tr><td>Hiếm</td><td>Mày đay</td></tr><tr><td>Không rõ</td><td>Phù mạch, ngứa</td></tr><tr><td>Rối loạn hệ thống miễn dịch</td><td>Không rõ</td><td>Quá mẫn</td></tr></tbody></table></figure></div></div><div class="careful" id="detail-content-4"><h2 class="css-8uyn92">Lưu ý </h2><div><p>Trước khi sử dụng thuốc bạn cần đọc kỹ hướng dẫn sử dụng và tham khảo thông tin bên dưới.</p><h3>Chống chỉ định</h3><p>Thuốc Smecta chống chỉ định trong trường hợp sau:</p><ul><li>Dị ứng với Diosmectite hay bất kỳ thành phần nào của thuốc.</li></ul><h3>Thận trọng khi sử dụng</h3><p>Diosmectite phải được dùng thận trọng ở bệnh nhân có tiền sử táo bón nặng.</p><p>Ở trẻ nhũ nhi và trẻ dưới 2 tuổi, nên tránh dùng Smecta. Điều trị khuyến cáo trong tiêu chảy là bù nước và điện giải đường uống (ORS).</p><p>Ở trẻ em trên 2 tuổi, tiêu chảy cấp phải được điều trị phối hợp với việc dùng sớm dung dịch bù nước, điện giải đường uống (ORS) nhằm tránh mất nước và các chất điện giải. Nên tránh dùng Smecta lâu dài.</p><p>Ở người lớn, điều trị không được bỏ qua việc bù nước và các chất điện giải nếu điều này là cần thiết.</p><p>Lượng dịch cần bù, bằng đường uống hoặc đường tĩnh mạch, phải được điều chỉnh phù hợp với mức độ tiêu chảy, tuổi và đặc điểm của bệnh nhân.</p><p>Bệnh nhân nên được thông báo về việc cần thiết phải:</p><ul><li>Uống nhiều dịch mặn hoặc ngọt, để bồi hoàn lượng dịch mất do tiêu chảy (nhu cầu lượng dịch trung bình hàng ngày ở người lớn là 2 lít).</li></ul><p>Chế độ ăn khi bị tiêu chảy:</p><ul><li>Nên tránh: Rau sống, trái cây, rau xanh, các món ăn nhiều gia vị, thức ăn đông lạnh.</li><li>Món ăn thích hợp là thịt nướng và cơm.</li></ul><p>Thuốc chứa glucose và saccharose khuyến cáo không nên dùng cho bệnh nhân có rối loạn dung nạp fructose, hấp thu kém glucose và galactose hoặc những bệnh nhân thiếu enzym tiêu hóa sucrose và isomaltase.</p><p>Thuốc chứa một lượng nhỏ ethanol (cồn), với lượng thấp hơn 100 mg/liều hàng ngày.</p><h3>Khả năng lái xe và vận hành máy móc</h3><p>Chưa có nghiên cứu trên khả năng lái xe và vận hành máy móc của thuốc này. Tuy nhiên thuốc được cho là không có tác động hoặc tác động không đáng kể lên khả năng lái xe và vận hành máy móc.</p><h3>Thời kỳ mang thai</h3><p>Không có dữ liệu hoặc có dữ liệu có giới hạn (dưới 300 phụ nữ có thai) dùng Smecta trong quá trình mang thai. Các nghiên cứu trên động vật không đủ để kết luận độc tính sinh sản. Smecta không khuyến cáo sử dụng cho phụ nữ có thai.</p><h3>Thời kỳ cho con bú</h3><p>Dữ liệu giới hạn về việc dùng Smecta trên phụ nữ cho con bú. Smecta không khuyến cáo sử dụng cho phụ nữ cho con bú.</p><h3>Tương tác thuốc</h3><p>Đặc tính hấp phụ của Smecta có thể tác động vào thời gian và/hoặc tỉ lệ hấp thu các chất khác, vì vậy khuyến cáo không nên dùng cùng lúc với các thuốc khác (nên dùng cách xa 2 giờ).</p></div></div><div class="preservation" id="detail-content-5"><h2 class="css-8uyn92">Bảo quản </h2><div><p>Bảo quản ở nhiệt độ không quá 30<sup>o</sup>C</p></div></div></div>', N'https://nonprod-cdn.nhathuoclongchau.com.vn/unsafe/https://cms-nonprod.s3-sgn09.fptcloud.com/449166525_987399509618032_3266935831396834348_n_58116f8cb1.png', N'#MentalHealth #AwarenessMonth #BreakTheStigma', 2, N'2024-09-15', 0)
GO
SET IDENTITY_INSERT [dbo].[news] OFF
GO
SET IDENTITY_INSERT [dbo].[news_category] ON 
GO
INSERT [dbo].[news_category] ([news_category_id], [news_category_parent_id], [news_category_level], [news_category_name], [news_category_detail], [is_delete]) VALUES (1, NULL, 1, N'World News', N'Latest updates on international affairs and world events.', 0)
GO
INSERT [dbo].[news_category] ([news_category_id], [news_category_parent_id], [news_category_level], [news_category_name], [news_category_detail], [is_delete]) VALUES (2, 1, 2, N'Politics', N'News and analysis on political events and decisions.', 0)
GO
INSERT [dbo].[news_category] ([news_category_id], [news_category_parent_id], [news_category_level], [news_category_name], [news_category_detail], [is_delete]) VALUES (3, 1, 2, N'Economy', N'Global economy and market news.', 0)
GO
INSERT [dbo].[news_category] ([news_category_id], [news_category_parent_id], [news_category_level], [news_category_name], [news_category_detail], [is_delete]) VALUES (4, 1, 2, N'Environment', N'News on climate change, conservation, and sustainability.', 0)
GO
INSERT [dbo].[news_category] ([news_category_id], [news_category_parent_id], [news_category_level], [news_category_name], [news_category_detail], [is_delete]) VALUES (5, NULL, 1, N'Technology', N'Latest developments in the tech industry.', 0)
GO
INSERT [dbo].[news_category] ([news_category_id], [news_category_parent_id], [news_category_level], [news_category_name], [news_category_detail], [is_delete]) VALUES (6, 5, 2, N'AI & Robotics', N'Advancements in artificial intelligence and robotics.', 0)
GO
INSERT [dbo].[news_category] ([news_category_id], [news_category_parent_id], [news_category_level], [news_category_name], [news_category_detail], [is_delete]) VALUES (7, 5, 2, N'Cybersecurity', N'News about data breaches, hacking, and online safety.', 0)
GO
INSERT [dbo].[news_category] ([news_category_id], [news_category_parent_id], [news_category_level], [news_category_name], [news_category_detail], [is_delete]) VALUES (8, NULL, 1, N'Health', N'Health-related news, research, and wellness tips.', 0)
GO
INSERT [dbo].[news_category] ([news_category_id], [news_category_parent_id], [news_category_level], [news_category_name], [news_category_detail], [is_delete]) VALUES (9, 8, 2, N'Mental Health', N'Resources and news on mental well-being and therapy.', 0)
GO
INSERT [dbo].[news_category] ([news_category_id], [news_category_parent_id], [news_category_level], [news_category_name], [news_category_detail], [is_delete]) VALUES (10, 8, 2, N'Nutrition', N'News and advice on healthy eating and diet trends.', 0)
GO
SET IDENTITY_INSERT [dbo].[news_category] OFF
GO
SET IDENTITY_INSERT [dbo].[product_category] ON 
GO
INSERT [dbo].[product_category] ([product_category_id], [product_category_parent_id], [product_category_level], [product_category_code], [product_category_name], [product_category_detail], [is_deleted]) VALUES (1, NULL, 1, N'CAT-ELECT', N'Electronics', N'All types of electronic devices', 1)
GO
INSERT [dbo].[product_category] ([product_category_id], [product_category_parent_id], [product_category_level], [product_category_code], [product_category_name], [product_category_detail], [is_deleted]) VALUES (2, 1, 2, N'CAT-SMRTPH', N'Smartphones', N'Latest smartphones and accessories', 0)
GO
INSERT [dbo].[product_category] ([product_category_id], [product_category_parent_id], [product_category_level], [product_category_code], [product_category_name], [product_category_detail], [is_deleted]) VALUES (3, 1, 2, N'CAT-LAPTOP', N'Laptops', N'Personal and business laptops', 0)
GO
INSERT [dbo].[product_category] ([product_category_id], [product_category_parent_id], [product_category_level], [product_category_code], [product_category_name], [product_category_detail], [is_deleted]) VALUES (4, NULL, 1, N'CAT-HOMEAPP', N'Home Appliances', N'Appliances for home use', 0)
GO
INSERT [dbo].[product_category] ([product_category_id], [product_category_parent_id], [product_category_level], [product_category_code], [product_category_name], [product_category_detail], [is_deleted]) VALUES (5, 2, 3, N'CAT-KITCHEN3', N'Kitchen Appliances3', N'Various kitchen gadgets and appliances3', 0)
GO
INSERT [dbo].[product_category] ([product_category_id], [product_category_parent_id], [product_category_level], [product_category_code], [product_category_name], [product_category_detail], [is_deleted]) VALUES (6, 4, 2, N'CAT-CLEANING', N'Cleaning Appliances', N'Vacuums and other cleaning devices', 0)
GO
INSERT [dbo].[product_category] ([product_category_id], [product_category_parent_id], [product_category_level], [product_category_code], [product_category_name], [product_category_detail], [is_deleted]) VALUES (7, NULL, 1, N'CAT-CLOTHING', N'Clothing', N'Men, Women, and Kids clothing', 0)
GO
INSERT [dbo].[product_category] ([product_category_id], [product_category_parent_id], [product_category_level], [product_category_code], [product_category_name], [product_category_detail], [is_deleted]) VALUES (8, 7, 2, N'CAT-MEN', N'Men Clothing', N'Men’s shirts, pants, and accessories', 0)
GO
INSERT [dbo].[product_category] ([product_category_id], [product_category_parent_id], [product_category_level], [product_category_code], [product_category_name], [product_category_detail], [is_deleted]) VALUES (9, 7, 2, N'CAT-WOMEN', N'Women Clothing', N'Women’s dresses, tops, and accessories', 0)
GO
INSERT [dbo].[product_category] ([product_category_id], [product_category_parent_id], [product_category_level], [product_category_code], [product_category_name], [product_category_detail], [is_deleted]) VALUES (10, 7, 2, N'CAT-KIDS', N'Kids Clothing', N'Clothing and accessories for children', 0)
GO
INSERT [dbo].[product_category] ([product_category_id], [product_category_parent_id], [product_category_level], [product_category_code], [product_category_name], [product_category_detail], [is_deleted]) VALUES (11, 1, 2, N'thien', N'thientest', N'213e', 0)
GO
SET IDENTITY_INSERT [dbo].[product_category] OFF
GO
SET IDENTITY_INSERT [dbo].[user] ON 
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (1, N'John Doe2222', N'1234567892', N'john.do2e@example.com', N'password123', 1, N'', N'Bank A', N'123456789', NULL, NULL, NULL, N'123 Main St', N'1990-01-01', N'2024-09-22', N'', 1, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (2, N'Jane Smith', N'0987654321', N'jane.smith@example.com', N'password456', 2, N'avatar2.jpg', N'Bank B', N'987654321', N'Province B', N'District B', N'Commune B', N'456 Second St', N'1992-05-15', N'2024-09-22', N'ACT456', 0, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (3, N'Robert Brown', N'1122334455', N'robert.brown@example.com', N'password789', 1, N'avatar3.jpg', N'Bank C', N'456123789', N'Province C', N'District C', N'Commune C', N'789 Third St', N'1985-08-20', N'2024-09-22', N'ACT789', 1, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (4, N'Emily Clark', N'6677889900', N'emily.clark@example.com', N'password101', 3, N'avatar4.jpg', N'Bank D', N'321654987', N'Province D', N'District D', N'Commune D', N'101 Fourth St', N'1995-12-30', N'2024-09-22', N'ACT101', 0, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (5, N'Michael Johnson', N'2233445566', N'michael.johnson@example.com', N'password202', 2, N'avatar5.jpg', N'Bank E', N'789456123', N'Province E', N'District E', N'Commune E', N'202 Fifth St', N'1993-11-10', N'2024-09-22', N'ACT202', 1, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (6, N'Sarah Lee', N'3344556677', N'sarah.lee@example.com', N'password303', 1, N'avatar6.jpg', N'Bank F', N'987123654', N'Province F', N'District F', N'Commune F', N'303 Sixth St', N'1991-02-18', N'2024-09-22', N'ACT303', 1, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (7, N'David Martinez', N'5566778899', N'david.martinez@example.com', N'password404', 2, N'avatar7.jpg', N'Bank G', N'123789456', N'Province G', N'District G', N'Commune G', N'404 Seventh St', N'1987-09-09', N'2024-09-22', N'ACT404', 0, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (8, N'Laura Wilson', N'7788990011', N'laura.wilson@example.com', N'password505', 1, N'avatar8.jpg', N'Bank H', N'321987654', N'Province H', N'District H', N'Commune H', N'505 Eighth St', N'1994-04-25', N'2024-09-22', N'ACT505', 1, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (9, N'Chris Evans', N'8899001122', N'chris.evans@example.com', N'password606', 3, N'avatar9.jpg', N'Bank I', N'654789321', N'Province I', N'District I', N'Commune I', N'606 Ninth St', N'1990-07-14', N'2024-09-22', N'ACT606', 0, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (10, N'Sophia Turner', N'9900112233', N'sophia.turner@example.com', N'password707', 2, N'avatar10.jpg', N'Bank J', N'789123456', N'Province J', N'District J', N'Commune J', N'707 Tenth St', N'1996-03-08', N'2024-09-22', N'ACT707', 1, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (11, N'Olivia White', N'1000112233', N'olivia.white@example.com', N'password808', 1, N'avatar11.jpg', N'Bank K', N'111222333', N'Province K', N'District K', N'Commune K', N'808 Eleventh St', N'1992-10-11', N'2024-09-22', N'ACT808', 1, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (12, N'Ethan Scott', N'1100223344', N'ethan.scott@example.com', N'password909', 2, N'avatar12.jpg', N'Bank L', N'222333444', N'Province L', N'District L', N'Commune L', N'909 Twelfth St', N'1993-06-21', N'2024-09-22', N'ACT909', 0, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (13, N'Ava Moore', N'1200334455', N'ava.moore@example.com', N'password1010', 3, N'avatar13.jpg', N'Bank M', N'333444555', N'Province M', N'District M', N'Commune M', N'1010 Thirteenth St', N'1989-12-12', N'2024-09-22', N'ACT1010', 1, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (14, N'James Harris', N'1300445566', N'james.harris@example.com', N'password1111', 2, N'avatar14.jpg', N'Bank N', N'444555666', N'Province N', N'District N', N'Commune N', N'1111 Fourteenth St', N'1988-11-07', N'2024-09-22', N'ACT1111', 1, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (15, N'Mia Lewis', N'1400556677', N'mia.lewis@example.com', N'password1212', 1, N'avatar15.jpg', N'Bank O', N'555666777', N'Province O', N'District O', N'Commune O', N'1212 Fifteenth St', N'1991-01-30', N'2024-09-22', N'ACT1212', 0, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (16, N'cxzzcx', N'czxzxcczx', N'thientm01@gmail.com', N'`12', 3, N'', N'123312', N'123312', NULL, NULL, NULL, N'213213', N'2024-09-10', N'Sep 22 2024  4:45PM', N'', 0, 0)
GO
INSERT [dbo].[user] ([user_id], [user_name], [user_phone], [user_email], [user_password], [role_level], [user_avatar], [user_bank], [user_bank_code], [user_province], [user_district], [user_commune], [specific_address], [date_of_birth], [created_date], [active_code], [is_active], [is_deleted]) VALUES (17, N'cxzzcx1111', N'czxzxcczx1111', N'thientm011@gmail.com', N'123', 4, N'', N'111', N'123312', NULL, NULL, NULL, N'213213', N'2024-09-16', N'Sep 22 2024  4:58PM', N'', 0, 0)
GO
SET IDENTITY_INSERT [dbo].[user] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__product__AE1A8CC4584C4B65]    Script Date: 9/22/2024 11:21:42 PM ******/
ALTER TABLE [dbo].[product] ADD UNIQUE NONCLUSTERED 
(
	[product_code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__product___871BFA4F36A1F4C5]    Script Date: 9/22/2024 11:21:42 PM ******/
ALTER TABLE [dbo].[product_category] ADD UNIQUE NONCLUSTERED 
(
	[product_category_code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UQ__staff__B9BE370E2BB3114E]    Script Date: 9/22/2024 11:21:42 PM ******/
ALTER TABLE [dbo].[staff] ADD UNIQUE NONCLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__user__6A5ACCAB268A1A8B]    Script Date: 9/22/2024 11:21:42 PM ******/
ALTER TABLE [dbo].[user] ADD UNIQUE NONCLUSTERED 
(
	[user_phone] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__user__B0FBA212D24FC26E]    Script Date: 9/22/2024 11:21:42 PM ******/
ALTER TABLE [dbo].[user] ADD UNIQUE NONCLUSTERED 
(
	[user_email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[news] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[news_category] ADD  DEFAULT ((0)) FOR [is_delete]
GO
ALTER TABLE [dbo].[price] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[product] ADD  DEFAULT ((0)) FOR [created_date]
GO
ALTER TABLE [dbo].[product] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[product_category] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[product_image] ADD  DEFAULT ((1)) FOR [is_main]
GO
ALTER TABLE [dbo].[product_image] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[staff] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[supplier] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[user] ADD  DEFAULT ((0)) FOR [is_active]
GO
ALTER TABLE [dbo].[user] ADD  DEFAULT ((0)) FOR [is_deleted]
GO
ALTER TABLE [dbo].[news]  WITH CHECK ADD FOREIGN KEY([creator_id])
REFERENCES [dbo].[user] ([user_id])
GO
ALTER TABLE [dbo].[news]  WITH CHECK ADD FOREIGN KEY([news_category_id])
REFERENCES [dbo].[news_category] ([news_category_id])
GO
ALTER TABLE [dbo].[news]  WITH CHECK ADD FOREIGN KEY([updated_id])
REFERENCES [dbo].[user] ([user_id])
GO
ALTER TABLE [dbo].[price]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([product_id])
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD FOREIGN KEY([product_category_id])
REFERENCES [dbo].[product_category] ([product_category_id])
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD FOREIGN KEY([supplier_id])
REFERENCES [dbo].[supplier] ([supplier_id])
GO
ALTER TABLE [dbo].[product_image]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([product_id])
GO
ALTER TABLE [dbo].[staff]  WITH CHECK ADD FOREIGN KEY([user_id])
REFERENCES [dbo].[user] ([user_id])
GO
USE [master]
GO
ALTER DATABASE [yagi_pharmacy] SET  READ_WRITE 
GO
