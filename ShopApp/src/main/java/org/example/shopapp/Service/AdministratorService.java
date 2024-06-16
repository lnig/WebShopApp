package org.example.shopapp.Service;

import org.example.shopapp.Model.DAO.AdministratorDao;
import org.example.shopapp.Model.Data.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {
    private final AdministratorDao administratorDao;
    @Autowired
    public AdministratorService(AdministratorDao administratorDao) {
        this.administratorDao = administratorDao;
    }

    public Administrator getAdministrator(String email, String password){
        return administratorDao.getAdministrator(email,password);
    }
}
