package com.nc13.react_board.Service;

import com.nc13.react_board.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService USER_SERVICE;

    @Autowired
    public UserDetailsServiceImpl(UserService userService){
        USER_SERVICE=userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: "+username);
        UserDTO userDTO= USER_SERVICE.selectByUsername(username);
        if(userDTO == null) {
            throw new UsernameNotFoundException(username+ "is not a valid username");
        }

        return userDTO;
    }
}
