package kl.v2x.provider.repository.impl;

import kl.v2x.provider.bean.Customer;
import kl.v2x.provider.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shil
 * @date 2022/6/1
 */
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {


    @Override
    public List<Customer> getAllCustomers() {
        // db operate
        return null;
    }

    @Override
    public void save(Customer customer) {
        // db operate
    }
}
