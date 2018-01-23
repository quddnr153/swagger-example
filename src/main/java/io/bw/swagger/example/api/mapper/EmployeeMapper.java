package io.bw.swagger.example.api.mapper;

import io.bw.swagger.example.api.model.Employee;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author Byungwook Lee on 2018-01-23
 * quddnr153@gmail.com
 * https://github.com/quddnr153
 */
@Repository
public interface EmployeeMapper {
    Employee selectEmployeeByName(final String name);
    Employee selectEmployeeBySeq(final long seq);
    List<Employee> selectEmployees();
    void insertEmployee(final Employee employee);
    void insertEmployeeHistory(final Employee employee);
    void updateEmployee(final Employee employee);
}
