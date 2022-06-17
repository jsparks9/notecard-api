package com.revature.notecard.controllers;

import com.revature.notecard.dtos.NewTaskRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
//@CrossOrigin // would have to go on top of every controller that could access another origin
// So, we just made a CORS filter
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String sanity() {
        return "/test works!";
    } // http://localhost:5000/notecard/test

    @GetMapping("/example-1")  // builds off of /test
    public String example1() {
        return "/test/example-1 works!";
    } // http://localhost:5000/notecard/test/example-1

    @GetMapping("/example-2")
    public String example2(@RequestParam String exampleKey) {
        return "exampleKey value: " + exampleKey;
    } // http://localhost:5000/notecard/test/example-2?exampleKey=

    @GetMapping("/example-3/{pathVar}")
    public String example3(@PathVariable String pathVar) {
        return "Value provided in pathVar: " + pathVar;
    }

    @GetMapping("/example-4")
    public String example4(@RequestHeader String myHeader) {
        return "Value provided in myHeader: " + myHeader;
    }

    @ResponseStatus(HttpStatus.CREATED) // 201 response code
    @PostMapping(value = "/example-5", consumes = "application/json")
    public NewTaskRequest example5(@RequestBody NewTaskRequest task) {
        task.setId(UUID.randomUUID().toString());
        return task;
    }
}
