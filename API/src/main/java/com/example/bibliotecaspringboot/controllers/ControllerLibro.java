package com.example.bibliotecaspringboot.controllers;

import com.example.bibliotecaspringboot.models.entities.BibliotecariosDTO;
import com.example.bibliotecaspringboot.models.entities.HistoricoDTO;
import com.example.bibliotecaspringboot.models.entities.LibroDTO;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryBibliotecarios;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryHistorico;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biblioteca/libros")
public class ControllerLibro {
    IRepositoryLibro repositoryLibro;
    IRepositoryHistorico repositoryHistorico;
    IRepositoryBibliotecarios repositoryBibliotecarios;
    @Autowired
    public ControllerLibro(IRepositoryLibro repositoryLibro, IRepositoryHistorico repositoryHistorico,
                           IRepositoryBibliotecarios repositoryBibliotecarios) {

        this.repositoryLibro = repositoryLibro;
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
    public List<LibroDTO> getAllLibros() {
        insertHistorico("Todos los libros seleccionados");
        return (List<LibroDTO>) repositoryLibro.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> getLibroById(@PathVariable(value = "id") int id) {
        Optional<LibroDTO> libro = repositoryLibro.findById(id);
        if (libro.isPresent()) {
            insertHistorico("Libro con ID " + id + " seleccionado");
            return  ResponseEntity.ok().body(libro.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteLibro(@PathVariable(value = "id") int id) {
        Optional<LibroDTO> libro = repositoryLibro.findById(id);
        if (libro.isPresent()) {
            repositoryLibro.deleteById(id);
            insertHistorico("Libro con ID " + id + " eliminado");
            return ResponseEntity.ok().body("{\"status\": \"Libro eliminado\"}");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLibro(@Validated @RequestBody LibroDTO libroActualizado,
                                         @PathVariable(value = "id") int id) {
        Optional<LibroDTO> libroOptional = repositoryLibro.findById(id);
        if (libroOptional.isPresent()) {
            LibroDTO libro = libroOptional.get();

            libro.setNombre(libroActualizado.getNombre());
            libro.setCategoria(libroActualizado.getCategoria());
            libro.setAutor(libroActualizado.getAutor());
            libro.setEditorial(libroActualizado.getEditorial());
            libro.setPrestamos(libroActualizado.getPrestamos());
            repositoryLibro.save(libro);
            insertHistorico("Libro con ID " + id + " actualizado");
            return ResponseEntity.ok().body("{\"status\": \"Libro actualizado\"}");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public LibroDTO saveLibro(@Validated @RequestBody LibroDTO libro) {
        libro.setId(0);
        insertHistorico("Libro " + libro.getNombre() + " creado");
        return repositoryLibro.save(libro);
    }

    /**
     * Para realizar la búsqueda con OR necesaria en FichaPrestamo
     */
    @GetMapping("/filter")
    public List<LibroDTO> getFilteredLibros(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "autor", required = false) String autor,
            @RequestParam(value = "editorial", required = false) String editorial,
            @RequestParam(value = "idcategoria", required = false) Integer idCategoria
    ) {
        return repositoryLibro.filter(id,nombre,autor,editorial,idCategoria);
    }

}
