package io.com.github.joaovictorjpg;

import io.com.github.joaovictorjpg.domen.entity.Cliente;
import io.com.github.joaovictorjpg.domen.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner executar(@Autowired ClienteRepository clienteRepository) {
        return args -> {

            System.out.println("Salvar cliente");
            Cliente c1 = clienteRepository.Salvar(new Cliente("João"));
            Cliente c2 = clienteRepository.Salvar(new Cliente("Victor"));
            List<Cliente> listaCliente = clienteRepository.obterTodos();
            listaCliente.forEach(System.out::println);

            System.out.println("Atualizar cliente");
            listaCliente.forEach( c -> {
                c.setNome(c.getNome() + " Atualizado");
                clienteRepository.atualizar(c);
            });
            listaCliente.forEach(System.out::println);

            System.out.println("Buscar cliente por nome");
            listaCliente = clienteRepository.buscarPorNome("Vic");
            listaCliente.forEach(System.out::println);

            System.out.println("Deletando cliente");
            Cliente cliente = listaCliente.get(0);
            clienteRepository.deletar(cliente);
            listaCliente.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
