package com.bank.persistence;

import com.bank.model.Account;

import java.util.Collection;

public interface AccountRepository {

    void saveAccounts(Collection<Account> accounts);

    Collection<Account> loadAccounts();

}