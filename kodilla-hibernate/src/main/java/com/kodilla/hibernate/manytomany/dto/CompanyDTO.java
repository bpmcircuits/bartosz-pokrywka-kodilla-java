package com.kodilla.hibernate.manytomany.dto;

import java.util.List;

public record CompanyDTO (Long id, String name, List<EmployeeDTO> employees){
}
