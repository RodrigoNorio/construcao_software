package com.example.trabalhocs;

import com.example.trabalhocs.View.CadastrarLogin;
import com.example.trabalhocs.View.GerenciarReceita;

import junit.framework.TestCase;

public class TestUnitarioJUnitRenato extends TestCase {
    public void testDataMenorQueOutra(){
        GerenciarReceita TestData = new GerenciarReceita();
        boolean resultado = TestData.verificarmenor("14/01/2020","01/12/2020");
        assertTrue(resultado);
    }
    public void testVerificarSeADataInseridaEstaCorreta(){
        GerenciarReceita TestData = new GerenciarReceita();
        boolean resultado = TestData.verdata("01/01/2020");
        assertTrue(resultado);
    }
    public void testVerificaSenhasIguais(){
        CadastrarLogin SenhaTest = new CadastrarLogin();
        boolean resultado = SenhaTest.verificarsenha("123","123");
        assertTrue(resultado);
    }
}
