package jmp.cloud.bank.impl;

import jmp.bank.api.Bank;
import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.CreditBankCard;
import jmp.dto.DebitBankCard;
import jmp.dto.User;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;

public class BankImpl implements Bank {

    private final Map<BankCardType, BiFunction<String, User, BankCard>> cardCreators;

    public BankImpl() {
        cardCreators = new HashMap<>();
        cardCreators.put(BankCardType.CREDIT, CreditBankCard::new);
        cardCreators.put(BankCardType.DEBIT, DebitBankCard::new);
    }

    @Override
    public BankCard createBankCard(User user, BankCardType cardType) {
        String number = UUID.randomUUID().toString();

        return cardCreators
                .getOrDefault(cardType, (num, us) -> throwIfTypeIsUnknown())
                .apply(number, user);
    }

    private BankCard throwIfTypeIsUnknown() {
        throw new IllegalArgumentException("Unknown credit type");
    }
}

