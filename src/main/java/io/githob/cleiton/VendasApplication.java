package io.githob.cleiton;

import io.githob.cleiton.domain.entity.Cliente;
import io.githob.cleiton.domain.repository.Clientes;
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
            clientes.save(new Cliente("Cleiton"));
            clientes.save(new Cliente("Lany"));

            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);
            System.out.println();

            System.out.println("************ATUALIZANDO CLIENTES************");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.save(c);
            });

            todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);
            System.out.println();

            System.out.println("************BUSCANDO CLIENTES************");
            clientes.findByNomeLike("ny").forEach(System.out::println);
            System.out.println();

            System.out.println("************DELETANDO CLIENTES************");
            clientes.findAll().forEach(c -> {
                clientes.delete(c);
            });
            System.out.println();

            System.out.println("************TODOS OS CLIENTES************");
            todosClientes = clientes.findAll();
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
