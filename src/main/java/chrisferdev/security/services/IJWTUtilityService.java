package chrisferdev.security.services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.nimbusds.jose.JOSEException;

public interface IJWTUtilityService {
    
    public String generateJWT(Long userId) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, JOSEException;
}
