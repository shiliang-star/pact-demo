package kl.v2x.provider.repository;

import kl.v2x.provider.bean.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shil
 * @date 2022/6/1
 */
public interface CustomerRepository {

    List<Customer> getAllCustomers();

    void save(Customer customer);
}
