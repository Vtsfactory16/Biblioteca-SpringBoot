package com.example.bibliotecaspringboot.controllers;


import com.example.bibliotecaspringboot.models.entities.CategoriaDTO;
import com.example.bibliotecaspringboot.models.entities.HistoricoDTO;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryCategoria;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryHistorico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biblioteca/categorias")
public class ControllerCategoria {

    IRepositoryCategoria repositoryCategoria;
    IRepositoryHistorico repositoryHistorico;

    @Autowired
    public ControllerCategoria(IRepositoryCategoria repositoryCategoria, IRepositoryHistorico repositoryHistorico) {
        this.repositoryCategoria = repositoryCategoria;
        this.repositoryHistorico = repositoryHistorico;
    }

    private void insertHistorico(String mensaje) {
        String user = "admin";
        repositoryHistorico.save(new HistoricoDTO(user , Timestamp.valueOf(LocalDateTime.now()), mensaje));
    }

    @GetMapping
    public List<CategoriaDTO> getAllCategorias() {
        insertHistorico("Todas las categorías seleccionadas");
        return (List<CategoriaDTO>) repositoryCategoria.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable(value = "id") int idCategoria) {
        Optional<CategoriaDTO> categoria = repositoryCategoria.findById(idCategoria);
        if (categoria.isPresent()) {
            insertHistorico("Categoría con ID " + idCategoria + " seleccionada");
            return ResponseEntity.ok().body(categoria.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public CategoriaDTO saveCategoria(@Validated @RequestBody CategoriaDTO categoria) {
        categoria.setId(0);
        insertHistorico("Categoría " + categoria.getCategoria() + " creada");
        return repositoryCategoria.save(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable(value = "id") int idCategoria) {
        Optional<CategoriaDTO> categoria = repositoryCategoria.findById(idCategoria);
        if (categoria.isPresent()) {
            repositoryCategoria.deleteById(idCategoria);
            insertHistorico("Categoría " + categoria.get().getCategoria() + " eliminada");
            return ResponseEntity.ok().body("{\"status\": \"Categoria Eliminada\"}");
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoria(@Validated @RequestBody CategoriaDTO nuevaCategoria,
                                        @PathVariable(value = "id") int idCategoria) {
        Optional<CategoriaDTO> categoria = repositoryCategoria.findById(idCategoria);
        if (categoria.isPresent()) {
            categoria.get().setCategoria(nuevaCategoria.getCategoria());
            repositoryCategoria.save(categoria.get());
            insertHistorico("Categoría " + categoria.get().getCategoria() + " actualizada");
            return ResponseEntity.ok().body("{\"status\": \"Categoria Actualizada\"}");
        }
        return ResponseEntity.notFound().build();
    }
}
