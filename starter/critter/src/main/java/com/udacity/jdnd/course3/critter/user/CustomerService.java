package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByPetId(Long petId) {
        try {
            Optional<Pet> pet = petRepository.findById(petId);
            if (pet.isPresent()) {
                Pet petFound = pet.get();
                return customerRepository.findOneByPets(petFound);
            } else {
                throw new NoSuchElementException("Pet with ID " + petId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while retrieving pet", e);
        }

    }

    public Customer getById(Long customerId) {
        try {
            Optional<Customer> customer = customerRepository.findById(customerId);
            if (customer.isPresent()) {
                return customer.get();
            } else {
                throw new NoSuchElementException("Customer with ID " + customerId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while retrieving customer", e);
        }
    }
}