package com.revature.notecard.service.token;

import com.revature.notecard.service.exceptions.TokenParseException;
import com.revature.notecard.service.dtos.Principal;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Aspect
@Component
public class SecurityAspect {

    private TokenService service;

    public SecurityAspect(TokenService service) {
        this.service = service;
    }

    @Pointcut("execution(com.revature.taskmaster..*)")
    public void thisFunctionCanBeNamedAnything() {}


    @Before("@annotation(Secured)")
    public void advice(JoinPoint joinPoint) {
        System.out.println("Did you want advice?");
        Object[] signatureArgs = joinPoint.getArgs();
        List<LinkedHashMap> canidates = new ArrayList<>();
        for (Object signatureArg : signatureArgs) {
            if (signatureArg.getClass() == LinkedHashMap.class) { //"class java.util.LinkedHashMap"
                System.out.println("Candidate found");
                canidates.add((LinkedHashMap)signatureArg);
                System.out.println("Arg: " + signatureArg.getClass());
            }
        }
        List<LinkedHashMap> finalCanidates = new ArrayList<>();
        for (LinkedHashMap cand : canidates) {
            if(cand.containsKey("authorization") && cand.get("authorization").toString().length()>0) {
                finalCanidates.add(cand);
            }
        }
        if (finalCanidates.size() != 1) throw new TokenParseException(); // TODO : custom exception
        // can get a maximum of 1 linked hashmap with a key "authorization"

        for (LinkedHashMap map: finalCanidates) {
            String tokenMaybe = ""+map.get("authorization");
            System.out.println("Final candidate: " + tokenMaybe);
//            throw new RuntimeException(); // stops it :)
            // auth with token
            Principal decoded = service.extractTokenDetails(tokenMaybe);
            System.out.println("Token decoded:" +decoded);
            // all this does so far is make sure they have exactly 1 linked hashmap with "authentication"
            // and that the token there can be decoded
            // TODO : check for roles and verify identity

            System.out.println();

        }

//        System.out.println(Arrays.stream(joinPoint.getArgs()).findFirst()); // TODO : some extraction to get auth token
//        for (String key: allHeaders.keySet()) {
//            System.out.println(key + " : " + allHeaders.get(key));
//        }

    }
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler
//    public ErrorResponse handleResourceNotFoundException(IncorrectPasswordException e) {
//        return new ErrorResponse(401, e.getMessage());
//    }
}
