package com.example.userservice.service;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.Users;

import java.util.List;

public interface UserService {
    Users saveUser(Users user);
    Role saveRole(Role role);
    void addRoleToUSer(String username, String roleName);
    Users getUser(String username);
    List<Users> getUsers();
}
