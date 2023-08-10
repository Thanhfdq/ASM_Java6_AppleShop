package com.poly.config;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.poly.entity.Account;
import com.poly.service.AccountService;
import com.poly.service.UserService;

import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
	@Autowired
	BCryptPasswordEncoder pe;

	@Autowired
	UserService userService;

	@Autowired
	AccountService accountService;

	@Autowired
	HttpSession session;

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.requestMatchers("/order/**").authenticated()
				.requestMatchers("/admin/**").hasRole("admin").requestMatchers("/rest/authorities").hasRole("admin")
				.anyRequest().permitAll())
				.formLogin((form) -> form.loginPage("/auth/login/form").defaultSuccessUrl("/auth/login/success", false)
						.failureUrl("/auth/login/error").permitAll())
				.logout((logout) -> logout.permitAll()).rememberMe(Customizer.withDefaults())
				.exceptionHandling((handling) -> handling.accessDeniedPage("/auth/access/denied"))
				.oauth2Login((oauth2) -> oauth2.loginPage("/auth/login/form")
						.defaultSuccessUrl("/oauth2/login/success", true).failureUrl("/auth/login/error")
						.authorizationEndpoint(authorization -> authorization.baseUri("/oauth2/authorization")));
		return http.build();
	}

	public void userDetailsService(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Account user = accountService.findById(username);
				String password = pe.encode(user.getPassword());
				String[] roles = user.getAuthorities().stream().map(er -> er.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);

				Map<String, Object> authentication = new HashMap<>();
				authentication.put("user", user);
				byte[] token = (username + ":" + user.getPassword()).getBytes();
				authentication.put("token", "Basic " + Base64.getEncoder().encodeToString(token));
				session.setAttribute("authentication", authentication);

				return User.withUsername(username).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + " not found!");
			}
		});
	}
	

}