package com.ocampo.t2.services;

import com.ocampo.t2.entities.Categoria;
import com.ocampo.t2.entities.Producto;

import java.util.List;

public interface ICategoriaService {

    public List<Categoria> findAll();

}
