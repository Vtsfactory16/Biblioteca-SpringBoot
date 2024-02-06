package com.example.bibliotecaspringboot.controllers;


import com.example.bibliotecaspringboot.models.entities.PrestamosDTO;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryPrestamos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biblioteca/prestamos")
public class ControllerPrestamos {
    @Autowired
    IRepositoryPrestamos repositoryPrestamos;

    @GetMapping
    public List<PrestamosDTO> buscarPrestamos() {
        return (List<PrestamosDTO>) repositoryPrestamos.findAll();
    }

    @GetMapping("/{id}")
    public PrestamosDTO buscarPrestamoPorId(int idPrestamo) {
        Optional<PrestamosDTO> prestamo = repositoryPrestamos.findById(idPrestamo);
        if (prestamo.isPresent())
            return prestamo.get();
        else
            return null;
    }

    @PostMapping
    public PrestamosDTO guardarPrestamo(PrestamosDTO prestamo) {
        return repositoryPrestamos.save(prestamo);
    }

    @DeleteMapping("/{id}")
    public String borrarPrestamo(int idPrestamo) {
        Optional<PrestamosDTO> prestamo = repositoryPrestamos.findById(idPrestamo);
        if (prestamo.isPresent()) {
            repositoryPrestamos.deleteById(idPrestamo);
            return "Borrado";
        } else {
            return "No se encontro el prestamo";
        }
    }
    @PutMapping("/{id}")
    public String actualizarPrestamo(PrestamosDTO nuevoPrestamo, int idPrestamo) {
        Optional<PrestamosDTO> prestamo = repositoryPrestamos.findById(idPrestamo);
        if (prestamo.isPresent()) {
            prestamo.get().setFechaPrestamo(nuevoPrestamo.getFechaPrestamo());
            prestamo.get().setLibro(nuevoPrestamo.getLibro());
            prestamo.get().setUsuario(nuevoPrestamo.getUsuario());
            repositoryPrestamos.save(prestamo.get());
            return "Actualizado";
        } else {
            return "No se encontro el prestamo";
        }
    }

}
