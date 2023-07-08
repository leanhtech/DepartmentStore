package com.ptit.springbootdepartmentstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.entity.User;
import com.ptit.springbootdepartmentstore.repository.UserRepository;

@Service
public class UserServiceSecurity implements UserDetailsService{ 

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByName(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }
    
    public UserDetails loadUserById(Integer id) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("NotFoundId");
        }
        return new CustomUserDetails(user);
    }


}
