package com.example.javamod8springtestinglab2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/crypto")
                .hasAuthority("admin");

         http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout();

         return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailService = new InMemoryUserDetailsManager();

        String username = "juan";
        String password = "1234";
        UserDetails thisUser = User.withUsername(username)
                .password(passwordEncoder().encode(password))
                .authorities("admin")
                .build();

        // Add the User into the Services Map.
        userDetailService.createUser(thisUser);

        // Let's give Jay an account too!
        username = "jay";
        password = "1234";
        thisUser = User.withUsername(username)
                .password(passwordEncoder().encode(password))
                .authorities("admin")
                .build();

        userDetailService.createUser(thisUser);
        // What if we want to make Jay (with his more secure password)
        // an administrator?

        return userDetailService;
    }

    // Use a bean to determine which Password Encoder will be used.
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
