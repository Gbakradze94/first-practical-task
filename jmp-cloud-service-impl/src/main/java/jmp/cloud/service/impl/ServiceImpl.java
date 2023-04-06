package jmp.cloud.service.impl;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;
import jmp.service.api.Service;

import java.util.List;
import java.util.Optional;

public class ServiceImpl implements Service {
    @Override
    public void subscribe(BankCard bankCard) {

    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}