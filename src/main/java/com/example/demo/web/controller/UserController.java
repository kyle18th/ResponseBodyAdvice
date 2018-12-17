package com.example.demo.web.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.model.ResponseModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<UserDto> findAll() {
        return userService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Integer id) {
        return convertToDto(userService.findById(id));
    }

    @PostMapping
    public UserDto save(@Validated @RequestBody UserDto userDto) {
        return convertToDto(userService.save(convertToEntity(userDto)));
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Integer id, @Validated @RequestBody UserDto userDto) {
        userDto.setId(id);
        return convertToDto(userService.update(convertToEntity(userDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);
    }

}
