package com.ocampo.t2.services;

import com.ocampo.t2.entities.Categoria;
import com.ocampo.t2.respositories.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private final ICategoriaRepository categoriaRepos;

    @Autowired
    public CategoriaServiceImpl(ICategoriaRepository categoriaRepos) {
        this.categoriaRepos = categoriaRepos;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepos.findAll();
    }
}
