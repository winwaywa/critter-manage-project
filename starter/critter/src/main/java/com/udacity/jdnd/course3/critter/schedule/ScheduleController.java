package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ScheduleMapper scheduleMapper;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.convertToEntity(scheduleDTO);
        Schedule scheduleSaved = scheduleService.createSchedule(schedule);
        return scheduleMapper.convertToDto(scheduleSaved);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> scheduleList = scheduleService.getAllSchedules();
        return scheduleList.stream()
                .map(scheduleMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByPetId(petId);
        return scheduleList.stream()
                .map(scheduleMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleList = scheduleService.getScheduleByEmployeeId(employeeId);
        return scheduleList.stream()
                .map(scheduleMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleList = scheduleService.getSchedulesForCustomer(customerId);
        return scheduleList.stream()
                .map(scheduleMapper::convertToDto)
                .collect(Collectors.toList());
    }
}
