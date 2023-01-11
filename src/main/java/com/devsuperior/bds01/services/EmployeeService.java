package com.devsuperior.bds01.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.DepartmentRepository;
import com.devsuperior.bds01.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository repository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAllPaged(Pageable pageable){
        Page<Employee> listPage = repository.findAll(pageable);
        
        return listPage.map(employee -> new ModelMapper().map(employee, EmployeeDTO.class));
    }

    @Transactional
    public EmployeeDTO insert(EmployeeDTO dto){
        dto.setId(null);
        Employee entity = new ModelMapper().map(dto, Employee.class);
        Department department = departmentRepository.getReferenceById(dto.getDepartmentId());
        entity.setDepartment(department);
        entity = repository.save(entity);
        dto.setId(entity.getId());
        dto.setDepartmentId(department.getId());
        return dto;
    }
}
