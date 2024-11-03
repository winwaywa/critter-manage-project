package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long employeeId) {
        try {
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            if (employee.isPresent()) {
                Employee employeeFound = employee.get();
                return employeeFound;
            } else {
                throw new NoSuchElementException("Employee with ID " + employeeId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while retrieving employee", e);
        }
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        try {
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            if (employee.isPresent()) {
                Employee employeeFound = employee.get();
                employeeFound.setDaysAvailable(daysAvailable);
                employeeRepository.save(employeeFound);
            } else {
                throw new NoSuchElementException("Employee with ID " + employeeId + " not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while update employee", e);
        }
    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        List<Employee> availableEmployees = employeeRepository.findAllByDaysAvailableContaining(dayOfWeek);

        return availableEmployees.stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

}