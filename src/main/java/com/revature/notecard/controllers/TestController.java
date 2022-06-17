package com.revature.notecard.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String sanity() {
        return "/test works!";
    }

    @GetMapping("/example-1")  // builds off of /test
    public String example1() {
        return "/test/example-1 works!";
    }

    @GetMapping("/example-2")
    public String example2(@RequestParam String exampleKey) {
        return "exampleKey value: " + exampleKey;
    }

}
