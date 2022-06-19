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

import static com.revature.notecard.utils.Encrypt.encrypt;

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
    public ResponseEntity<String> auth(@RequestBody LinkedHashMap inputMap) {


        // Prints input to console
        System.out.println("[LOG] - "+name+" received input:");
        System.out.println(new String(new char[20]).replace("\0", "*"));
        for (Object obj: inputMap.keySet()) {
            String key = obj.toString();
            String value = inputMap.get(obj).toString();
            System.out.println(key + " " + value);
        }
        System.out.println(new String(new char[20]).replace("\0", "*"));

        String[] regRequired = {"username", "password", "fname", "lname"};
        String[] logRequired = {"username", "password"};
        // select login or register
        String destination = "none";
        if (    inputMap.keySet().size()==2 &&
                inputMap.get("username")!=null &&
                inputMap.get("password")!=null) destination="login";
        else if (inputMap.keySet().size()==4 &&
                inputMap.get("username")!=null &&
                inputMap.get("password")!=null &&
                inputMap.get("fname")!=null &&
                inputMap.get("lname")!=null) destination="register";
        else return new ResponseEntity<String>("Bad Request", null, HttpStatus.BAD_REQUEST); // 400

        if (destination.equals("login")) {
            // Validate input first
            if (    !inputMap.get("username").toString().endsWith("@Revature.net") &&
                    !inputMap.get("username").toString().endsWith("@revature.net")) {
                return new ResponseEntity<String>("Username must end with @revature.net", null, HttpStatus.BAD_REQUEST); // 400
            }
            if (inputMap.get("username").toString().trim().equals("")) {
                return new ResponseEntity<String>("Username cannot be blank", null, HttpStatus.BAD_REQUEST); // 400
            }
            if (inputMap.get("password").toString().trim().equals("")) {
                return new ResponseEntity<String>("Password cannot be blank", null, HttpStatus.BAD_REQUEST); // 400
            }
            if (inputMap.get("password").toString().trim().contains(",")) {
                return new ResponseEntity<String>("Username cannot contain commas", null, HttpStatus.BAD_REQUEST); // 400
            }
            if (inputMap.get("password").toString().trim().contains("!")) {
                return new ResponseEntity<String>("Username cannot contain !", null, HttpStatus.BAD_REQUEST); // 400
            }
            if (inputMap.get("password").toString().trim().contains(";")) {
                return new ResponseEntity<String>("Username cannot contain ;", null, HttpStatus.BAD_REQUEST); // 400
            }
            if (inputMap.get("username").toString().length() < 15) { // username is before @, domain name is revature.net
                return new ResponseEntity<String>("Username length must be at least 2", null, HttpStatus.BAD_REQUEST); // 400
            }
            if (inputMap.get("username").toString().length() > 32) { // 32 - length("@revature.net") == 19
                return new ResponseEntity<String>("Username max length is 19", null, HttpStatus.BAD_REQUEST); // 400
            }
            if (inputMap.get("password").toString().length() < 5) {
                return new ResponseEntity<String>("Password length must be at least 5", null, HttpStatus.BAD_REQUEST); // 400
            }
            if (inputMap.get("password").toString().length() > 32) {
                return new ResponseEntity<String>("Password max length is 32", null, HttpStatus.BAD_REQUEST); // 400
            }


            Boolean found = false;
            User founduser = new User();
            for (User user: data.getUsers()) {
                System.out.println("Comparing "+user.getUsername()+" and " + inputMap.get("username").toString());
                if (user.getUsername().equalsIgnoreCase(inputMap.get("username").toString())) { // usernames are case-insensitive
                    System.out.println("Matched");
                    found = true;
                    founduser = user;
                    break;
                }
                if (!found) return new ResponseEntity<String>("User Not Found", null, HttpStatus.NOT_FOUND); // 404
                if (founduser.getPassword().equals(encrypt(inputMap.get("password").toString()))) {
                    founduser.setPassword(encrypt(founduser.getPassword()));
                    try {
                        return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(founduser)); // OK = 200
                    } catch (JsonProcessingException e) {
                        return new ResponseEntity<String>("Internal Error", null, HttpStatus.INTERNAL_SERVER_ERROR); // 500
                    }
                } else {
                    return new ResponseEntity<String>("Invalid Credentials", null, HttpStatus.UNAUTHORIZED); // 401
                }
            }
        }

        // returns a 204 with message
        try {
//            HashMap<String, Object> message = new HashMap<>();
//            message.put("code", 200);
            Boolean found = false;
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
