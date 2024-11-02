package org.self.threadxcelerate.Controllers;


import org.self.threadxcelerate.DTO.FileData;
import org.self.threadxcelerate.Models.Customer;
import org.self.threadxcelerate.Services.FileService;
import org.self.threadxcelerate.Services.NonBlockingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/NonBlocking")
public class NonBlockingController {


    @Autowired
    private NonBlockingService nonBlockingService;

    @Autowired
    FileService fileService;

    @GetMapping("/customers/{name}")
    public CompletableFuture<List<Customer>> getCustomerByName(@PathVariable String name) {
//        log.info("Getting customer by name {} ", name);
        CompletableFuture<List<Customer>> listCompletableFuture = nonBlockingService.getCustomerByName(name);
        return listCompletableFuture;
    }

    @PostMapping("/customers/save")
    public CompletableFuture<Customer> addCustomer(@RequestBody Customer customer) {
//        log.info("Adding user {} to the Database", customer.getName());
        return nonBlockingService.saveCustomer(customer);
    }

    @GetMapping("/fileRead")
    public CompletableFuture<String> readFile() {
//        log.info("reading file request");
        return nonBlockingService.readFile();
    }

    @PostMapping("/fileWrite")
    public CompletableFuture<Boolean> writeFile(@RequestBody FileData fileData) {
//        log.info("Write data {} to File", fileData);
        return nonBlockingService.writeFile(fileData);
    }

}
