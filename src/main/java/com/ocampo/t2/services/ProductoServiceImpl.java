package com.ocampo.t2.services;

import com.ocampo.t2.entities.Producto;
import com.ocampo.t2.respositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final IProductoRepository productoRepos;

    @Autowired
    public ProductoServiceImpl(IProductoRepository productoRepos) {
        this.productoRepos = productoRepos;
    }

    @Override
    public List<Producto> findAll() {
        return productoRepos.findAll();
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return productoRepos.findById(id);
        //return productRepos.findById(id).orElse(null);;
    }
}
