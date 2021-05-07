package com.example.bluewinmailhometask.controller;

import com.example.bluewinmailhometask.model.entity.UserEntity;
import com.example.bluewinmailhometask.model.entity.UsersEntityModel;
import com.example.bluewinmailhometask.model.request.User;
import com.example.bluewinmailhometask.model.response.EmailQuota;
import com.example.bluewinmailhometask.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserQuotaController {

    private final UserService userService;

    @GetMapping(produces = {"application/hal+json"})
    public ResponseEntity<UsersEntityModel> getAllUsers(){
        return ResponseEntity.ok().body(userService.getUserList());
    }

    @GetMapping(value = "/{id}",produces = {"application/hal+json"})
    public ResponseEntity<UserEntity> getUserById(@PathVariable long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping(produces = {"application/hal+json"})
    public ResponseEntity<UserEntity> createUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @PatchMapping(value = "/{id}",
    produces = {"application/hal+json"})
    public ResponseEntity<UserEntity> updateUser(@PathVariable @NonNull long id, @RequestBody @NonNull EmailQuota emailQuota){
        return ResponseEntity.ok().body(userService.updateEmailQuota(id, emailQuota));
    }
}
