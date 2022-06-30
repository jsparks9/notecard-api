package com.revature.notecard.service.token;

import com.revature.notecard.tables.User;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class KnownRoleValidator implements ConstraintValidator<KnownRole, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return (value == null) || Arrays.stream(User.Role.values())
                .map(User.Role::name)
                .collect(Collectors.toList())
                .contains(value);
    }
}
