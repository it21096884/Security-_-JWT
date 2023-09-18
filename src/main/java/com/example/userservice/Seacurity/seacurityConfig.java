package com.example.userservice.Seacurity;

import com.example.userservice.filter.CustormAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class seacurityConfig extends WebSecurityConfigurerAdapter {

 private final UserDetailsService userDetailsService;
 private final BCryptPasswordEncoder bCryptPasswordEncoder;

 protected void configure(AuthenticationManager auth)throws Exception{

     auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
 }
 protected void configure(HttpSecurity http)throws Exception{
     http.csrf().disable();
     http.sessionManagement().sessionCreationPolicy(STATELESS);
     http.authorizeRequests().anyRequest().permitAll();
     http.addFilter(new CustormAuthenticationFilter(authenticationManagerBean()));

 }
 @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
     return super.authenticationManagerBean();
 }




}
