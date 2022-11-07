package io.com.github.joaovictorjpg.rest.controller;

import io.com.github.joaovictorjpg.domen.entity.Cliente;
import io.com.github.joaovictorjpg.domen.repository.ClienteRepository;
import io.swagger.annotations.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Api("API Clientes")
public class ClienteController {

    /*
        consumes é o tipo de formatação que vamos receber
        produces é o tipo de formatação que vamos retornar
            JSON ou XML

        @RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET)
        @ResponseBody
        public String helloCliente(@PathVariable("nome") String nomeCliente) {
            return String.format("Holla %s ", nomeCliente);
        }

     */

    ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado")
    })
    public Cliente getClienteById(@PathVariable @ApiParam("Id do cliente") Integer id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cadastrar cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
            @ApiResponse(code = 404, message = "Erro de validação")
    })
    public Cliente save( @RequestBody @Valid Cliente cliente ) {
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Void delete(@PathVariable Integer id) {
        clienteRepository.findById(id).map(cliente ->{
                clienteRepository.delete(cliente);
                return Void.class;
                }).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
        clienteRepository.findById(id).map(clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            clienteRepository.save(cliente);
            return clienteExistente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não foi encontrado"));
    }

    @GetMapping
    public List<Cliente> find(@RequestBody Cliente filter) {

        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, matcher);
        return clienteRepository.findAll(example);
    }

}
