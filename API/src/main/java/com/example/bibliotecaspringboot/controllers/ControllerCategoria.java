package com.example.bibliotecaspringboot.controllers;


import com.example.bibliotecaspringboot.models.entities.CategoriaDTO;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biblioteca/categorias")
public class ControllerCategoria {

    @Autowired
    IRepositoryCategoria repositoryCategoria;


    @GetMapping
    public List<CategoriaDTO> buscarCategorias() {
        return (List<CategoriaDTO>) repositoryCategoria.findAll();
    }

    @GetMapping("/{id}")
    public CategoriaDTO buscarCategoriaPorId(int idCategoria) {
        Optional<CategoriaDTO> categoria = repositoryCategoria.findById(idCategoria);
        if (categoria.isPresent())
            return categoria.get();
        else
            return null;
    }

    @PostMapping
    public CategoriaDTO guardarCategoria(CategoriaDTO categoria) {
        return repositoryCategoria.save(categoria);
    }

    @DeleteMapping("/{id}")
    public void borrarCategoria(int idCategoria) {
        Optional<CategoriaDTO> categoria = repositoryCategoria.findById(idCategoria);
        if (categoria.isPresent()) {
            repositoryCategoria.deleteById(idCategoria);
        }

    }

    @PutMapping("/{id}")
    public CategoriaDTO actualizarCategoria(CategoriaDTO nuevaCategoria, int idCategoria) {
        Optional<CategoriaDTO> categoria = repositoryCategoria.findById(idCategoria);
        if (categoria.isPresent()) {
            categoria.get().setCategoria(nuevaCategoria.getCategoria());
            repositoryCategoria.save(categoria.get());
            return categoria.get();
        } else {
            return null;
        }
    }
}
