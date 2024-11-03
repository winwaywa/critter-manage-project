package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerMapper.convertToEntity(customerDTO);
        Customer savedCustomer = customerService.saveCustomer(customer);

        return customerMapper.convertToDto(savedCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream()
                .map(customerMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Customer customerFinded = customerService.getCustomerByPetId(petId);
        return customerMapper.convertToDto(customerFinded);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.convertToEntity(employeeDTO);
        Employee employeeSaved = employeeService.saveEmployee(employee);
        return employeeMapper.convertToDto(employeeSaved);
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return employeeMapper.convertToDto(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable,employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employee = employeeService.findEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate());
        return employee.stream()
                .map(employeeMapper::convertToDto)
                .collect(Collectors.toList());
    }

}
