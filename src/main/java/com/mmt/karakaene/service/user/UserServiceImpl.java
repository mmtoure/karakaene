package com.mmt.karakaene.service.user;

import com.mmt.karakaene.model.Role;
import com.mmt.karakaene.model.TypeRole;
import com.mmt.karakaene.model.User;
import com.mmt.karakaene.model.Validation;
import com.mmt.karakaene.repository.UserRepository;
import com.mmt.karakaene.repository.ValidationRepository;
import com.mmt.karakaene.service.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ValidationService validationService;

    @Override
    public User createUser(User user) {
        String passwordCrypted = this.passwordEncoder.encode(user.getPassword());
        Role roleUser = new Role();
        roleUser.setTypeRole(TypeRole.USER);
        user.setRole(roleUser);
        user.setPassword(passwordCrypted);
        User newUser = userRepository.save(user);
        this.validationService.validationByUser(newUser);
        return newUser;

    }

    public void confirmationCode(Map<String,String> activation){
        Validation validation = this.validationService.getValidationByCode(activation.get("code"));
        User user = validation.getUser();
        user.setActif(true);
        this.userRepository.save(user);

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
