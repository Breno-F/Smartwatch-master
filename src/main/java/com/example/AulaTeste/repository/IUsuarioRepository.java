package com.example.AulaTeste.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AulaTeste.model.UsuarioModel;

public interface IUsuarioRepository extends JpaRepository<UsuarioModel, UUID> {
  UsuarioModel findByEmail(String email);
  UsuarioModel deleteByEmail(String email);
}
