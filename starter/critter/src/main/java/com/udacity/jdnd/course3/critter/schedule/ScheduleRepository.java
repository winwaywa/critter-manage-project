package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByPets_Id(Long petId);
    List<Schedule> findAllByEmployees_Id(Long employeeId);

    List<Schedule> findAllByPetsIn(List<Pet> pets);
}