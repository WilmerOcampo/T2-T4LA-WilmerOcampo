package com.ocampo.t2.services;

import com.ocampo.t2.entities.Categoria;
import com.ocampo.t2.entities.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    public List<Producto> findAll();
    public Optional<Producto> findById(Integer id);

}
