package com.mmd.config;

import com.mmd.entity.Member;
import com.mmd.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            String userName, password;
            List<GrantedAuthority> authorities = new ArrayList<>();

            Member user = memberRepository.findMemberByUsernameWithRole(username);
            if (user == null) {
                throw new UsernameNotFoundException("User details not found for the user : " + username);
            }
            if (user.getRoles() == null) {
                throw new UsernameNotFoundException("No authorities are set for the user : " + username);
            }
            userName = user.getUsername().trim();
            password = user.getPassword().trim();
            user.getRoles().forEach(role-> authorities.add(new SimpleGrantedAuthority(role.getRole().trim())));

            System.out.println(authorities);

            return new User(userName,password,authorities);
    }
}
