package io.bw.swagger.example.api.bo;

import io.bw.swagger.example.api.mapper.EmployeeMapper;
import io.bw.swagger.example.api.model.Employee;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Byungwook Lee on 2018-01-23
 * quddnr153@gmail.com
 * https://github.com/quddnr153
 */
@Service
public class EmployeeBOImpl implements EmployeeBO {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getEmployee(final Employee employee) {
        if (StringUtils.isNotEmpty(employee.getEmployeeId())) {
            throw new NotImplementedException("getEmployeeById is Not Implemented yet");
        }

        if (StringUtils.isNotEmpty(employee.getEmployeeName())) {
            return employeeMapper.selectEmployeeByName(employee.getEmployeeName());
        }

        return employeeMapper.selectEmployeeBySeq(employee.getEmployeeSeq());
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeMapper.selectEmployees();
    }

    @Transactional
    public void createEmployee(final Employee employee) {
        employeeMapper.insertEmployee(employee);
        employeeMapper.insertEmployeeHistory(employee);
    }

    @Transactional
    public void modifyEmployee(final Employee employee) {
        employeeMapper.updateEmployee(employee);
        employeeMapper.insertEmployeeHistory(employee);
    }
}
