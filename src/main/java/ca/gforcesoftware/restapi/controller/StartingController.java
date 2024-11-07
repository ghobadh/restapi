package ca.gforcesoftware.restapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gavinhashemi on 2024-11-06
 */

/*
We use @Controller annotation to make a java class as a Spring MVC contoller
The @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse Object
The @RestController is combination of @Controller and @ResponseBody
 */
@RestController
public class StartingController {

    //HTTP GET Request
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

}
