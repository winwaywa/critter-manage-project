package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Pet savePet(Pet pet) {
        Pet savedPet =  petRepository.save(pet);
        Customer owner = savedPet.getOwner();
        if (owner != null) {
            if (owner.getPets() == null) {
                owner.setPets(new ArrayList<>());
            }
            owner.getPets().add(savedPet);
            customerRepository.save(owner);
        }
        return savedPet;
    }

    public List<Pet> getPetsByOwner(Long ownerId) {
        try {
            Optional<Customer> owner = customerRepository.findById(ownerId);
            if (owner.isPresent()) {
                Customer ownerFinded = owner.get();
                return petRepository.findAllByOwner(ownerFinded);
            } else {
                throw new NoSuchElementException("Customer with ID " + ownerId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while retrieving customer", e);
        }

    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(Long petId) {
        try {
            Optional<Pet> pet = petRepository.findById(petId);
            if (pet.isPresent()) {
                return pet.get();
            } else {
                throw new NoSuchElementException("Pet with ID " + petId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while retrieving pet", e);
        }
    }
}
