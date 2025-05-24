package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CompanyFacade {

    private static final Logger LOGGER = Logger.getLogger(CompanyFacade.class.getName());
    private final CompanyDao companyDao;
    private final EmployeeDao employeeDao;

    @Autowired
    public CompanyFacade(CompanyDao companyDao, EmployeeDao employeeDao) {
        this.companyDao = companyDao;
        this.employeeDao = employeeDao;
    }

    public void saveEmployee(Employee employee) {
        LOGGER.info("Saving employee: " + employee.getFirstName() + " " + employee.getLastName());
        employeeDao.save(employee);
    }

    public void saveCompany(Company company) {
        LOGGER.info("Saving company: " + company.getName());
        companyDao.save(company);
    }

    public List<Company> findCompanyByAnyPartOfName(String partOfName) {
        LOGGER.info("Searching for companies with name containing: " + partOfName);
        return companyDao.findByNameContaining(partOfName);
    }

    public List<Employee> findEmployeeByAnyPartOfName(String partOfName) {
        LOGGER.info("Searching for employees with name containing: " + partOfName);
        return employeeDao.findByNameContaining(partOfName);
    }

    public void deleteEmployee(Employee employee) {
        LOGGER.info("Deleting employee: " + employee.getFirstName() + " " + employee.getLastName());
        employeeDao.delete(employee);
    }

    public void deleteCompany(Company company) {
        LOGGER.info("Deleting company: " + company.getName());
        companyDao.delete(company);
    }
}
