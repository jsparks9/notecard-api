package com.revature.notecard.controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.dtos.NewTaskRequest;
import com.revature.notecard.models.User;
import com.revature.notecard.utils.MockData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static ObjectMapper mapper = new ObjectMapper();
    private final String name = "AuthController";
    private static MockData data = MockData.getInstance();

    @GetMapping(path="/data/users")
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(data.getUsers()));
        } catch (JsonProcessingException e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            //.setLocation(location);
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<String>("Internal Error", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    @GetMapping(path="/data/cards")
    public ResponseEntity getCards() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(data.getCards()));
        } catch (JsonProcessingException e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            //.setLocation(location);
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<String>("Internal Error", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    @GetMapping(path="/data/decks")
    public ResponseEntity getDecks() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(data.getDecks()));
        } catch (JsonProcessingException e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            //.setLocation(location);
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<String>("Internal Error", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }


    @PostMapping()
    public ResponseEntity<String> login(@RequestBody LinkedHashMap inputMap) {


        // Prints input to console
        System.out.println("[LOG] - "+name+" received input:");
        System.out.println(new String(new char[20]).replace("\0", "*"));
        for (Object obj: inputMap.keySet()) {
            String key = obj.toString();
            String value = inputMap.get(obj).toString();
            System.out.println(key + " " + value);
        }
        System.out.println(new String(new char[20]).replace("\0", "*"));


        // returns a 204 with message
        try {
//            HashMap<String, Object> message = new HashMap<>();
//            message.put("code", 200);
            for (User user: data.getUsers()) {

            }
            User user = new User(1,1,"test@revature.net","Tester","McTesterson","12345");
//            message.put("auth-user", user);
//            message.put("message", "Logged in");
//            message.put("timestamp", LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(user)); // OK = 200
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("Error in "+name+". Can't return anything!");
        }

        return null;
    }
}
