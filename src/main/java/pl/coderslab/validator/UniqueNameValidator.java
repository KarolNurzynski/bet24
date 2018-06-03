package pl.coderslab.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.entity.User;
import pl.coderslab.serviceInterface.UserServiceInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    @Autowired
    UserServiceInterface userServiceInterface;

        @Override
        public void initialize(UniqueName constraintAnnotation) {
        }
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
                try {
                    User user = (userServiceInterface.findByUserName(value));
                    return false;
                } catch (NullPointerException e) {
                    return true;
                }


        }
}
