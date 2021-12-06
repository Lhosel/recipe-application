/*
 * Project: < comp3095_assignment2 >
 * Assignment: < assignment 2 >
 * Author(s): <Dilan Piyasenage Don, Dominic Gopalakrishnan, Kunga Lhosel, Minh Duc Cung>
 * Student Number: <101278656, 101289239, 101266937,101234383>
 * Date: December 5th, 2021
 * Description: The service for MyUserDetails so you can load by username
 */

package ca.gbc.recipeproject.config;

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
