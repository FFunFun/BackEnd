package com.ffuntree.ffunfun.service;

import com.ffuntree.ffunfun.data.email.ReqSendEmailAuthenticationDTO;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailVertificationService {
    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    public Boolean sendEmailAuthentication(ReqSendEmailAuthenticationDTO reqEmailAuthenticationApiV1DTO, String authenticationCode) {
        // 메시지 객체를 생성하고
        MimeMessage message = javaMailSender.createMimeMessage();

        // 정규식 패턴
        String acKrPattern = ".*\\.ac\\.kr$";

        String email = reqEmailAuthenticationApiV1DTO.getEmail();

        // 정규식 패턴과 매치되는지 확인
        Pattern pattern = Pattern.compile(acKrPattern);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            return false;
        }

        try {
            // 이메일 제목 설정
            message.setSubject("사이트 회원가입 인증번호 입니다.");

            // 이메일 수신자 설정
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, "", "UTF-8"));

            // 이메일 내용 설정
            message.setText(setContext(authenticationCode), "UTF-8", "html");

            // 송신
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        // 다 성공했다면
        return true;
    }

    // 생성해놓은 html에 인증 코드를 넣어서 반환
    private String setContext(String authenticationCode) {
        Context context = new Context();
        context.setVariable("authenticationCode", authenticationCode);
        return templateEngine.process("email-authentication", context);
    }
}