package com.example.bibliotecaspringboot.controllers;

import com.example.bibliotecaspringboot.models.entities.PrestamosDTO;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryPrestamos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biblioteca/prestamos")
public class ControllerPrestamos {

    IRepositoryPrestamos repositoryPrestamos;

    @Autowired
    public ControllerPrestamos(IRepositoryPrestamos repositoryPrestamos) {
        this.repositoryPrestamos = repositoryPrestamos;
    }

    @GetMapping
    public List<PrestamosDTO> getAllPrestamos() {
        return (List<PrestamosDTO>) repositoryPrestamos.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamosDTO> getPrestamoById(@PathVariable(value = "id") int idPrestamo) {
        Optional<PrestamosDTO> prestamo = repositoryPrestamos.findById(idPrestamo);
        if (prestamo.isPresent())
            return ResponseEntity.ok().body(prestamo.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public PrestamosDTO savePrestamo(@Validated @RequestBody PrestamosDTO prestamo) {
        prestamo.setIdPrestamo(0);
        return repositoryPrestamos.save(prestamo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrestamo(@PathVariable(value = "id") int idPrestamo) {
        Optional<PrestamosDTO> prestamo = repositoryPrestamos.findById(idPrestamo);
        if (prestamo.isPresent()) {
            repositoryPrestamos.deleteById(idPrestamo);
            return ResponseEntity.ok().body("{\"status\": \"Prestamo Eliminado\"}");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePrestamo(@Validated @RequestBody PrestamosDTO nuevoPrestamo,
                                            @PathVariable(value = "id") int idPrestamo) {
        Optional<PrestamosDTO> prestamo = repositoryPrestamos.findById(idPrestamo);
        if (prestamo.isPresent()) {
            prestamo.get().setFechaPrestamo(nuevoPrestamo.getFechaPrestamo());
            prestamo.get().setLibro(nuevoPrestamo.getLibro());
            prestamo.get().setUsuario(nuevoPrestamo.getUsuario());
            repositoryPrestamos.save(prestamo.get());
            return ResponseEntity.ok().body("{\"status\": \"Prestamo Actualizado\"}");
        }
        return ResponseEntity.notFound().build();
    }

}
