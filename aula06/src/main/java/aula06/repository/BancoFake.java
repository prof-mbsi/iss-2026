package aula06.repository;

import java.util.ArrayList;
import java.util.List;

import aula06.model.Produto; 

public class BancoFake { 

    public static List<Produto> produtos = new ArrayList<>(); 

    static { 
        produtos.add(new Produto("1", "Notebook", 3500)); 
        produtos.add(new Produto("2", "Mouse", 80)); 
    } 
} 