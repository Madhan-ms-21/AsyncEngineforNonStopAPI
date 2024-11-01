package org.self.threadxcelerate.Services;

import lombok.extern.slf4j.Slf4j;
import org.self.threadxcelerate.DTO.FileData;
import org.self.threadxcelerate.Models.Customer;
import org.self.threadxcelerate.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BlockingService {


    @Value("${cod.demo.file}")
    String fileName;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomerByName(String name) {
        List<Customer> customers = customerRepository.findByName(name);
        if (customers.isEmpty()) {
            return new ArrayList<>();
        }
        return customers;
    }

    public Customer addCustomer(Customer customer) {

        Customer customer2 = customerRepository.save(customer);

        return customer2;
    }

    public String readFile() {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            //br returns as stream and convert it into a List
            list = br.lines().toList();

        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for(String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

    public boolean writeFile(FileData data)  {
        log.info("Writing to file using Asynch thread");
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(data.getData());
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;


    }

}
