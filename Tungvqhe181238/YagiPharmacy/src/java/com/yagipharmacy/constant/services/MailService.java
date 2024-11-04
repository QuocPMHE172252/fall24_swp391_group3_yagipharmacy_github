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
import java.util.Date;
import java.util.List;
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
        String mail = """
                      <h1>Bảng sản phẩm yêu cầu</h1><br>
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
        mail += "<span style='color:red'>Ngày nhập yêu cầu:"+importExpectedDate.getDate()+"/"+importExpectedDate.getMonth()+"/"+(importExpectedDate.getYear()+1900)+"</span>";
        return mail;
    }
}
