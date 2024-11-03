package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ScheduleMapper {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;

    public ScheduleDTO convertToDto(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setDate(schedule.getDate());
        dto.setActivities(schedule.getActivities());
        if (schedule.getEmployees() != null) {
            dto.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        }
        if (schedule.getPets() != null) {
            dto.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        }
        return dto;
    }

    public Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        try {
            Schedule schedule = new Schedule();
            schedule.setId(scheduleDTO.getId());
            schedule.setDate(scheduleDTO.getDate());
            schedule.setActivities(scheduleDTO.getActivities());
            if (scheduleDTO.getEmployeeIds() != null) {
                schedule.setEmployees(scheduleDTO.getEmployeeIds().stream()
                        .map(employeeService::getEmployeeById)
                        .collect(Collectors.toList()));
            }
            if (scheduleDTO.getPetIds() != null) {
                schedule.setPets(scheduleDTO.getPetIds().stream()
                        .map(petService::getPetById)
                        .collect(Collectors.toList()));
            }
            return schedule;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while convert to entity schedule", e);
        }

    }
}
