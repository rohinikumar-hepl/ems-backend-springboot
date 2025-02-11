package com.ems.backend.service;

import com.ems.backend.dto.EmployeeDto;
import com.ems.backend.entity.Employee;
import com.ems.backend.exception.ResourceNotFoundException;
import com.ems.backend.mapper.EmployeeMapper;
import com.ems.backend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl  implements  EmployeeService{


    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee is not exist with give id"));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

   @Override
   public List<EmployeeDto> getAllEmployees(){
       List<Employee> employees = employeeRepository.findAll();

       // Map Employee objects to EmployeeDto objects
       return employees.stream()
               .map(employee -> EmployeeMapper.mapToEmployeeDto(employee)) // Use the correct mapping method
               .collect(Collectors.toList());
   }

    @Override
    public EmployeeDto updateEmployees(Long employeeId, EmployeeDto updateEmployee) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee is not found"));
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());
        Employee updatedEmp = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmp);
    }

    @Override
    public void deleteEmployees(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee id not found"));

        employeeRepository.deleteById(employee.getId());
    }


}
