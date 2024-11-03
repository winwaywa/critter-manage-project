package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeDTO convertToDto(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSkills(employee.getSkills());
        dto.setDaysAvailable(employee.getDaysAvailable());
        return dto;
    }

    public Employee convertToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setSkills(dto.getSkills());
        employee.setDaysAvailable(dto.getDaysAvailable());
        return employee;
    }
}
