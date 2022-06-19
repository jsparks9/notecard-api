package com.revature.notecard.controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.notecard.dtos.NewTaskRequest;
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
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static ObjectMapper mapper = new ObjectMapper();
    private final String name = "AuthController";

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<String> example5(@RequestBody LinkedHashMap inputMap) {

        // Prints input to console
        System.out.println("[LOG] - "+name+" received input:");
        System.out.println(new String(new char[20]).replace("\0", "*"));
        for (Object obj: inputMap.keySet()) {
            String key = obj.toString();
            String value = inputMap.get(obj).toString();
            System.out.println(key + " " + value);
        }
        System.out.println(new String(new char[20]).replace("\0", "*"));
        try {
            HashMap<String, Object> message = new HashMap<>();
            message.put("code", 204);
            message.put("message", "Logged in");
            message.put("timestamp", LocalDateTime.now().toString());
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(message));
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("Error in "+name+". Can't return anything!");
        }

        return null;
    }
}
