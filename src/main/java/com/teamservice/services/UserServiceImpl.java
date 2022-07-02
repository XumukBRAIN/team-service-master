package com.teamservice.services;

import com.teamservice.models.User;
import com.teamservice.repositories.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> usersNotInGroup(Long groupId) {
        return userRepository.getUsersNotInGroup(groupId);
    }

    @Override
    public List<User> dayOverdue(Long userID) {
        return userRepository.dayOverdue(userID);
    }


}
