package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyFacadeTest {

    @Autowired
    CompanyFacade companyFacade;

    @Test
    void testFindCompanyByAnyPartOfName() {
        // Given
        Company company1 = new Company("Software Machine");
        Company company2 = new Company("Software Warriors");
        Company company3 = new Company("Twilight Software");
        companyFacade.saveCompany(company1);
        companyFacade.saveCompany(company2);
        companyFacade.saveCompany(company3);
        String partOfName = "twa";

        // When
        List<Company> companies = companyFacade.findCompanyByAnyPartOfName(partOfName);

        // Then
        try {
            assertFalse(companies.isEmpty());
            companies.forEach(c -> assertTrue(c.getName().contains(partOfName)));
        } finally {
            companyFacade.deleteCompany(company1);
            companyFacade.deleteCompany(company2);
            companyFacade.deleteCompany(company3);
        }

    }

    @Test
    void testFindEmployeeByAnyPartOfName() {
        // Given
        Employee employee1 = new Employee("John", "Clarckson");
        Employee employee2 = new Employee("Stephanie", "Clarckson");
        Employee employee3 = new Employee("Darck", "Kovalsky");
        companyFacade.saveEmployee(employee1);
        companyFacade.saveEmployee(employee2);
        companyFacade.saveEmployee(employee3);
        String partOfName = "arck";

        // When
        List<Employee> employees = companyFacade.findEmployeeByAnyPartOfName(partOfName);

        // Then
        try {
            assertFalse(employees.isEmpty());
            employees.forEach(e -> assertTrue(e.getFirstName().contains(partOfName)
                    || e.getLastName().contains(partOfName)));
        } finally {
            companyFacade.deleteEmployee(employee1);
            companyFacade.deleteEmployee(employee2);
            companyFacade.deleteEmployee(employee3);
        }

    }
}