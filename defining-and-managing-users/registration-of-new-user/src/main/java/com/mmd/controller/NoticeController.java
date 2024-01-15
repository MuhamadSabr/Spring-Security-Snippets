package com.mmd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

    @GetMapping("noticies")
    public String getNoticies(){
        return "get notices from the DB";
    }

}
