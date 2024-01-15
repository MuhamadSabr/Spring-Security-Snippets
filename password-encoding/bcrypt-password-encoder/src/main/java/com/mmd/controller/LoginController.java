package com.mmd.controller;

import com.mmd.DTO.RegisterUserDTO;
import com.mmd.entity.Member;
import com.mmd.repository.MemberRepository;
import com.mmd.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class LoginController {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@PostMapping("/register")
	public ResponseEntity<String> registerMember(@RequestBody RegisterUserDTO userDTO){
		ResponseEntity<String> response = null;
		Member savedMember = null;

		Member member = new Member(userDTO.getUsername(), 1, LocalDateTime.now(), userDTO.getPassword(), null);
		member.setRoles(List.of(roleRepository.findById("EMPLOYEE").get()));

		try {
			String hashedPassword = passwordEncoder.encode(member.getPassword());
			member.setPassword(hashedPassword);
			savedMember = memberRepository.save(member);
			if(savedMember.getRoles()!=null){
				response = ResponseEntity.status(HttpStatus.CREATED).body("Given user details are successfully created");
			}
		}catch (Exception exc){
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occurred : " + exc.getMessage());
		}
		return response;
	}

}
