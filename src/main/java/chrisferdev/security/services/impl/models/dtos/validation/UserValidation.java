package chrisferdev.security.services.impl.models.dtos.validation;

import chrisferdev.security.persistence.entities.UserEntity;
import chrisferdev.security.services.impl.models.dtos.ResponseDTO;

public class UserValidation {

    public ResponseDTO validate(UserEntity user){
        ResponseDTO response = new ResponseDTO();
        response.setNumOfErrors(0);

        if(user.getFirstName() == null ||
           user.getFirstName().length() < 3 ||
           user.getFirstName().length() > 15
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo firstName no puede ser nulo, también tiene" +
                                "que tener entre 3 y 15 carateres.");
        }

        if(user.getLastName() == null ||
           user.getLastName().length() < 3 ||
           user.getLastName().length() > 30
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo lastName no puede ser nulo, también tiene" +
                                "que tener entre 3 y 30 carateres.");
        }

        if(user.getEmail() == null ||
           !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo email no es valido.");
        }

        if(user.getPassword() == null||
           !user.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,16}$")){
           response.setNumOfErrors(response.getNumOfErrors() + 1);
           response.setMessage("La contraseña debe tener entre 8 y 16 caracteres," +
                               "al menos un número, una minuscula y una mayuscula.");
        }
        
        return response;
    }
}
