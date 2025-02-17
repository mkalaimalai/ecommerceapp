package com.example.customer.domain.repository; 

import com.example.customer.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final R2dbcCustomerRepository customerRepository;
    private final R2dbcAddressRepository addressRepository;

    @Override
    public Mono<Customer> save(Customer customer) {
        return customerRepository.save(customer)
                .flatMap(savedCustomer -> 
                    Flux.fromIterable(customer.getAddresses())
                        .map(address -> {
                            address.setCustomerId(savedCustomer.getId());
                            return address;
                        })
                        .flatMap(addressRepository::save)
                        .collectList()
                        .map(addresses -> {
                            savedCustomer.setAddresses(addresses);
                            return savedCustomer;
                        })
                );
    }

    @Override
    public Mono<Customer> findById(UUID id) {
        return customerRepository.findById(id)
                .flatMap(customer -> 
                    addressRepository.findByCustomerId(customer.getId())
                        .collectList()
                        .map(addresses -> {
                            customer.setAddresses(addresses);
                            return customer;
                        })
                );
    }

    @Override
    public Flux<Customer> findAll() {
        return customerRepository.findAll()
                .flatMap(customer -> 
                    addressRepository.findByCustomerId(customer.getId())
                        .collectList()
                        .map(addresses -> {
                            customer.setAddresses(addresses);
                            return customer;
                        })
                );
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return addressRepository.findByCustomerId(id)
                .flatMap(address -> addressRepository.deleteById(address.getId()))
                .then(customerRepository.deleteById(id));
    }

    @Override
    public Mono<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .flatMap(customer -> 
                    addressRepository.findByCustomerId(customer.getId())
                        .collectList()
                        .map(addresses -> {
                            customer.setAddresses(addresses);
                            return customer;
                        })
                );
    }
} 