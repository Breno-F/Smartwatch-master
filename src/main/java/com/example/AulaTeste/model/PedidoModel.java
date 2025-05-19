package com.example.AulaTeste.model;


import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity(name = "tb_pedidos")
public class PedidoModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(name = "codigo_pedido", unique = true)
    private String codigo;

    @Column(name = "nome_cliente", unique = true)
    private String NomeCliente;

    @Column(name = "email_cliente", unique = true)
    private String EmailCliente;

    @Column(name = "valorcompra_user")
    private String valor;

    @Column(name = "enderecodestino_pedido")
    private String enderecod;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public PedidoModel() {}

    public PedidoModel(String codigo, String valor, String enderecod, String NomeCliente, String EmailCliente) {
        this.codigo = codigo;
        this.valor = valor;
        this.enderecod = enderecod;
        this.NomeCliente = NomeCliente;
        this.EmailCliente = EmailCliente;
    }
}    