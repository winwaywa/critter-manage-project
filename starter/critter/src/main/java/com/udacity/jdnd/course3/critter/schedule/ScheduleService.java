package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CustomerService customerService;

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleByPetId(Long petId) {
        try {
            return scheduleRepository.findAllByPets_Id(petId);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while retrieving schedule", e);
        }
    }

    public List<Schedule> getScheduleByEmployeeId(Long petId) {
        try {
            return scheduleRepository.findAllByEmployees_Id(petId);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while retrieving schedule", e);
        }
    }

    public List<Schedule> getSchedulesForCustomer(long customerId) {
        Customer customer = customerService.getById(customerId);
        List<Pet> pets = customer.getPets();

        return scheduleRepository.findAllByPetsIn(pets);
    }
}