package jmp.cloud.bank.api.bank.api;


import jmp.cloud.bank.dto.BankCard;
import jmp.cloud.bank.dto.BankCardType;
import jmp.cloud.bank.dto.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType bankCardType);
}
