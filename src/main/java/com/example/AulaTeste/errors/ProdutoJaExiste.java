package com.example.AulaTeste.errors;

public class ProdutoJaExiste extends RuntimeException {
  private String mensagem;
  public ProdutoJaExiste() {
    this.mensagem = "O produto já existe";
  }

  public String getMensagem() {
      return mensagem;
  }

  public void setMensagem(String mensagem) {
      this.mensagem = mensagem;
  }
}