package com.poly.controller;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poly.dao.AuthorityDAO;
import com.poly.dao.RoleDAO;
import com.poly.entity.Authority;
import com.poly.dao.AccountDAO;

@RestController
public class AuthorityRestController {
    
	@Autowired
	AuthorityDAO authorityDAO;
	
	@Autowired
	RoleDAO roleDAO;
	
	@Autowired
	AccountDAO accountDAO;
	
	
	@GetMapping("/rest/authorities")
	public Map<String, Object> getAuthorities(){
		Map<String,Object> data = new HashMap<>();
		data.put("authorities", authorityDAO.findAll());
		data.put("roles", roleDAO.findAll());
		data.put("accounts", accountDAO.findAll());
		return data;
	}
	
	@PostMapping("/rest/authorities")
	public Authority create(@RequestBody Authority authority) {
		return authorityDAO.save(authority);
	}
	
	@DeleteMapping("/rest/authorities/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authorityDAO.deleteById(id);
	}
}
