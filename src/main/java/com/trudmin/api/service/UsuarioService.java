package com.trudmin.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trudmin.api.dao.IUsuarioDao;
import com.trudmin.api.dao.IUsuarioDaoPage;
import com.trudmin.api.dto.UsuarioDTO;
import com.trudmin.api.model.Usuario;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UsuarioService {

    private static final Logger LOG = Logger.getLogger(UsuarioService.class.getName());

    @Autowired
    IUsuarioDao usuarioDao;

    @Autowired
    IUsuarioDaoPage usuarioDaoPage;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    ModelMapper modelMapper = new ModelMapper();

    public <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    } 

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> list = usuarioDao.obtenerUsuario();
        return list;
    }

    public Page<UsuarioDTO> obtenerUsuariosPage(Pageable pageable) {
        Page<Usuario> entity = usuarioDaoPage.findAll(pageable);
        Page<UsuarioDTO> usuarioDto = mapEntityPageIntoDtoPage(entity, UsuarioDTO.class);
        return usuarioDto;

    }

    public Usuario obtenerUsuarioId(long id) {
        Usuario comprador = usuarioDao.obtenerUsuarioId(id);
        return comprador;
    }

    public UsuarioDTO registrarUsuario(Usuario usuario) {

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setId(UUID.randomUUID().hashCode());
        usuario.setEstatus(true);
        usuario.setFechaCreacion(new Date());

        Usuario usuarioNew = usuarioDao.registrarUsuario(usuario);
        UsuarioDTO usuarioDto = new UsuarioDTO();
        modelMapper.map(usuarioNew, usuarioDto);
        return usuarioDto;
    }

    public Usuario actualizarUsuario(Usuario comprador) {
        Usuario compradorNew = usuarioDao.actualizarUsuario(comprador);
        return compradorNew;
    }

    public void eliminarUsuario(long id) {
        LOG.info("Id de Usuario a eliminar: " + id);
        usuarioDao.eliminarUsuario(id);
    }

}
