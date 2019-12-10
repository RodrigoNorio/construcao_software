package com.example.trabalhocs.Controller;

import com.example.trabalhocs.Model.ModeloRecurso;

import java.util.List;

public class EstoqueController {

    private List<ModeloRecurso> recursoList;

    public EstoqueController(List<ModeloRecurso> recursoList) {
        this.recursoList = recursoList;
    }

    public List<ModeloRecurso> getRecursoList() {
        return recursoList;
    }
}
