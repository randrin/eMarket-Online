package com.eMarket.online.security;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.ImmutableList;

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
		//http.cors(); //adds your custom CorsFilter
		// To disable default Security Spring
		http.csrf().disable();
		// Authentification di type STATELESS with TOKEN
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.cors().and().authorizeRequests().antMatchers(HttpMethod.GET, "/emarketCategories/**").permitAll();
		http.cors().and().authorizeRequests().antMatchers(HttpMethod.GET, "/emarketProducts/**").permitAll();
		http.cors().and().authorizeRequests().antMatchers(HttpMethod.GET, "/emarketSubCategories/**").permitAll();
		http.cors().and().authorizeRequests().antMatchers("/emarketCategories/**").hasAuthority("ADMIN");
		http.cors().and().authorizeRequests().antMatchers("/emarketProducts/**").hasAuthority("USER");
		http.cors().and().authorizeRequests().antMatchers("/emarketSubCategories/**").hasAuthority("USER");
		http.cors().and().authorizeRequests().anyRequest().authenticated();
		http.headers().frameOptions().disable();
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	/*@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        //configuration.setAllowedOrigins(ImmutableList.of("http://localhost:8080","http://localhost:8084"));
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
	}*/
	
	/*@Bean
	BCryptPasswordEncoder getBCRP() {
		return new BCryptPasswordEncoder();
	}*/
}
