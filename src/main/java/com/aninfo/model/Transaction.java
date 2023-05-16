package com.aninfo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Transaction {
    public enum TransactionType {
        DEPOSIT,
        WITHDRAW
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double amount;

    private String type;

    @ManyToOne
    @JoinColumn(name = "cbu")
    private Account account;

    public Transaction(){
    }

    public Transaction(Double amount, String type){
        this.amount = amount;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}