package com.example.jdbcexample.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EmailProvider {

    GMAIL_COM("gmail.com"),
    INBOX_LV("inbox.lv"),
    YAHOO_COM("yahoo.com"),
    MAIL_RU("mail.ru");

    @Getter
    private String emailProvider;

}


