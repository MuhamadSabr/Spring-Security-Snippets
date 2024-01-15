package com.mmd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean(name = "MySecurityFilterChain")
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/noticies", "/contacts", "/register").permitAll()
                .anyRequest().authenticated())
        .formLogin(withDefaults())
        .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }


//    @Bean
//    JdbcUserDetailsManager userDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }




    //    @Bean(name = "myInMemoryUserDetailsManager")
//    public InMemoryUserDetailsManager userDetailsManager(){
//
//        UserDetails mmd = User
//                .withDefaultPasswordEncoder()
//                .username("mmd")
//                .password("mmd")
//                .roles("read", "admin")
//                .build();
//
//        UserDetails test= User
//                .withDefaultPasswordEncoder()
//                .username("test")
//                .password("test")
//                .authorities("read")
//                .build();
//
//        return new InMemoryUserDetailsManager(mmd, test);
//    }

//    @Bean(name = "myInMemoryUserDetailsManager")
//    public InMemoryUserDetailsManager userDetailsManager(){
//
//        UserDetails mmd = User
//                .builder()
//                .username("mmd")
//                .password("mmd")
//                .roles("read", "admin")
//                .build();
//
//        UserDetails test= User
//                .builder()
//                .username("test")
//                .password("test")
//                .authorities("read")
//                .build();
//
//        return new InMemoryUserDetailsManager(mmd, test);
//    }

}
