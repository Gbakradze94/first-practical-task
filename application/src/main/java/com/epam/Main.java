package com.epam;

import jmp.bank.api.Bank;
import jmp.cloud.bank.impl.BankImpl;
import jmp.cloud.service.impl.ServiceImpl;
import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.NoSuchSubscriptionException;
import jmp.dto.Subscription;
import jmp.dto.User;
import jmp.service.api.Service;
import java.time.LocalDate;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("James", "Franco", LocalDate.of(1998,11,12));
        User user2 = new User("Thomas", "Wayne", LocalDate.of(1990,1,10));
        Bank bank = new BankImpl();
        Service service = new ServiceImpl();

        BankCard creditCard = bank.createBankCard(user1, BankCardType.CREDIT);
        BankCard debitCard = bank.createBankCard(user2, BankCardType.DEBIT);
        service.subscribe(creditCard);
        service.subscribe(debitCard);

        Optional<Subscription> subscribtionByBankCardNumber = service.getSubscriptionByBankCardNumber(
                creditCard.getNumber()
        );

        Subscription subscription = subscribtionByBankCardNumber
                .orElseThrow(() -> new NoSuchSubscriptionException("Subscription does not exist"));

        subscribtionByBankCardNumber.ifPresent(System.out::println);

        double averageUsersAge = service.getAverageUsersAge();
        System.out.println("Average age is : " + averageUsersAge);
    }
}
