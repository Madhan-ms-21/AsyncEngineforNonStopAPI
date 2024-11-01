package org.self.threadxcelerate.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/NonBlocking")
public class NonBlockingController {

    @GetMapping("/customers")
    public String customers() {
        return "Customers";
    }

    @PostMapping("/customer")
    public String customer() {
        return "Customers";
    }

    @GetMapping("/fileRead")
    public String fileRead() {
        return "FileRead";
    }

    @PostMapping("/fileWrite")
    public String fileWrite() {
        return "FileWrite";
    }

}
