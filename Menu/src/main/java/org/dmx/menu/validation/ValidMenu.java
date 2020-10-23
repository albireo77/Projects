package org.dmx.menu.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MenuValidator.class)
public @interface ValidMenu {

    String message() default "";

    Class<?>[] groups() default {};

    Class <? extends Payload>[] payload() default {};
}
