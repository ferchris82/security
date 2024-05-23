package chrisferdev.security.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chrisferdev.security.persistence.entities.UserEntity;
import chrisferdev.security.persistence.entities.repositories.UserRepository;
import chrisferdev.security.services.IUserService;

@Service
public class UserServiceImpl implements IUserService{
    
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }
}
