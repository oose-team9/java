package service;

import domain.AccountDeadline;
import persistence.AccountDeadlineRepository;

import java.util.ArrayList;

public class AccountDeadlineService {
    private final AccountDeadlineRepository accountDeadlineRepository = AccountDeadlineRepository.getInstance();

    public void create(AccountDeadline deadline) {
        accountDeadlineRepository.create(deadline);
    }

    public ArrayList<AccountDeadline> read() {
        return accountDeadlineRepository.read();
    }
}
