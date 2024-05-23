package chrisferdev.security.services;

import java.util.HashMap;

import chrisferdev.security.persistence.entities.UserEntity;
import chrisferdev.security.services.impl.models.dtos.LoginDTO;
import chrisferdev.security.services.impl.models.dtos.ResponseDTO;

public interface IAuthService {

    public HashMap<String, String> login(LoginDTO login) throws Exception;

    public ResponseDTO register(UserEntity user) throws Exception;
}
