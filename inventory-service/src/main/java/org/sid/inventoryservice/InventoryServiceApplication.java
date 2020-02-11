package org.sid.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository,
                            RepositoryRestConfiguration restConfiguration) {
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.save(new Product(null, "Ordinateur HP 878", 8700));
            productRepository.save(new Product(null, "Ordinateur Mac Book Pro", 12000));
            productRepository.save(new Product(null, "Imprimante Epson 32", 700));
        };
    }

}
