package com.eMarket.online.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// Comment because it is not necessary to manually generate authentification
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		BCryptPasswordEncoder bcrp = getBCRP();
		
		auth.inMemoryAuthentication().withUser("randrino17").password(bcrp.encode("1234")).roles("ADMIN", "USER", "SUPERVISOR");
		auth.inMemoryAuthentication().withUser("vanessa07").password(bcrp.encode("1234")).roles("USER", "SUPERVISOR");
		auth.inMemoryAuthentication().withUser("aurel10").password(bcrp.encode("1234")).roles("USER");
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// To disable default Security Spring
		http.csrf().disable();
		// Authentification di type STATELESS with TOKEN
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/categories/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/products/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/subcategories/**").permitAll();
		http.authorizeRequests().antMatchers("/categories/**").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/products/**").hasAuthority("USER");
		http.authorizeRequests().antMatchers("/subcategories/**").hasAuthority("USER");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	/*@Bean
	BCryptPasswordEncoder getBCRP() {
		return new BCryptPasswordEncoder();
	}*/
}
