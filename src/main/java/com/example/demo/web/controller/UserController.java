package com.example.demo.web.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Validated
@RestController
@RequestMapping("/users")
public class UserController extends BaseController<UserDto, User> {

    @Autowired
    private UserService userService;

    @Override
    protected UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    protected User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable @Min(0) Integer id) {
        return convertToDto(userService.findById(id));
    }

    @PostMapping
    public UserDto save(@RequestBody @Validated UserDto userDto) {
        return convertToDto(userService.save(convertToEntity(userDto)));
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Integer id, @RequestBody @Validated UserDto userDto) {
        userDto.setId(id);
        return convertToDto(userService.update(convertToEntity(userDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);
    }

}
