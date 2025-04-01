package com.mmt.karakaene.service.user;

import com.mmt.karakaene.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id) throws Exception;
    User updateUser(User user, Long id) throws Exception;
    void deleteUser(Long id) throws Exception;
    List<User> getAllUsers();
    void confirmationCode(Map<String,String> activation);
    User loadUserByUsername (String username);
}
