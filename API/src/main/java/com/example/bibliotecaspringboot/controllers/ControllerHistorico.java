package com.example.bibliotecaspringboot.controllers;


import com.example.bibliotecaspringboot.models.repositories.IRepositoryHistorico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/biblioteca/historico")
public class ControllerHistorico {

    @Autowired
    IRepositoryHistorico repositoryHistorico;

    @GetMapping
    public String getHistorico(){
        return "Historico";
    }





}
