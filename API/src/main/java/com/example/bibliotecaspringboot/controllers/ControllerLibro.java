package com.example.bibliotecaspringboot.controllers;

import com.example.bibliotecaspringboot.models.entities.LibroDTO;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biblioteca/libro")
public class ControllerLibro {
    @Autowired
    IRepositoryLibro repositoryLibro;


    @GetMapping
    public List<LibroDTO> buscarEmpleados() {
        return (List<LibroDTO>) repositoryLibro.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> buscarEmpleadoPorId(@PathVariable(value = "id") int id) {
        Optional<LibroDTO> libro = repositoryLibro.findById(id);
        if (libro.isPresent())
            return  ResponseEntity.ok().body(libro.get());
        else return ResponseEntity.notFound().build();
    }
    @PostMapping
    public LibroDTO guardarEmpleado(@Validated @RequestBody LibroDTO libro) {
        return repositoryLibro.save(libro);
    }

}
