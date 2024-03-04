package com.iablonski.users.service;

import com.iablonski.users.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface UserService {

    UserDTO getUserByEmail(String email);
    UserDTO getUserById(UUID id);
    void createUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUserByEmail(String email);
    void deleteUserById(UUID id);
    Page<UserDTO> findByParams(String username, String email, PageRequest pageRequest);
}
