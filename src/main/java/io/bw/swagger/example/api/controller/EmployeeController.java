package io.bw.swagger.example.api.controller;

import io.bw.swagger.example.api.bo.EmployeeBO;
import io.bw.swagger.example.api.model.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Byungwook Lee on 2018-01-23
 * quddnr153@gmail.com
 * https://github.com/quddnr153
 */
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeBO employeeBO;

    @RequestMapping("/api/employee")
    public Employee getEmployee(@RequestBody final  Employee employee) {
        return employeeBO.getEmployee(employee);
    }

    @RequestMapping("/api/employee/{seq}")
    public Employee getEmployeeBySeq(@PathVariable("seq") final long seq) {
        Employee employee = Employee.builder()
                                    .withSeq(seq)
                                    .build();
        return employeeBO.getEmployee(employee);
    }

    @RequestMapping("/api/employees")
    public List<Employee> getEmployees() {
        return employeeBO.getEmployees();
    }

    @RequestMapping(value = "/api/employee", method = RequestMethod.POST)
    public String createEmployee(@RequestBody final  Employee employee) {
        employeeBO.createEmployee(employee);

        return "Success";
    }

    @RequestMapping(value = "/api/employee", method = RequestMethod.PUT)
    public String modifyEmployee(@RequestBody final  Employee employee) {
        employeeBO.modifyEmployee(employee);

        return "Success";
    }
}
