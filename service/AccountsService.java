package service;

import domain.Accounts;
import persistence.AccountsRepository;

import java.util.ArrayList;

public class AccountsService {
    private final AccountsRepository accountsRepository = AccountsRepository.getInstance();

    public void create(Accounts account) {
        accountsRepository.create(account);
    }

    public ArrayList<Accounts> read() {
        return accountsRepository.read();
    }

    public int delete(String[] ids) {
        return accountsRepository.delete(ids);
    }
}
