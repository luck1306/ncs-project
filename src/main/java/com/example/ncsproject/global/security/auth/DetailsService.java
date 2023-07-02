package com.example.ncsproject.global.security.auth;

import com.example.ncsproject.domain.user.domain.User;
import com.example.ncsproject.domain.user.domain.repository.UserRepository;
import com.example.ncsproject.domain.user.exception.NameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElseThrow(()-> NameNotFoundException.EXCEPTION);
        return new Details(user);
    }
}
