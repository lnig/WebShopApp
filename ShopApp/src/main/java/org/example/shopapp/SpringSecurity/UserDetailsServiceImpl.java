package org.example.shopapp.SpringSecurity;

import jakarta.servlet.http.HttpSession;
import org.example.shopapp.Model.DAO.AdministratorDao;
import org.example.shopapp.Model.DAO.ClientDao;
import org.example.shopapp.Model.Data.Administrator;
import org.example.shopapp.Model.Data.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdministratorDao administratorDao;

    @Autowired
    private ClientDao clientDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administrator administrator = administratorDao.getAdministratorByEmail(email);
        Client client = clientDao.getClientByEmail(email);

        if (administrator == null && client == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found!");
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        UserDetails userDetails;

        if (administrator != null) {
            userDetails = buildUserDetails(administrator.getId(), administrator.getEmail(), administrator.getPassword(), Collections.singleton("ADMIN"));
        } else {
            userDetails = buildUserDetails(client.getId(), client.getEmail(), client.getPassword(), Collections.singleton("CLIENT"));
        }

        HttpSession session = request.getSession(false);
        if (session != null && !session.isNew()) {
            session.setAttribute("UserDetails", userDetails);
        }

        System.out.println("UserDetails: " + userDetails.getUsername() + " " + userDetails.getPassword() + " " + userDetails.getAuthorities());
        return userDetails;
    }

    private UserDetails buildUserDetails(Integer userId, String email, String password, Set<String> roles) {
        return new CustomUserDetails(userId, email, password, roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList()));
    }
}
