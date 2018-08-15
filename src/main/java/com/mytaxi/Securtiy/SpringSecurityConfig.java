package com.mytaxi.Securtiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthenticationEntryPoint authEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // All requests send to the Web Server request must be authenticated
        http.authorizeRequests().anyRequest().authenticated()
            .and()
            .authorizeRequests().antMatchers("/console/**").permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();

        // Use AuthenticationEntryPoint to authenticate user/password
        http.httpBasic().authenticationEntryPoint(authEntryPoint);
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {

        String password = "123";

        String encrytedPassword = this.passwordEncoder().encode(password);
        System.out.println("Encoded password of 123=" + encrytedPassword);

        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>
            mngConfig = auth.inMemoryAuthentication();

        UserDetails u1 = User.withUsername("tom").password(encrytedPassword).roles("USER").build();
        UserDetails u2 = User.withUsername("jerry").password(encrytedPassword).roles("USER").build();

        mngConfig.withUser(u1);
        mngConfig.withUser(u2);

    }

}