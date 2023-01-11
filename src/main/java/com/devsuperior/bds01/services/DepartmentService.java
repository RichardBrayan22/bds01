package com.devsuperior.bds01.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.repositories.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<DepartmentDTO>findAll(){
        List<Department> departments = repository.findAll(Sort.by("name"));
        return departments.stream().map(department -> new ModelMapper().map(department, DepartmentDTO.class)).collect(Collectors.toList());
    }
    
}
