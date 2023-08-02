package com.poly.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.entity.Authority;
@Repository
public interface AuthorityDAO extends JpaRepository<Authority, Integer>{

}
