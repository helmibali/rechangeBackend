package com.helmi.TunningMarket.entities;

import javax.persistence.*;

@Entity
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="email_id")
    private Long id;
    private String body;
    private String subject;
    private String mailTo ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public Email(Long id, String body, String subject, String mailTo) {
        this.id = id;
        this.body = body;
        this.subject = subject;
        this.mailTo = mailTo;
    }

    public Email() {
    }
}
