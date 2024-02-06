package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.CategoriaDTO;
import org.springframework.data.repository.CrudRepository;

public interface IRepositoryCategoria extends CrudRepository<CategoriaDTO, Integer> {


}
