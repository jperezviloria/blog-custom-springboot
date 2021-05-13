package dev.protobot.blogcustom.security;

import dev.protobot.blogcustom.model.User;
import dev.protobot.blogcustom.respository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImplementation (UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException(("No user found with username :").concat(username)));


        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnable(),
                true,
                true,
                true,
                getAuthorities("USER"));
    }


    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }

}
