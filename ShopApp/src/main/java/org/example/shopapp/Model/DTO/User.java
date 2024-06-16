package org.example.shopapp.Model.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String name;
    private String surname;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='*****'" +
                '}';
    }
}
