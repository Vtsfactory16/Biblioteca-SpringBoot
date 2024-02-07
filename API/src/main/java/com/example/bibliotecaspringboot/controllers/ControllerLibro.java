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
@RequestMapping("biblioteca/libro")
public class ControllerLibro {
    @Autowired
    IRepositoryLibro repositoryLibro;


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
    @GetMapping("/{id}")
    public ResponseEntity<?> borrarLibro(@PathVariable(value = "id") int id) {
        Optional<LibroDTO> empleado = repositoryLibro.findById(id);
        if (empleado.isPresent()) {
            repositoryLibro.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> actualizarEmpleado(@RequestBody LibroDTO libroActualizado,
                                                @PathVariable(value = "id") int id) {
        Optional<LibroDTO> empleado = repositoryLibro.findById(id);
        if (empleado.isPresent()) {
            empleado.get().setNombre(libroActualizado.getNombre());
            empleado.get().setCategoria(libroActualizado.getCategoria());
            empleado.get().setAutor(libroActualizado.getAutor());
            empleado.get().setEditorial(libroActualizado.getEditorial());
            empleado.get().setPrestamos(libroActualizado.getPrestamos());
            repositoryLibro.save(empleado.get());
            return ResponseEntity.ok().body("Actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public LibroDTO guardarLibro(@Validated @RequestBody LibroDTO libro) {
        return repositoryLibro.save(libro);
    }

}
