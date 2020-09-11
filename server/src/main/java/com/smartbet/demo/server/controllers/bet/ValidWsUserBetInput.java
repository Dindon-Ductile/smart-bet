package com.smartbet.demo.server.controllers.bet;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidWSUserBetInputConstraintValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidWsUserBetInput {
    String message() default "The User bet does not respect creation constraints";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
