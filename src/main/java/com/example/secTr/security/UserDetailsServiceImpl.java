package com.example.secTr.security;

import com.example.secTr.Model.User;
import com.example.secTr.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepos userRepos;

    @Autowired
    public UserDetailsServiceImpl(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepos.findByEmail(email).orElseThrow(() ->
                    new UsernameNotFoundException("User doesn't exist"));
        return SecurityUser.fromUser(user);
    }
}
