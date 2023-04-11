package jmp.cloud.service.impl;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;
import jmp.service.api.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class ServiceImpl implements Service {

    private final Map<User, List<Subscription>> storage = new HashMap<>();
    @Override
    public void subscribe(BankCard bankCard) {
        User user = bankCard.getUser();
        String number = bankCard.getNumber();
        Subscription subscription = new Subscription(number, LocalDate.now());
        storage.computeIfAbsent(user, u -> new LinkedList<>()).add(subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        Predicate<Subscription> subscriptionPredicate =
                subscription -> subscription.cardNumber().equals(number);
        return getSubscriptionByBankCardNumber(subscriptionPredicate);

    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(Predicate<Subscription> filter) {
        return storage.values().stream()
                .flatMap(Collection::stream)
                .filter(filter)
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(storage.keySet());
    }
}
