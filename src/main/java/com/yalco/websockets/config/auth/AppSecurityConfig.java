package com.yalco.websockets.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index_medium.html",true)
                .failureUrl("/login.html?error=true");

//        http.authorizeRequests()
//                .mvcMatchers("/api/admin")
//                .hasRole("ADMIN")
//                .mvcMatchers("/api/*")
//                .hasRole("USER")
//                .and()
//                .httpBasic();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("bill")
                .password("bill1")
                .roles("USER")
                .build();

        UserDetails user2 = User.withUsername("john")
                .password("john1")
                .roles("USER")
                .build();

        UserDetails user3 = User.withUsername("ellie")
                .password("ellie1")
                .roles("USER")
                .build();

        UserDetails user4 = User.withUsername("kim")
                .password("kim1")
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("admin")
                .roles("ADMIN","USER")
                .build();

        uds.createUser(user);
        uds.createUser(user2);
        uds.createUser(user3);
        uds.createUser(user4);
        uds.createUser(admin);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
