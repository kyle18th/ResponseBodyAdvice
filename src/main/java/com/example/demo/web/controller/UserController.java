package com.example.demo.web.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<User> findAll(@RequestParam(required = false, defaultValue = "1") @Min(0) Integer page,
                              @RequestParam(required = false, defaultValue = "10") @Min(1) Integer size) {
        Page<User> userPage = userService.findAll(page, size);
        userPage.get().forEach(this::convertToDto);
        return userPage;
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Integer id) {
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
