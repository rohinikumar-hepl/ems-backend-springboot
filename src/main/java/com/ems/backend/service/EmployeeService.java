package com.ems.backend.service;

import com.ems.backend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long EmployeeId);

    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployees(Long employeeId, EmployeeDto updateEmployee);
    void deleteEmployees(Long employeeId);
    List<EmployeeDto> addManyEmployee(List<EmployeeDto> listEmployee);
}
