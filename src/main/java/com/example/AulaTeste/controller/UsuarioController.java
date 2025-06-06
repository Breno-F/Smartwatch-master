package com.example.AulaTeste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AulaTeste.errors.UsuarioJaExiste;
import com.example.AulaTeste.model.UsuarioModel;
import com.example.AulaTeste.service.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar_usuario")
    public ResponseEntity<?> criarUsuario(@RequestBody UsuarioModel userModel) {
        try {
            var user = usuarioService.criarUsuario(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (UsuarioJaExiste e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/todos_usuarios")
    public ResponseEntity<List<UsuarioModel>> getAllUsers() {
        var users = usuarioService.listarUsuarios();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/buscar_usuario")
    public ResponseEntity<UsuarioModel> getUser(@RequestParam String email) {
        var user = usuarioService.buscarPorEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login_usuario")
    public ResponseEntity<?> login(@RequestBody UsuarioModel userModel) {
        boolean autenticado = usuarioService.autenticar(userModel.getEmail(), userModel.getSenha());
        if (autenticado) {
            return ResponseEntity.ok("Autenticação bem-sucedida");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorreta");
    }

    @DeleteMapping("/deletar_usuario")
    public ResponseEntity<Void> deletUser(@RequestParam String email) {
        usuarioService.deletarPorEmail(email);
        return ResponseEntity.ok().build();
    }
}