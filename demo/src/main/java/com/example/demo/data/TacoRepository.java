package com.example.demo.data;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Taco;


public interface TacoRepository extends CrudRepository<Taco,Long>{
}