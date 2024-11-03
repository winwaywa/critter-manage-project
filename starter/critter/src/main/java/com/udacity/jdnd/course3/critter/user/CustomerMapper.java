package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    public CustomerDTO convertToDto(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setNotes(customer.getNotes());
        dto.setPhoneNumber(customer.getPhoneNumber());
        if(customer.getPets() != null){
            dto.setPetIds(customer.getPets().stream()
                    .map(Pet::getId)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public Customer convertToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setNotes(dto.getNotes());
        return customer;
    }
}
