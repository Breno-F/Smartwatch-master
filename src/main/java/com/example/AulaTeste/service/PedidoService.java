package com.example.AulaTeste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AulaTeste.errors.PedidoSemEstoque;
import com.example.AulaTeste.model.PedidoModel;
import com.example.AulaTeste.repository.IPedidoRepository;

import jakarta.transaction.Transactional;
@Service
public class PedidoService {
    @Autowired
    private IPedidoRepository pedidoRepository;

    public PedidoModel criarPedido(PedidoModel pedidoModel) {
        var pedidoExistente = pedidoRepository.findByPedido(pedidoModel.getCodigo());
        if (pedidoExistente != null) {
            throw new PedidoSemEstoque();
        }

        return pedidoRepository.save(pedidoModel);
    }

    public List<PedidoModel> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public PedidoModel buscarPorPedido(String codigo) {
        return pedidoRepository.findByPedido(codigo);
    }

    @Transactional
    public void deletarPorPedido(String codigo) {
        pedidoRepository.deleteByPedido(codigo);
    }
}
