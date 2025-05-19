package com.example.AulaTeste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AulaTeste.model.PedidoModel;
import com.example.AulaTeste.repository.IPedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private IPedidoRepository pedidoRepository;
    @Autowired
    private EmailService emailService;


   public PedidoModel criarPedido(PedidoModel pedidoModel) {
    // Primeiro salvar o pedido no banco de dados
    PedidoModel pedidoSalvo = pedidoRepository.save(pedidoModel);
    
        try {
            emailService.enviarEmailCompra(pedidoModel.getEmailCliente(), pedidoModel.getNomeCliente());
        } catch (Exception e) {
            // Apenas logar o erro, não impedir a criação do pedido
            System.err.println("Erro ao enviar email de confirmação: " + e.getMessage());
            e.printStackTrace();
        }
    
    
    return pedidoSalvo;
}

    public List<PedidoModel> listarPedidos() {
        return pedidoRepository.findAll();
    }
}