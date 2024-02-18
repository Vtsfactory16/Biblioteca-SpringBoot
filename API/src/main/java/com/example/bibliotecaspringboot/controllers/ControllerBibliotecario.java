package com.example.bibliotecaspringboot.controllers;

import com.example.bibliotecaspringboot.models.entities.BibliotecariosDTO;
import com.example.bibliotecaspringboot.models.entities.CategoriaDTO;
import com.example.bibliotecaspringboot.models.entities.HistoricoDTO;
import com.example.bibliotecaspringboot.models.entities.LibroDTO;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryBibliotecarios;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryHistorico;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryPrestamos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/biblioteca/bibliotecarios")
public class ControllerBibliotecario {

    IRepositoryBibliotecarios repositoryBibliotecarios;
    IRepositoryHistorico repositoryHistorico;


    @Autowired
    public ControllerBibliotecario(IRepositoryBibliotecarios repositoryBibliotecarios, IRepositoryHistorico repositoryHistorico) {
        this.repositoryBibliotecarios = repositoryBibliotecarios;
        this.repositoryHistorico = repositoryHistorico;
    }


    @GetMapping("/{usuario}")
    public ResponseEntity<BibliotecariosDTO> getBibliotecarioByUsuario(@PathVariable(value = "usuario") String usuario) {
        Optional<BibliotecariosDTO> bibliotecario = repositoryBibliotecarios.findBibliotecariosDTOByUsuario(usuario);
        if (bibliotecario.isPresent()) {

            return ResponseEntity.ok().body(bibliotecario.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{usuario}")
    public ResponseEntity<?> updateBibliotecario(@Validated @RequestBody BibliotecariosDTO nuevaBibliotecario,
                                                 @PathVariable(value = "usuario") String usuario) {
        Optional<BibliotecariosDTO> bibliotecarios = repositoryBibliotecarios.findBibliotecariosDTOByUsuario(usuario);
        if (bibliotecarios.isPresent()) {
            BibliotecariosDTO bibliotecario = bibliotecarios.get();

            bibliotecario.setId(nuevaBibliotecario.getId());
            bibliotecario.setUsuario(nuevaBibliotecario.getUsuario());
            bibliotecario.setClave(nuevaBibliotecario.getClave());
            bibliotecario.setActivo(nuevaBibliotecario.getActivo());

            repositoryBibliotecarios.save(bibliotecario);
            if (bibliotecario.getActivo() == 1) {
                insertHistorico("El usuario " + bibliotecario.getUsuario() + " se ha conectado.", bibliotecario.getUsuario());
            } else {
                insertHistorico("El usuario " + bibliotecario.getUsuario() + " se ha desconectado.", bibliotecario.getUsuario());
            }

            return ResponseEntity.ok().body("{\"status\": \"Bibliotecario actualizado\"}");
        }
        return ResponseEntity.notFound().build();
    }


    private void insertHistorico(String mensaje) {
        String user = "admin";
        repositoryHistorico.save(new HistoricoDTO(user, Timestamp.valueOf(LocalDateTime.now()), mensaje));
    }

    private void insertHistorico(String mensaje, String usuario) {
        repositoryHistorico.save(new HistoricoDTO(usuario, Timestamp.valueOf(LocalDateTime.now()), mensaje));
    }
}
