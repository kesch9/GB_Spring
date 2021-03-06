package ru.geekbrains.spring.product.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class ValuesAllowedValidator implements ConstraintValidator<ValuesAllowed, String[]> {

    private List<String> expectedValues;
    private String returnMessage;

    @Override
    public void initialize(ValuesAllowed requiredIfChecked) {
        expectedValues = Arrays.asList(requiredIfChecked.values());
        returnMessage = requiredIfChecked.message().concat(expectedValues.toString());
    }

    @Override
    public boolean isValid(String[] testValues, ConstraintValidatorContext context) {

        boolean valid = true;
        for(String value : testValues) {
            if (expectedValues.contains(value)) continue;
            valid = false;
            break;
        }
        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(returnMessage)
                    .addConstraintViolation();
        }
        return valid;
    }
}