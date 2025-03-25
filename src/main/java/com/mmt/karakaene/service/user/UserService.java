package com.mmt.karakaene.service.user;

import com.mmt.karakaene.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id) throws Exception;
    User updateUser(User user, Long id) throws Exception;
    void deleteUser(Long id) throws Exception;
    List<User> getAllUsers();
}
