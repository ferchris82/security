package chrisferdev.security.services;

import java.util.List;

import chrisferdev.security.persistence.entities.UserEntity;

public interface IUserService {

    public List<UserEntity> findAllUsers();
}
