package io.githob.cleiton;

import io.githob.cleiton.domain.entity.Cliente;
import io.githob.cleiton.domain.entity.Pedido;
import io.githob.cleiton.domain.repository.Clientes;
import io.githob.cleiton.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
    ){
        return args -> {
            System.out.println();
            System.out.println("************SALVANDO CLIENTES************");
            Cliente fulano = new Cliente("Fulano");
            clientes.save(fulano);

            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

            pedidos.findByCliente(fulano).forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
