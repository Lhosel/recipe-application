package ca.gbc.recipeproject.services;

import ca.gbc.recipeproject.config.MyUserDetails;
import ca.gbc.recipeproject.model.User;
import ca.gbc.recipeproject.services.springdatajpa.UserSDJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserSDJpaService userSDJpaService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userSDJpaService.getUserByUsername(username);

        if(user == null && !user.isStatus()) {
            throw new UsernameNotFoundException("User does not exist");
        }

        return new MyUserDetails(user);

    }
}
