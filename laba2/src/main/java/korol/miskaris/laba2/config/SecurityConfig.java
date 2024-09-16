package korol.miskaris.laba2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import korol.miskaris.laba2.model.User;
import korol.miskaris.laba2.services.UsersDetailsService;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	private final UsersDetailsService userService; 
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userService).passwordEncoder(getPasswordEncoder());
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests((requests) -> requests.requestMatchers("/admin").hasRole("ADMIN").requestMatchers("/hello").permitAll().anyRequest().authenticated()).
		//formLogin((form) -> form.loginPage("/auth/login").defaultSuccessUrl("/hello", true).failureUrl("/auth/login?error").permitAll()).
			logout((logout) -> logout.permitAll());
		return http.build();
	}
}
