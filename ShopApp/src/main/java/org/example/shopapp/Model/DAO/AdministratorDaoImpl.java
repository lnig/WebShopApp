package org.example.shopapp.Model.DAO;

import org.example.shopapp.Model.Data.Administrator;
import org.example.shopapp.Repository.AdministratorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministratorDaoImpl implements AdministratorDao {
    private AdministratorRepository administratorRepository;

    public AdministratorDaoImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Administrator getAdministrator(String email,String password) {
        Administrator adminstrator = administratorRepository.findAdministratorByEmailAndPassword(email,password);
        return adminstrator;
    }

    @Override
    public List<Administrator> getAllCAdministrators() {
        return administratorRepository.findAll();
    }

    @Override
    public void updateAdministrator(Administrator administrator) {
    }

    @Override
    public void deleteAdministrator(Administrator administrator) {
    }
}
