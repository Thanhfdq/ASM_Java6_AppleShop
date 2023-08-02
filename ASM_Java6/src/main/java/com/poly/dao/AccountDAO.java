package com.poly.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String>{

}
