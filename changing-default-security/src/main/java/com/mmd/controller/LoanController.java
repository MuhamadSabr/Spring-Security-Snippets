package com.mmd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping("/MyLoans")
    public String getLoansDetails(){
        return "Get loans details from the DB";
    }

}
