package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.BibliotecariosDTO;
import com.example.bibliotecaspringboot.models.entities.UsuarioDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepositoryBibliotecarios extends CrudRepository<BibliotecariosDTO, Integer> {
    Optional<BibliotecariosDTO> findBibliotecariosDTOByUsuario(String usuario);

    Optional<BibliotecariosDTO> findBibliotecariosDTOByActivo(Byte activo);

}
