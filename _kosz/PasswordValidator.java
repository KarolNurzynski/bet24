package pl.coderslab.validation;

import pl.coderslab.bet.dto.UserDto;
import pl.coderslab.entity.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator <PasswordKN, Object> {

    @Override
    public void initialize(PasswordKN constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object userToValidate, ConstraintValidatorContext constraintValidatorContext) {
        User user = (User) userToValidate;
        return user.getPassword().equals(user.getPasswordCheck());
    }

}
