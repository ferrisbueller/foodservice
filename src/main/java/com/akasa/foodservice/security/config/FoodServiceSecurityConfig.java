package com.akasa.foodservice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.akasa.foodservice.security.login.security.services.UserDetailsServiceImpl;
import com.akasa.foodservice.security.login.security.jwt.AuthEntryPointJwt;
import com.akasa.foodservice.security.login.security.jwt.AuthTokenFilter;


@Configuration
@EnableMethodSecurity(
	// securedEnabled = true,
	// jsr250Enabled = true,
	//prePostEnabled = true (True by default)
	)
@EnableWebSecurity
public class FoodServiceSecurityConfig {

	@Value("${spring.h2.console.path}")
	private String h2ConsolePath;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeHttpRequests()
			.requestMatchers(AntPathRequestMatcher.antMatcher("/api/auth/**")).permitAll()
			.requestMatchers(AntPathRequestMatcher.antMatcher("/api/food/**")).permitAll()
			.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-ui/**")).permitAll()
			.requestMatchers(AntPathRequestMatcher.antMatcher("/actuator/mappings/**")).permitAll()
			.requestMatchers(AntPathRequestMatcher.antMatcher("/error/**")).permitAll()
			.anyRequest().authenticated();

		// fix H2 database console: Refused to display '
		// in a frame because it set 'X-Frame-Options' to 'deny'
		http.headers().frameOptions().sameOrigin();

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJwtTokenFilter(),
			UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}