package com.example.bibliotecaspringboot.controllers;


import com.example.bibliotecaspringboot.models.entities.UsuarioDTO;
import com.example.bibliotecaspringboot.models.repositories.IRepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/biblioteca/usuarios")
public class ControllerUsuario {

    IRepositoryUsuario repositoryUsuario;

    @Autowired
    public ControllerUsuario(IRepositoryUsuario repositoryUsuario) {
        this.repositoryUsuario = repositoryUsuario;
    }


    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return (List<UsuarioDTO>) repositoryUsuario.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable(value = "id") int idUsuario) {
        Optional<UsuarioDTO> usuarioOptional = repositoryUsuario.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok().body(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public UsuarioDTO saveUsuario(@Validated @RequestBody UsuarioDTO usuario) {
        usuario.setId(0);
        return repositoryUsuario.save(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") int idUsuario) {
        Optional<UsuarioDTO> usuario = repositoryUsuario.findById(idUsuario);
        if (usuario.isPresent()) {
            repositoryUsuario.deleteById(idUsuario);
            return ResponseEntity.ok().body("{\"status\": \"Usuario Eliminado\"}");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@Validated @RequestBody UsuarioDTO newUsuario,
                                           @PathVariable(value = "id") int idUsuario) {
        Optional<UsuarioDTO> usuarioOptional = repositoryUsuario.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            UsuarioDTO usuario = usuarioOptional.get();
            usuario.setNombre(newUsuario.getNombre());
            usuario.setApellidos(newUsuario.getApellidos());
            repositoryUsuario.save(usuario);
            return ResponseEntity.ok().body("{\"status\": \"Usuario Actualizado\"}");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filter")
    public List<UsuarioDTO> getFilteredUsuarios(
            @RequestParam(value = "id", required = false) int id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "apellidos", required = false) String apellidos
    ) {
        return repositoryUsuario.filter(id, nombre, apellidos);
    }
}
