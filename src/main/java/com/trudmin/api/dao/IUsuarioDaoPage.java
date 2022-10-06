package com.trudmin.api.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.trudmin.api.model.Usuario;

public interface IUsuarioDaoPage extends JpaRepository<Usuario, Long> {

    public Page<Usuario> findAll(Pageable pageable);

}
