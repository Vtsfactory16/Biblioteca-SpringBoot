package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.PrestamosDTO;
import org.springframework.data.repository.CrudRepository;


public interface IRepositoryPrestamos extends CrudRepository<PrestamosDTO, Integer> {

    PrestamosDTO findByIdPrestamo(int idPrestamo);



}
