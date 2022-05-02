package com.geekbrains.spring.web.auth.services;

import com.geekbrains.spring.web.auth.entities.Role;
import com.geekbrains.spring.web.auth.entities.User;
import com.geekbrains.spring.web.auth.repositories.RoleRepository;
import com.geekbrains.spring.web.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    public boolean validateLoginInDatabase(String login){
        return userRepository.findByUsername(login).isPresent();
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public void addNewUserInDatabase(String login, String password, String email) {
        long roleUser = 1L;
        User user = new User();
        user.setUsername(login);
        user.setPassword(encoder.encode(password));
        user.setEmail(email);
        user.setRoles(new ArrayList<>(List.of(roleRepository.getById(roleUser))));
        userRepository.save(user);
    }
}