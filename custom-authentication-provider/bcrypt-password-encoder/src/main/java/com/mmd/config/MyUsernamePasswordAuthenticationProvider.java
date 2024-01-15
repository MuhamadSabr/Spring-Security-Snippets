package com.mmd.config;

import com.mmd.entity.Member;
import com.mmd.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName(), password = authentication.getCredentials().toString();
        List<GrantedAuthority> authorities = new ArrayList<>();

        Member user = memberRepository.findMemberByUsernameWithRole(authentication.getName());
        if(user!=null) {
            if (passwordEncoder.matches(password, user.getPassword().trim())) {
                user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole().trim())));
                return new UsernamePasswordAuthenticationToken(userName, password, authorities);
            } else {
                throw new BadCredentialsException("Invalid Password");
            }
        }
        else{
            throw new BadCredentialsException("No user exists with such user details");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
