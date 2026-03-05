package com.Bank.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.Bank.entity.enums.AccountType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts", indexes = { @Index(name = "idx_account_number", columnList = "accountNumber") })
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@Column(nullable = false, unique = true)
	private String accountNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AccountType type;

	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal balance = BigDecimal.ZERO;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	/*
	 * One account belongs to one user
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	/*
	 * One acount has many transaction
	 * 
	 */
	@OneToMany(mappedBy = "sourceAccount", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> transaction;

	public Account() {
		this.createdAt = LocalDateTime.now();
		// TODO Auto-generated constructor stub
	}

	public Account(Long id, String accountNumber, AccountType type, BigDecimal balance, LocalDateTime createdAt,
			User user, List<Transaction> transaction) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.type = type;
		this.balance = balance;
		this.createdAt = createdAt;
		this.user = user;
		this.transaction = transaction;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}



	public Account orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
