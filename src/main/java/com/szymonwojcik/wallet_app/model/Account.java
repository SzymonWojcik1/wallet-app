package com.szymonwojcik.wallet_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "fromAccount")
    @JsonBackReference
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "toAccount")
    @JsonBackReference
    private List<Transaction> incomingTransactions;
}
