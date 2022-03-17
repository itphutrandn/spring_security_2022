package com.eazybytes.config;

import com.eazybytes.entities.User;
import com.eazybytes.models.BankUserDetail;
import com.eazybytes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class BankUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userInfo = userRepository.findByEmail(email);
        if(ObjectUtils.isEmpty(userInfo)) {
            throw new UsernameNotFoundException("User details not found for the user :"+email);
        }
        // Set password encoder bcrypt
        //userInfo.setPassword(cryptPasswordEncoder.encode(userInfo.getPassword()));
        return new BankUserDetail(userInfo);
    }
}
