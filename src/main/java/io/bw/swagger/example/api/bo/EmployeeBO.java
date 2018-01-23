package io.bw.swagger.example.api.bo;

import io.bw.swagger.example.api.model.Employee;

import java.util.List;

/**
 * @author Byungwook Lee on 2018-01-23
 * quddnr153@gmail.com
 * https://github.com/quddnr153
 */
public interface EmployeeBO {
    Employee getEmployee(final Employee employee);
    List<Employee> getEmployees();
    void createEmployee(final Employee employee);
    void modifyEmployee(final Employee employee);
}
