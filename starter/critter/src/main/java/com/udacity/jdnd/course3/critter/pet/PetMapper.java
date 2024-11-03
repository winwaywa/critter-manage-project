package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {
    @Autowired
    private CustomerService customerService;

    public PetDTO convertToDto(Pet pet) {
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setType(pet.getType());
        dto.setName(pet.getName());
        dto.setBirthDate(pet.getBirthDate());
        dto.setNotes(pet.getNotes());
        if (pet.getOwner() != null) {
            dto.setOwnerId(pet.getOwner().getId());
        }
        return dto;
    }

    public Pet convertToEntity(PetDTO petDTO) {
        try {
            Pet pet = new Pet();
            pet.setId(petDTO.getId());
            pet.setType(petDTO.getType());
            pet.setName(petDTO.getName());
            pet.setBirthDate(petDTO.getBirthDate());
            pet.setNotes(petDTO.getNotes());
            Customer customer = customerService.getById(petDTO.getOwnerId());
            pet.setOwner(customer);
            return pet;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while convert to entity customer", e);
        }

    }
}
