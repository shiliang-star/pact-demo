package kl.v2x.provider.controller;

import kl.v2x.provider.bean.Customer;
import kl.v2x.provider.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shil
 * @date 2022/5/25
 */
@RestController
@RequestMapping(value = "/", produces = "application/json; charset=utf-8")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}
