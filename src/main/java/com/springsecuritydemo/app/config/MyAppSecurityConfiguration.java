package com.springsecuritydemo.app.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class MyAppSecurityConfiguration {
	
	@Autowired
	HttpSecurity httpSecurity;
	
	//@Bean
//	public InMemoryUserDetailsManager setupUser() {
		
//		ArrayList<GrantedAuthority> authoritiesList = new ArrayList<>();
		
//		SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("admin");
//		SimpleGrantedAuthority role2 = new SimpleGrantedAuthority("user");
		
//		authoritiesList.add(role1);
//		authoritiesList.add(role2);

//		UserDetails user = new User("Jahnavi","Jahnavi",authoritiesList);
//		UserDetails user2 = new User("Sudha","Sudha",authoritiesList);
		
//		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
		
//		inMemoryUserDetailsManager.createUser(user);
//		inMemoryUserDetailsManager.createUser(user2);
		
//		return inMemoryUserDetailsManager;
//	}
	@Bean
	public SecurityFilterChain setupHttpSecurity() throws Exception {
		
	//	httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
	//	httpSecurity.formLogin();
		//httpSecurity.formLogin().loginPage("/mycustomlogin");
	//	httpSecurity.httpBasic();
		
		httpSecurity.authorizeHttpRequests(
				customizer->{
					customizer.requestMatchers("/sayHi","/sayBye").permitAll();
					customizer.anyRequest().authenticated();
				});
		httpSecurity.formLogin(Customizer.withDefaults());
		httpSecurity.httpBasic(Customizer.withDefaults());		
		
		return httpSecurity.build();
	}
		
	@Bean
	public InMemoryUserDetailsManager setupUser() {
		
		UserDetails user1 = User
				.withUsername("Jahnavi")
				.password("Jahnavi")
				.roles("admin","user")
				.build();
			
		UserDetails user2 = User.withUsername("Sudha")
				.password("Sudha")
				.roles("user")
				.build();
		return new InMemoryUserDetailsManager(user1,user2);
	}
	
	//Instead of using password encoder we can use {noop} just befor the password..
	//spring will automatically convert it to NoOpPasswordEncoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean(name="mvcHandlerMappingIntrospector")
	public HandlerMappingIntrospector MappingIntrospector() {
		return new HandlerMappingIntrospector();
	}
}
