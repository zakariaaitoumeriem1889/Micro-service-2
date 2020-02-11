package org.sid.billingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerService customerService,
                            InventoryService inventoryService) {
        return args -> {
            Customer c1 = customerService.findCustomerById(1L);
            System.out.println("*******************************");
            System.out.println("ID = " + c1.getId());
            System.out.println("Name = " + c1.getName());
            System.out.println("Email = " + c1.getEmail());
            System.out.println("*******************************");
            Bill bill = billRepository.save(new Bill(null, new Date(), c1.getId(), null, null));
            PagedModel<Product> products = inventoryService.findAllProducts();
            products.getContent().forEach(product -> {
                productItemRepository.save(new ProductItem(null, product.getId(), null, product.getPrice(), 30, bill));
            });

        };
    }

}
