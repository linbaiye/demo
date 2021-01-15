package com.example.demo.model;

import lombok.Builder;

import java.util.List;
import java.util.Map;

/**
 * @author tao.lin
 * @date 2021/1/13
 */
@Builder
public class Contract {

    private final String feeMethod;

    private final Integer penaltyStartDays;

    private final String repayMethod;

    private final String penaltyMethod;

    private final Map<Event.Type, Rule> rules;

    public List<InstallmentAccountEntry> createInitializationInstallmentEntries(Loan loan) {
        return new DailyEqualPrincipleCalculator().generate(loan, new InstallmentGeneratedEvent());
    }

    public void addRule(Event.Type typ, Rule rule) {
        rules.put(typ, rule);
    }

    public List<AccountEntry> handleEvent(Loan loan, Event event) {
        return rules.get(event.type).handleEvent(loan, event);
    }

    public interface InstallmentGenerateRule {
        List<InstallmentAccountEntry> generate(Loan loan, Event event);
    }

    public interface Rule {

        List<AccountEntry> handleEvent(Loan loan, Event event);

        default void next(Loan loan, Event event) { }
    }
}
