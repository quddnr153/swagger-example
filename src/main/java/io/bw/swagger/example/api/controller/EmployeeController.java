package io.bw.swagger.example.api.controller;

import io.bw.swagger.example.api.bo.EmployeeBO;
import io.bw.swagger.example.api.model.Employee;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Byungwook Lee on 2018-01-23
 * quddnr153@gmail.com
 * https://github.com/quddnr153
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeBO employeeBO;

    @ApiOperation(value = "employee 조회")
    @ApiImplicitParam(name = "seq", value = "employee seq", required = true, dataType = "long", paramType = "path", defaultValue = "1")
    @GetMapping(value = "/employee/{seq}")
    public Employee getEmployeeBySeq(@PathVariable("seq") final long seq) {
        Employee employee = Employee.builder()
                                    .withSeq(seq)
                                    .build();
        return employeeBO.getEmployee(employee);
    }

    @ApiOperation(value = "employee 전체조회")
    @GetMapping(value = "/employee")
    public List<Employee> getEmployees() {
        return employeeBO.getEmployees();
    }

    @ApiOperation(value = "employee 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "employee id", required = true, dataType = "String", paramType = "form", example = "TEST001"),
            @ApiImplicitParam(name = "name", value = "employee name", required = true, dataType = "String", paramType = "form", example = "tester"),
            @ApiImplicitParam(name = "password", value = "employee password", required = true, dataType = "String", paramType = "form", example = "test001")
    })
    @PostMapping(value = "/employee")
    public String createEmployee(@RequestParam(value = "id") final String id,
                                 @RequestParam(value = "name") final String name,
                                 @RequestParam(value = "password") final String password) {
        employeeBO.createEmployee(Employee.builder().withId(id).withName(name).withPassword(password).withStatus("USE").build());

        return "Success";
    }

    @ApiOperation(value = "employee 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seq", value = "employee seq", required = true, dataType = "long", paramType = "form", example = "1"),
            @ApiImplicitParam(name = "name", value = "employee name", dataType = "String", paramType = "form", example = "tester"),
            @ApiImplicitParam(name = "password", value = "employee password", dataType = "String", paramType = "form", example = "test001"),
            @ApiImplicitParam(name = "status", value = "employee status", dataType = "String", paramType = "form", example = "USE or DISUSE")
    })
    @PutMapping(value = "/employee")
    public String modifyEmployee(@RequestParam(value = "seq") final long seq,
                                 @RequestParam(value = "name") final String name,
                                 @RequestParam(value = "password") final String password,
                                 @RequestParam(value = "status") final String status) {
        employeeBO.modifyEmployee(Employee.builder().withSeq(seq).withName(name).withPassword(password).withStatus(status).build());

        return "Success";
    }
}
