package com.example.bibliotecaspringboot.controllers;

import com.example.bibliotecaspringboot.models.entities.BibliotecariosDTO;
import com.example.bibliotecaspringboot.models.entities.HistoricoDTO;
import com.example.bibliotecaspringboot.models.entities.PrestamosDTO;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryBibliotecarios;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryHistorico;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryPrestamos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biblioteca/prestamos")
public class ControllerPrestamos {

    IRepositoryPrestamos repositoryPrestamos;
    IRepositoryHistorico repositoryHistorico;
    IRepositoryBibliotecarios repositoryBibliotecarios;
    @Autowired
    public ControllerPrestamos(IRepositoryPrestamos repositoryPrestamos, IRepositoryHistorico repositoryHistorico,
                               IRepositoryBibliotecarios repositoryBibliotecarios) {
        this.repositoryPrestamos = repositoryPrestamos;
        this.repositoryHistorico = repositoryHistorico;
        this.repositoryBibliotecarios = repositoryBibliotecarios;
    }
    private void insertHistorico(String mensaje) {
        String user="";
        Optional<BibliotecariosDTO> bibliotecario = repositoryBibliotecarios.findBibliotecariosDTOByActivo((byte)1);
        if (bibliotecario.isPresent()) {
            user = bibliotecario.get().getUsuario();
        }
        repositoryHistorico.save(new HistoricoDTO(user , Timestamp.valueOf(LocalDateTime.now()), mensaje));
    }

    @GetMapping
    public List<PrestamosDTO> getAllPrestamos() {
        insertHistorico("Todos los prestamos seleccionados");
        return (List<PrestamosDTO>) repositoryPrestamos.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamosDTO> getPrestamoById(@PathVariable(value = "id") int idPrestamo) {
        Optional<PrestamosDTO> prestamo = repositoryPrestamos.findById(idPrestamo);
        if (prestamo.isPresent()){
            insertHistorico("Prestamo con ID " + idPrestamo + " seleccionado");
            return ResponseEntity.ok().body(prestamo.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public PrestamosDTO savePrestamo(@Validated @RequestBody PrestamosDTO prestamo) {
        prestamo.setIdPrestamo(0);
        insertHistorico("Prestamo con ID " + prestamo.getIdPrestamo() + " creado");
        return repositoryPrestamos.save(prestamo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePrestamo(@PathVariable(value = "id") int idPrestamo) {
        Optional<PrestamosDTO> prestamo = repositoryPrestamos.findById(idPrestamo);
        if (prestamo.isPresent()) {
            repositoryPrestamos.deleteById(idPrestamo);
            insertHistorico("Prestamo con ID " + idPrestamo + " eliminado");
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
            insertHistorico("Prestamo con ID " + idPrestamo + " actualizado");
            return ResponseEntity.ok().body("{\"status\": \"Prestamo Actualizado\"}");
        }
        return ResponseEntity.notFound().build();
    }

}
