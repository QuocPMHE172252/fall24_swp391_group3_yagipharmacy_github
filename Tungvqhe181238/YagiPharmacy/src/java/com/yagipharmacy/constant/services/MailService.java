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

    public String createAcceptOrderDetailsMail(String suplierId, List<ImportOrderDetail> importOrderDetails) {
        String tableDetail = "";
        String mail = """
                      <h1>Đơn xác nhận đặt thuốc</h1><br>
                      <table>
                      <thead>Bảng nhập hàng</thead>
                      <tr>
                      <th>Mã sản phẩm</td>
                      <th>Tên sản phẩm</td>
                      <th>Đơn vị</td>
                      <th>Số lượng</td>
                      </tr>""" + tableDetail
                + """ 
                                           </table>
                      """;
        for (ImportOrderDetail importOrderDetail : importOrderDetails) {
            String row = """
                         <>
                         """;
        }
        return mail;
    }
}
