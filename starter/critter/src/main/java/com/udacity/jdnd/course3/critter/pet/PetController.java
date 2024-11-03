package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PetMapper petMapper;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petMapper.convertToEntity(petDTO);
        Pet petSaved = petService.savePet(pet);
        return petMapper.convertToDto(petSaved);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        return petMapper.convertToDto(pet);
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<Pet> petList = petService.getAllPets();
        return petList.stream()
                .map(petMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> petList = petService.getPetsByOwner(ownerId);
        return petList.stream()
                .map(petMapper::convertToDto)
                .collect(Collectors.toList());
    }
}
