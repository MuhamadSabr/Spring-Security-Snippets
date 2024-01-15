package com.mmd.controller;

import com.mmd.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/contacts")
    public String getContacts(){
        return "Get contacts from the DB";
    }

}
