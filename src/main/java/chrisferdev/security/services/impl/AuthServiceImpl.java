package chrisferdev.security.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import chrisferdev.security.persistence.entities.UserEntity;
import chrisferdev.security.persistence.entities.repositories.UserRepository;
import chrisferdev.security.services.IAuthService;
import chrisferdev.security.services.IJWTUtilityService;
import chrisferdev.security.services.impl.models.dtos.LoginDTO;
import chrisferdev.security.services.impl.models.dtos.ResponseDTO;
import chrisferdev.security.services.impl.models.dtos.validation.UserValidation;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IJWTUtilityService jIjwtUtilityService;

    @Autowired
    private UserValidation userValidation;

    @Override
    public HashMap<String, String> login(LoginDTO login) throws Exception{

        try {
            HashMap<String, String> jwt = new HashMap<>();
            Optional<UserEntity> user = userRepository.findByEmail(login.getEmail());

            if (user.isEmpty()) {
                jwt.put("error", "User not registered!");
                return jwt;
            }

            if (verifyPassword(login.getPassword(), user.get().getPassword())) {
                jwt.put("jwt", jIjwtUtilityService.generateJWT(user.get().getId()));
            } else {
                jwt.put("error", "Authentication failed");
            }
            return jwt;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }
    public ResponseDTO register(UserEntity user) throws Exception{
        try {
            ResponseDTO response = userValidation.validate(user);  
            
            if (response.getNumOfErrors() > 0) {
                return response;
            }

            List<UserEntity> getAllUsers = userRepository.findAll();

            for(UserEntity repetFields : getAllUsers){
                if (repetFields != null) {
                    response.setNumOfErrors(1);
                    response.setMessage("User already exits!");
                    return response;
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            response.setMessage("User created succesfully!");

            return response;
        } catch (Exception e) {
            throw new Exception(e. toString());
        }
    }
    private boolean verifyPassword(String enteredPassword, String storedPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
