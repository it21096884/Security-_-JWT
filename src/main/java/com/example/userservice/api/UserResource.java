package com.example.userservice.api;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.Users;
import com.example.userservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping("/getUsers")
    public ResponseEntity<List<Users>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }


    @PostMapping ("/user/addUsers")
    public ResponseEntity<Users> saveUsers(@RequestBody Users users) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/addUsers").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(users));
    }

    @PostMapping ("/role/addRole")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/addRole").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping ("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserFrom roleToUserFrom) {
        userService.addRoleToUSer(roleToUserFrom.getUsername(), roleToUserFrom.getRoleName());
        return ResponseEntity.ok().build();
    }

}
@Data

class RoleToUserFrom{
    private String username;
    private String roleName;
}

