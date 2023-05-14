package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;


    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAccounts() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    // WORK ON WITHDRAW AND DEPOSIT
    @Transactional
    public Transaction withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        accountRepository.save(account);
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        if (sum >= 2000) {
            double promoSum = sum * 0.10;
            if (promoSum > 500) {
                promoSum = 500;
            }
            sum += promoSum;
        }

        Account account = transactionRepository.findAccountByCbu(cbu);
        account.setBalance(account.getBalance() + sum);
        transactionRepository.save(account);

        return account;
    }

}
