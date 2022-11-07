package io.com.github.joaovictorjpg;

import io.com.github.joaovictorjpg.domen.entity.Cliente;
import io.com.github.joaovictorjpg.domen.entity.Pedido;
import io.com.github.joaovictorjpg.domen.repository.ClienteRepository;
import io.com.github.joaovictorjpg.domen.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner executar(@Autowired ClienteRepository clienteRepository, @Autowired PedidosRepository pedidosRepository){
        return args -> {
            System.out.println("Salvar cliente");
            Cliente cliente = clienteRepository.save(new Cliente("Claudia"));

            Pedido pedido = pedidosRepository.save(new Pedido(cliente, LocalDate.now(), BigDecimal.valueOf(10000)));

            List<Pedido> listPedidos = pedidosRepository.findByCliente(cliente);

            listPedidos.forEach(System.out::println);

            /*cliente = clienteRepository.findClienteFetchPedidos(cliente.getId());

            System.out.println(cliente);

            List<Cliente> listaCliente = clienteRepository.findAll();
            listaCliente.forEach(System.out::println);

            System.out.println("Atualizar clientes");
            listaCliente.forEach(c -> {
                c.setNome(c.getNome() + " Atualizar");
                clienteRepository.save(c);
            });
            listaCliente.forEach(System.out::println);

            System.out.println("Buscar Cliente");
            List<Cliente>listaCliente2 = clienteRepository.buscarPorNome("VIC");
            listaCliente2.forEach(System.out::println);

            System.out.println("Deletar cliente");
            clienteRepository.delete(listaCliente.get(0));
            listaCliente = clienteRepository.findAll();
            listaCliente.forEach(System.out::println);

            Boolean exists = clienteRepository.existsByNome("Victor Atualizar");
            System.out.println("Existe um cliente com o nome dia? " + exists);*/

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
