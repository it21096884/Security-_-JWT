package com.example.userservice.service.util;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.Users;
import com.example.userservice.repository.RoleRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceIMPL implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username);
        if (users == null){
            log.error("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }else {
            log.info("user found in the database : {} ",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        users.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(users.getUsername(),users.getPassword(),authorities);
    }
    @Override
    public Users saveUser(Users user) {
        log.info("Saving new user to database",user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUSer(String username, String roleName) {
        log.info("Adding role {} to user {} ", roleName,username);
        Users user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public Users getUser(String username) {
        log.info("Fetching user {} ",username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Users> getUsers() {
        log.info("Fetching all users ");
        return userRepository.findAll();
    }


}
