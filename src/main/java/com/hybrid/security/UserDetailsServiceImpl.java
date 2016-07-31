package com.hybrid.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hybrid.domain.Account;
import com.hybrid.mapper.AccountMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	AccountMapper accountRepository;

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		Account account = new Account();
		account.setUserid("admin");
		account.setPassword("1234");
		account.setRole("ROLE_ADMIN");
//		account.setNick("xxx");
		
		return new UserDetailsImpl(account);
		
//		Account account = accountRepository.findByUserid(userid);
//		if (account == null) {
//	            throw new UsernameNotFoundException(userid);
//	    }
//		return new UserDetailsImpl(account);
		
	}
}