/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.constant.services;

/**
 *
 * @author admin
 */
import com.yagipharmacy.constant.variables.SystemVariable;
import com.yagipharmacy.entities.ImportOrderDetail;
import com.yagipharmacy.entities.SaleOrder;
import com.yagipharmacy.entities.SaleOrderDetail;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 *
 * @author admin
 */
public class MailService {

    public static boolean sentEmail(String toEmail, String subject, String body) {

        // Config
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SystemVariable.MAIL, SystemVariable.PASSWORD);
            }
        });

        // Mail info
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SystemVariable.MAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject, "UTF-8");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(body, "text/html; charset=UTF-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean sentEmailNoStatic(String toEmail, String subject, String body) {

        // Config
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SystemVariable.MAIL, SystemVariable.PASSWORD);
            }
        });

        // Mail info
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SystemVariable.MAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject, "UTF-8");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(body, "text/html; charset=UTF-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String createAcceptOrderDetailsMail(String suplierId, Date importExpectedDate ,List<ImportOrderDetail> importOrderDetails) {
        String tableDetail = "";
        String mail = """
                      <strong>Người gửi: </strong><span>Nhà thuốc Yagi Pharmacy</span><br>
                      <strong>Địa chỉ: </strong><span>Thôn 3 Thạch Hòa, Thạch Thất, Hà Nội</span><br>
                      <strong>Điện thoại: </strong><span>0356682909</span><br>
                      <strong>Người gửi: </strong><span>Nhà thuốc Yagi Pharmacy</span><br>
                      <strong>Email: </strong><span>minhquoctkq25@gmail.com</span><br><br>
                      <strong>Người nhận: </strong><span>"""+importOrderDetails.get(0).getSupplier().getSupplierName()+"""
                                                              </span><br><br>
                      <strong>Nội dung thông báo:</strong><br><br>
                      <div>Kính gửi Quý nhà cung cấp,</div><br><br>
                      <div>
                           Nhà thuốc Yagi Pharmacy xin gửi lời chào trân trọng và cảm ơn Quý Nhà cung cấp đã hợp tác<br>trong thời gian qua. Nhằm đáp ứng nhu cầu dự trữ và cung cấp thuốc đến khách hàng, chúng tôi<br>có nhu cầu nhập một số mặt hàng sau:
                      </div><br><br>
                      
                      """;
        for (ImportOrderDetail importOrderDetail : importOrderDetails) {
            String row = """
                         <tr>
                            <td>"""+importOrderDetail.getProduct().getProductCode()+"""
                                    </td>
                            <td>"""+importOrderDetail.getProduct().getProductName()+"""
                                    </td>
                            <td>"""+importOrderDetail.getUnit().getUnitName()+"""
                                    </td>
                            <td>"""+importOrderDetail.getQuantity()+"""
                                    </td>
                         </tr>
                         """;
            tableDetail += row;
        }
        String tbArea = """
                      <table border='solid'>
                      <tr>
                      <th>Mã sản phẩm</td>
                      <th>Tên sản phẩm</td>
                      <th>Đơn vị</td>
                      <th>Số lượng</td>
                      </tr>""" + tableDetail
                + """ 
                                           </table><br>
                      """;
        mail += tbArea;
        mail += "<span style='color:red'><strong>Thời gian giao hàng dự kiến:</strong>"+importExpectedDate.getDate()+"/"+importExpectedDate.getMonth()+"/"+(importExpectedDate.getYear()+1900)+"</span><br>";
        mail += """
                <strong>Địa điểm giao hàng: </strong><span>Nhà thuốc Yagi Pharmacy, Thôn 3 Thạch Hòa, Thạch Thất, Hà Nội</span><br><br>
                <strong>Yêu cầu đặc biệt:</strong><br>
                <ul>
                    <li>Hàng hóa phải đảm bảo chất lượng, nguồn gốc xuất xứ rõ ràng.</li>
                    <li>Vui lòng gửi báo giá chi tiết và thông tin về thời gian giao hàng cụ thể.</li>
                </ul><br>
                <div>Quý Nhà cung cấp vui lòng phản hồi trong thời gian sớm nhất để chúng tôi có thể hoàn tất các<br>thủ tục cần thiết.</div>
                <strong>Trân trọng cảm ơn,</strong><br>
                <div>Nhà thuốc Yagi Pharmacy</div>
                <div>&nbsp;&nbsp;Nhân viên</div>
                <div>Phạm Minh Quốc</div>
                """;
        
        mail = "<div>"+mail+"</div>";
        mail = "<div style='display:flex;flex-direction:row;justify-content:center'>"+mail+"</div>";
        return mail;
    }
    
    public static String createSaleOrderSuccessfullMail(SaleOrder saleOrder){
        String mail = """
                      <strong>Người gửi: </strong><span>Nhà thuốc Yagi Pharmacy</span><br>
                      <strong>Địa chỉ: </strong><span>Thôn 3 Thạch Hòa, Thạch Thất, Hà Nội</span><br>
                      <strong>Điện thoại: </strong><span>0356682909</span><br>
                      <strong>Người gửi: </strong><span>Nhà thuốc Yagi Pharmacy</span><br>
                      <strong>Email: </strong><span>minhquoctkq25@gmail.com</span><br><br>
                      <strong>Người nhận: </strong><span>"""+saleOrder.getReceiverName()+"""
                                                              </span><br><br>
                      <strong>Địa chỉ giao hàng: </strong><span>"""+saleOrder.getSpecificAddress()+"""
                                                                     </span><br>
                      <strong>Nội dung thông báo:</strong><br><br>
                      <div>Kính gửi Quý khách,</div><br><br>
                      <div>
                           Nhà thuốc Yagi Pharmacy xin trân trọng cảm ơn Quý khách đã tin tưởng và lựa chọn dịch vụ của<br>chúng tôi. Chúng tôi xin xác nhận đơn hàng của qúy khách đã được xử lý thành<br>công. Sau đây là thông tin chi tiết đơn hàng:
                      </div><br><br>
                      
                      """;
        String tableDetail = "";
        for (SaleOrderDetail saleOrderDetail : saleOrder.getSaleOrderDetails()) {
            String row = """
                         <tr>
                            <td>"""+saleOrderDetail.getProduct().getProductCode()+"""
                                    </td>
                            <td>"""+saleOrderDetail.getProduct().getProductName()+"""
                                    </td>
                            <td>"""+saleOrderDetail.getUnit().getUnitName()+"""
                                    </td>
                            <td>"""+saleOrderDetail.getQuantity()+"""
                                    </td>
                            <td>"""+formatPrice(saleOrderDetail.getProductUnit().getSellPrice()*saleOrderDetail.getQuantity())+"""
                                    </td>
                         </tr>
                         """;
            tableDetail += row;
        }
        
        String tbArea = """
                      <table border='solid'>
                      <tr>
                      <th>Mã sản phẩm</td>
                      <th>Tên sản phẩm</td>
                      <th>Đơn vị</td>
                      <th>Số lượng</td>
                      <th>Giá</td>
                      </tr>""" + tableDetail
                + """ 
                                           </table><br>
                      """;
        mail += tbArea;
        mail += """
                <strong>Tổng giá trị đơn hàng: </strong><span>
                """+formatPrice(saleOrder.getTotalPrice())+
                """
                </span><br>
                <strong>Thời gian dự kiến:</strong>&nbsp;<span>2 giờ sau khi đặt hàng</span>
                <div>Nếu có bất kì thắc mắc nào về đơn hàng quý khách vui lòng liên hệ chúng tôi qua số điện<br>thoại 0356682909 hoặc email: minhquoctkq25@gmail.com</div><br><br>
                <strong>Trân trọng cảm ơn,</strong>
                <div>Nhà thuốc YagiPharmacy</div>
                <div>&nbsp;&nbsp;Nhân viên</div>
                <div>Phạm Minh Quốc</div>
                """;
        return mail;
    }
    
    public static String formatPrice(Double sellPrice) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("en", "US"));
        return formatter.format(sellPrice);
    }
}
