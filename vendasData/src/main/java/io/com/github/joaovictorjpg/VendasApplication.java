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
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
        return args -> {
            System.out.println("Salvar cliente");
            clienteRepository.salvar(new Cliente("João"));
            clienteRepository.salvar(new Cliente("Victor"));

            List<Cliente> clienteList = clienteRepository.obterClientes();
            clienteList.forEach(System.out::println);

            System.out.println("Atualizar cliente");
            clienteList.forEach( c -> {
                c.setNome(c.getNome() + " atualizado");
                clienteRepository.atualizar(c);
            });

            clienteList = clienteRepository.obterClientes();
            clienteList.forEach(System.out::println);

            System.out.println("Buscar cliente por nome");
            clienteList = clienteRepository.obterPorNome("Vic");
            clienteList.forEach(System.out::println);

            System.out.println("Deletando cliente");
            Cliente cliente = clienteList.get(0);
            clienteRepository.deletar(cliente);

            clienteList = clienteRepository.obterClientes();
            clienteList.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
