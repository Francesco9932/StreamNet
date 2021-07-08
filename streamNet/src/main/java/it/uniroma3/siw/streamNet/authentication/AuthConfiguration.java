package it.uniroma3.siw.streamNet.authentication;

import static it.uniroma3.siw.streamNet.model.Credenziali.RUOLO_ADMIN;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource datasource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/", "/index", "/login", "/register", "/css/**", "/images/**").permitAll()
		.antMatchers(HttpMethod.POST, "/login", "/register").permitAll()
		.antMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(RUOLO_ADMIN)
		.antMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(RUOLO_ADMIN)
		.anyRequest().authenticated()
		
		.and().formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/default")
		
		.and().logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/index")
		.invalidateHttpSession(true)
		.clearAuthentication(true).permitAll();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder aut) throws Exception{
		aut.jdbcAuthentication()
		.dataSource(this.datasource)
		.authoritiesByUsernameQuery("SELECT username,ruolo FROM credenziali WHERE username=?")
		.usersByUsernameQuery("SELECT username,password, 1 as enabled FROM credenziali WHERE username =?");
		
	}
	
	 @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
