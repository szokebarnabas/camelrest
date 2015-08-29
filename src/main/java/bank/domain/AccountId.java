package bank.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public final class AccountId {
    private final String id;
}
