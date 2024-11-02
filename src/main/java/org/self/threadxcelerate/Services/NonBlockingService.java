package org.self.threadxcelerate.Services;

import org.self.threadxcelerate.DTO.FileData;
import org.self.threadxcelerate.Models.Customer;
import org.self.threadxcelerate.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NonBlockingService {

    @Autowired
    private CustomerRepository CustomerRepository;


    @Autowired
    FileService fileService;

    @Async
    public CompletableFuture<List<Customer>> getCustomerByName(String name) {

        List<Customer> customerList = CustomerRepository.findByName(name);
        return CompletableFuture.completedFuture(customerList);
    }

    @Async
    public CompletableFuture<Customer> saveCustomer(Customer customer) {
//        log.info("Saving customer {} using Asynch thread", customer.getName());
        Customer newCustomer = CustomerRepository.save(customer);
        return CompletableFuture.completedFuture(newCustomer);
    }

    @Async
    public CompletableFuture<String> readFile() {
//        log.info("Reading the file using Asynch thread");
        String fileData = fileService.readFile();
        return CompletableFuture.completedFuture(fileData);
    }

    @Async
    public CompletableFuture<Boolean> writeFile(FileData fileData) {
//        log.info("Writing to file using Asynch thread");
        boolean result = fileService.writeFile(fileData);
        return CompletableFuture.completedFuture(result);
    }
}
