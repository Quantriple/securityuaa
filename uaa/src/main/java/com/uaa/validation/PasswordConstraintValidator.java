package com.uaa.validation;

import com.uaa.annotation.ValidPassword;
import lombok.RequiredArgsConstructor;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * @author TripleQ
 * @description 密码验证规则  密码验证器
 * @date 2022/4/10 14:48:07
 * @VERSION 1.0
 **/
@RequiredArgsConstructor
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private final MessageResolver messageResolver;
    @Override
    public void initialize(ValidPassword constraintAnnotation) {


    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator passwordValidator = new PasswordValidator(messageResolver,Arrays.asList(
                new LengthRule(8,12),
                new CharacterRule(EnglishCharacterData.UpperCase,1),
                new CharacterRule(EnglishCharacterData.LowerCase,1),
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical,5,false),//连续的字符
                new IllegalSequenceRule(EnglishSequenceData.Numerical,5,false),//连续的数字
                new IllegalSequenceRule(EnglishSequenceData.USQwerty,5,false),//键盘上连续的5个
                new CharacterRule(EnglishCharacterData.Special,0),
                new WhitespaceRule()
        ));
        RuleResult validate = passwordValidator.validate(new PasswordData(password));
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(String.join(",", passwordValidator.getMessages(validate)))
                .addConstraintViolation();
        return validate.isValid();
    }
}
