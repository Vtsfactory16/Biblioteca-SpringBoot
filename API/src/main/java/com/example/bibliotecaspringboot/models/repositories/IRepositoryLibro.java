package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.LibroDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IRepositoryLibro extends CrudRepository<LibroDTO, Integer> {
    @Query("SELECT l FROM LibroDTO l " +
            "WHERE l.id = :id OR l.nombre like %:nombre% OR l.autor like %:autor% " +
            "OR l.editorial like %:editorial% OR l.categoria.id = :idcategoria" )
    List<LibroDTO> filter(
        @Param("id") Integer id,
        @Param("nombre") String nombre,
        @Param("autor") String autor,
        @Param("editorial") String editorial,
        @Param("idcategoria") Integer idCategoria
    );
}
