package com.smartbet.demo.server.controllers.bet;

import com.smartbet.demo.bet.service.BetValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidWSUserBetInputConstraintValidator implements ConstraintValidator<ValidWsUserBetInput, WsUserBetInput> {
    private final BetValidator betValidator;

    public ValidWSUserBetInputConstraintValidator(BetValidator betValidator) {
        this.betValidator = betValidator;
    }

    @Override
    public boolean isValid(WsUserBetInput value, ConstraintValidatorContext context) {
        return betValidator.verifyBets(value.getEntries()) && value.getBetMoney() > 0d;
    }
}
