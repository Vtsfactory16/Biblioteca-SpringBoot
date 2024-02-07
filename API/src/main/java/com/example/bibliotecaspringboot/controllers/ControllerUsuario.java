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

    IRepositoryUsuario usuarioRepository;

    @Autowired
    public ControllerUsuario(IRepositoryUsuario repositoryUsuario) {
        this.usuarioRepository = repositoryUsuario;
    }


    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return (List<UsuarioDTO>) usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable(value = "id") Integer idUsuario) {
        Optional<UsuarioDTO> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok().body(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public UsuarioDTO saveUsuario(@Validated @RequestBody UsuarioDTO usuario) {
        return usuarioRepository.save(usuario); // =)
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Integer idUsuario) {
        Optional<UsuarioDTO> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()) {
            usuarioRepository.deleteById(idUsuario);
            return ResponseEntity.ok().body("Usuario Borrado");
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody UsuarioDTO newUsuario,
                                           @PathVariable(value = "id") Integer idUsuario) {
        Optional<UsuarioDTO> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            UsuarioDTO usuario = usuarioOptional.get();
            usuario.setNombre(newUsuario.getNombre());
            usuario.setApellidos(newUsuario.getApellidos());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().body("Usuario Actualizado");
        }
        return ResponseEntity.notFound().build();
    }
}
