package pl.coderslab.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is a validator of email address
 */
public class EmailValidator implements ConstraintValidator <EmailKN, String> {


    @Override
    public void initialize(EmailKN constraintAnnotation) {

    }

    @Override
    public boolean isValid(String emailToValidate, ConstraintValidatorContext constraintValidatorContext) {
        return emailToValidate.matches(".+@.+\\..+");
    }
}
