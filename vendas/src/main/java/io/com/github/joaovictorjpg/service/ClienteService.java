package io.com.github.joaovictorjpg.service;

import io.com.github.joaovictorjpg.model.Cliente;
import io.com.github.joaovictorjpg.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente) {
        validarCliente(cliente);
        clienteRepository.persistir(cliente);
        return cliente;
    }

    private void validarCliente(Cliente cliente) {
    }
}
