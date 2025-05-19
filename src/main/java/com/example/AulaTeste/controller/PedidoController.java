package com.example.AulaTeste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AulaTeste.errors.PedidoSemEstoque;
import com.example.AulaTeste.model.PedidoModel;
import com.example.AulaTeste.service.PedidoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/criar_pedido")
    public ResponseEntity<?> criarPedido(@RequestBody PedidoModel pedidoModel) {
        try {
            var pedido = pedidoService.criarPedido(pedidoModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
        } catch (PedidoSemEstoque e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/todos_pedidos")
    public ResponseEntity<List<PedidoModel>> getAllPedidos() {
        var pedidos = pedidoService.listarPedidos();
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(pedidos);
    }
}