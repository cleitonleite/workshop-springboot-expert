package io.githob.cleiton;

import io.githob.cleiton.domain.entity.Cliente;
import io.githob.cleiton.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println();
            System.out.println("************SALVANDO CLIENTES************");
            clientes.salvar(new Cliente("Cleiton"));
            clientes.salvar(new Cliente("Lany"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);
            System.out.println();

            System.out.println("************ATUALIZANDO CLIENTES************");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.atualizar(c);
            });

            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);
            System.out.println();

            System.out.println("************BUSCANDO CLIENTES************");
            clientes.buscarPorNome("ny").forEach(System.out::println);
            System.out.println();

            System.out.println("************DELETANDO CLIENTES************");
            clientes.obterTodos().forEach(c -> {
                clientes.deletar(c);
            });
            System.out.println();

            System.out.println("************TODOS OS CLIENTES************");
            todosClientes = clientes.obterTodos();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            }else{
                todosClientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
