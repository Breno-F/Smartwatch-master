package com.example.AulaTeste.errors;

public class AASemEstoque extends RuntimeException {
  private String mensagem;
  public PedidoSemEstoque() {
    this.mensagem = "O pedido está esgotado";
  }

  public String getMensagem() {
      return mensagem;
  }

  public void setMensagem(String mensagem) {
      this.mensagem = mensagem;
  }
}