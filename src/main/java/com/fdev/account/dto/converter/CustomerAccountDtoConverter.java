package com.fdev.account.dto.converter;

import com.fdev.account.dto.CustomerAccountDto;
import com.fdev.account.model.Account;
import org.springframework.stereotype.Component;
import java.util.Objects;
import java.util.stream.Collectors;
@Component
public class CustomerAccountDtoConverter {
    private final TransactionDtoConverter transactionDtoConverter;

    public CustomerAccountDtoConverter(TransactionDtoConverter transactionDtoConverter) {
        this.transactionDtoConverter = transactionDtoConverter;
    }
    public CustomerAccountDto convert(Account from) {
        return new CustomerAccountDto(
                Objects.requireNonNull(from.getId()),
                from.getBalance(),
                from.getTransaction()
                        .stream()
                        .map(transactionDtoConverter::convert)
                        .collect(Collectors.toSet()),
                from.getCreationDate());
    }
}
