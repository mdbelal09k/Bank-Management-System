package com.Bank.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.Bank.entity.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable =false)
	private TransactionType type;

	
	@Column(nullable =false,precision = 15,scale=2)
	private  BigDecimal amount;

	/*
	 * source account (for withdraw / transfer)
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="source_account_id")
	private String SourceAccount;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@Column(name="target_account_id")
	private String targetAccount;
	
	@Column(name="transction_time")
	private LocalDateTime transactionTime;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(Long id, TransactionType type, BigDecimal amount, String sourceAccount, String targetAccount,
			LocalDateTime transactionTime) {
		super();
		this.id = id;
		this.type = type;
		this.amount = amount;
		SourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.transactionTime = transactionTime;
	}

	public Transaction(TransactionType withdraw, BigDecimal amount2, Account account, Object object) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSourceAccount() {
		return SourceAccount;
	}

	public void setSourceAccount(String sourceAccount) {
		SourceAccount = sourceAccount;
	}

	public String getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(String targetAccount) {
		this.targetAccount = targetAccount;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}
}



	