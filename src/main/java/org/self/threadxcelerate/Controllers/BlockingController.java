package org.self.threadxcelerate.Controllers;

import org.self.threadxcelerate.DTO.FileData;
import org.self.threadxcelerate.Models.Customer;
import org.self.threadxcelerate.Services.BlockingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Blocking")
public class BlockingController {

    @Autowired
    private BlockingService blockingService;

    @GetMapping("/customers/{name}")
    public List<Customer> customers(@PathVariable String name) {
        return blockingService.getCustomerByName(name);
    }

    @PostMapping("/customer")
    public Customer customer(@RequestBody Customer customer) {
        return blockingService.addCustomer(customer);
    }

    @GetMapping("/fileRead")
    public String fileRead() {
        return blockingService.readFile();
    }

    @PostMapping("/fileWrite")
    public boolean fileWrite(@RequestBody FileData fileData) {
        return blockingService.writeFile(fileData);
    }
}
