package pl.coderslab.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This interface creates new annotation for email validation
 */
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailKN {

    String message() default "Incorrect email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
