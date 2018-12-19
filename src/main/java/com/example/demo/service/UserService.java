package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.ServiceException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> findAll(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ServiceException(20001, "User not found"));
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

}
