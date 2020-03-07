package org.adenaden.tutorial.jvm.workshop.service;

import org.adenaden.tutorial.jvm.workshop.model.Registration;
import org.adenaden.tutorial.jvm.workshop.entity.Account;
import org.adenaden.tutorial.jvm.workshop.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Account create(Registration registration) {
		Account account = new Account();
		account.setName(registration.getName());
		account.setEmail(registration.getEmail());
		account.setPassword(System.currentTimeMillis() + "");
		return create(account);
	}
	
	public Account create(Account account) {
		return accountRepository.save(account);
	}
	
	public Account findByEmail(String email){
		return accountRepository.findTopByEmail(email);
	}
	public Account findByEmailAndPassword(String email, String password){
		return accountRepository.findByEmailAndPassword(email, password);
	}
	
}
