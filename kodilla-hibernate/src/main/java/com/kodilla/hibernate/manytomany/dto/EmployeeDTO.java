package com.kodilla.hibernate.manytomany.dto;

import java.util.List;

public record EmployeeDTO(Long id, String firstName, String lastName, List<CompanyDTO> companies) {
}
