package com.iablonski.users.controller;

import com.iablonski.users.dto.UserDTO;
import com.iablonski.users.response.MessageResponse;
import com.iablonski.users.search.UserSearchValues;
import com.iablonski.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    public static final String ID_COLUMN = "id";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestBody String email) {
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/id")
    public ResponseEntity<UserDTO> getUserById(@RequestBody UUID id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> createUser(@RequestBody UserDTO userDTO) {
        System.out.println("controller");
        userService.createUser(userDTO);
        return new ResponseEntity<>(new MessageResponse("Successfully created"), HttpStatus.OK);    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return new ResponseEntity<>(new MessageResponse("Successfully updated"), HttpStatus.OK);
    }

    @PostMapping("/delete/email")
    public ResponseEntity<MessageResponse> deleteUserByEmail(@RequestBody String email) {
        userService.deleteUserByEmail(email);
        return new ResponseEntity<>(new MessageResponse("Successfully deleted"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteUserById(@PathVariable("id") UUID id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(new MessageResponse("Successfully deleted"), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<UserDTO>> searchUser(@RequestBody UserSearchValues values) {
        Sort sort = Sort.by(values.getSortDirection(), values.sortColumn(), ID_COLUMN);
        PageRequest pageRequest = PageRequest.of(values.pageNumber(), values.pageSize(), sort);
        Page<UserDTO> page = userService.findByParams(values.email(), values.username(), pageRequest);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}