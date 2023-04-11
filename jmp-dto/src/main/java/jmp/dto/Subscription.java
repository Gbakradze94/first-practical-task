package jmp.dto;

import java.time.LocalDate;

public record Subscription(String cardNumber, LocalDate startDate) {
}
