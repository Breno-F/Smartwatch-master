package com.example.AulaTeste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AulaTeste.errors.UsuarioJaExiste;
import com.example.AulaTeste.model.UsuarioModel;
import com.example.AulaTeste.repository.IUsuarioRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.transaction.Transactional;
@Service
public class UsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    public UsuarioModel criarUsuario(UsuarioModel userModel) {
        var userExistente = usuarioRepository.findByEmail(userModel.getEmail());
        if (userExistente != null) {
            throw new UsuarioJaExiste();
        }

        String senhaCriptografada = BCrypt.withDefaults().hashToString(12, userModel.getSenha().toCharArray());
        userModel.setSenha(senhaCriptografada);

        return usuarioRepository.save(userModel);
    }

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean autenticar(String email, String senha) {
        UsuarioModel usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) return false;

        return BCrypt.verifyer().verify(senha.toCharArray(), usuario.getSenha()).verified;
    }

    @Transactional
    public void deletarPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }
}
