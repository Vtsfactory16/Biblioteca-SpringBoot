package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.UsuarioDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositoryUsuario extends CrudRepository<UsuarioDTO, Integer> {
    @Query("select u from UsuarioDTO u " +
            "where u.id = :id OR u.nombre like %:nombre% OR u.apellidos like %:apellidos%")
    List<UsuarioDTO> filter(
            @Param("id") int id,
            @Param("nombre") String nombre,
            @Param("apellidos") String apellidos
    );
}
