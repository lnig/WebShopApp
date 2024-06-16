package org.example.shopapp.Model.DAO;

import org.example.shopapp.Model.Data.Administrator;

import java.util.List;

public interface AdministratorDao {
    Administrator getAdministrator(String email, String password);
    List<Administrator> getAllCAdministrators();
    void updateAdministrator(Administrator administrator);
    void deleteAdministrator(Administrator administrator);
}
