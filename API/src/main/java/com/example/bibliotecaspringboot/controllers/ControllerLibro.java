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
@RequestMapping("/biblioteca/libros")
public class ControllerLibro {
    IRepositoryLibro repositoryLibro;
    @Autowired
    public ControllerLibro(IRepositoryLibro repositoryLibro) {
        this.repositoryLibro = repositoryLibro;
    }


    @GetMapping
    public List<LibroDTO> buscarLibros() {
        return (List<LibroDTO>) repositoryLibro.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> buscarLibroPorId(@PathVariable(value = "id") int id) {
        Optional<LibroDTO> libro = repositoryLibro.findById(id);
        if (libro.isPresent())
            return  ResponseEntity.ok().body(libro.get());
        else return ResponseEntity.notFound().build();
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> borrarLibro(@PathVariable(value = "id") int id) {
        Optional<LibroDTO> libro = repositoryLibro.findById(id);
        if (libro.isPresent()) {
            repositoryLibro.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@Validated @RequestBody LibroDTO libroActualizado,
                                                @PathVariable(value = "id") int id) {
        Optional<LibroDTO> libroOptional = repositoryLibro.findById(id);
        if (libroOptional.isPresent()) {
            LibroDTO libro = libroOptional.get();

            libro.setNombre(libroActualizado.getNombre());
            libro.setCategoria(libroActualizado.getCategoria());
            libro.setAutor(libroActualizado.getAutor());
            libro.setEditorial(libroActualizado.getEditorial());
            libro.setPrestamos(libroActualizado.getPrestamos());
            repositoryLibro.save(libroOptional.get());
            return ResponseEntity.ok().body("Actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public LibroDTO guardarLibro(@Validated @RequestBody LibroDTO libro) {
        libro.setId(0);
        return repositoryLibro.save(libro);
    }

}
