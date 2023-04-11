package jmp.service.api;

import jmp.dto.BankCard;
import jmp.dto.Subscription;
import jmp.dto.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {
    void subscribe(BankCard bankCard);
    Optional<Subscription> getSubscriptionByBankCardNumber(String number);
    Optional<Subscription> getSubscriptionByBankCardNumber(Predicate<Subscription> filter);
    List<User> getAllUsers();

    default double getAverageUsersAge() {
        return getAllUsers().stream()
                .mapToDouble(user -> user.birthday().toEpochDay())
                .average()
                .orElse(0);
    }

    static boolean isPayable(User user) {
        return user.birthday().isBefore(LocalDate.now().minusYears(18));
    }
}
