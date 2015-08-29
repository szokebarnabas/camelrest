package bank.infrastructure;

import bank.domain.Balance;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BalanceTypeConverter implements Converter<String, Balance>{

    @Override
    public Balance convert(String balance) {
        return Balance.of(new BigDecimal(balance));
    }
}
