package org.example.shopapp.Model.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailDto {
    private String to;
    private String subject;
    private String text;

}
