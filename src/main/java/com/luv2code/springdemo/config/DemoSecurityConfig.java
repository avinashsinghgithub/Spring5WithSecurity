package com.luv2code.springdemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("securityDataSource")
	private DataSource securityDataSource; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(securityDataSource);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		//.anyRequest().authenticated()
//		.antMatchers("/customer/list").hasAnyRole("EMPLOYEE","ADMIN","MANAGER")
//		.antMatchers("/customer/save*").hasAnyRole("ADMIN","MANAGER")
//		.antMatchers("/customer/delete*").hasRole("ADMIN")
//		.antMatchers("/resources/**").permitAll()
//		.and()
//		.formLogin().loginPage("/showMyLoginPage")
//		.loginProcessingUrl("/authenticateTheUser")
//		.permitAll()
//		.and()
//		.logout()
//		.permitAll()
//		.and()
//		.exceptionHandling().accessDeniedPage("/access-denied");
		
		
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		 .antMatchers("/customer/showForm*").hasAnyRole("MANAGER", "ADMIN")
		 .antMatchers("/customer/save*").hasAnyRole("MANAGER", "ADMIN")
		 .antMatchers("/customer/delete").hasRole("ADMIN")
		 .antMatchers("/customer/**").hasRole("EMPLOYEE")
		 .antMatchers("/resources/**").permitAll()
		 .and()
		 .formLogin()
		 .loginPage("/showMyLoginPage")
		.loginProcessingUrl("/authenticateTheUser")
		.permitAll()
		 .and()
		 .logout().logoutSuccessUrl("/").permitAll()
		 .and()
		 .exceptionHandling().accessDeniedPage("/access-denied");
	}
}
