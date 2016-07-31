package com.hybrid.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hybrid.domain.Account;

@Mapper
public interface AccountMapper {
	Account findByUserid(String userid);
	
}
