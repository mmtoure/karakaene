package com.mmt.karakaene.service.user;

import com.mmt.karakaene.model.User;
import com.mmt.karakaene.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService{

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws Exception {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw  new Exception(" User not found");
        }
        return optionalUser.get();
    }

    @Override
    public User updateUser(User user, Long id) throws Exception {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw  new Exception(" User not found");
        }
        User existingUser = optionalUser.get();
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setLastName(user.getLastName());
        existingUser.setFirstName(user.getFirstName());
        return  userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw  new Exception(" User not found");
        }
        User existingUser = optionalUser.get();
        userRepository.deleteById(existingUser.getId());

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
