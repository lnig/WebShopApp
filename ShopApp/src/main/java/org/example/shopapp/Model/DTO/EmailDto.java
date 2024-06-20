package org.example.shopapp.Model.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailDto {
    private String to;
    private String subject;
    private String text;

    public EmailDto(String to, String subject, String text) {
        this.to = to;
        this.subject = subject;
        this.text = text;
    }
}
