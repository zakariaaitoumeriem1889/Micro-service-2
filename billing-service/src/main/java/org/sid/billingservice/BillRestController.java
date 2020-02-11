package org.sid.billingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerService customerService;
    private final InventoryService inventoryService;

    public BillRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerService customerService, InventoryService inventoryService) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerService = customerService;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerService.findCustomerById(bill.getCustomerID()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(inventoryService.findProductById(productItem.getProductID()));
        });
        return bill;
    }
}
