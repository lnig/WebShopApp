package org.example.shopapp.Model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Login implements Serializable {

    private String email;
    private String password;
}
