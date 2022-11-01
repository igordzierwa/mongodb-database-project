package com.agh.northwindproject.Orders;

import com.agh.northwindproject.Customers.Customer;
import com.agh.northwindproject.Customers.CustomersRepository;
import com.agh.northwindproject.Employees.Employee;
import com.agh.northwindproject.Employees.EmployeesRepository;
import com.agh.northwindproject.Products.Product;
import com.agh.northwindproject.Products.ProductsRepository;
import com.agh.northwindproject.Shippers.Shipper;
import com.agh.northwindproject.Shippers.ShippersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;
import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private ShippersRepository shippersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping(value = "/api/orders")
    @ResponseBody
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(ordersRepository.findAll());
    }

    @GetMapping(value = "/api/order/{orderId}")
    @ResponseBody
    public ResponseEntity<Order> getOrder(@PathVariable String orderId){
        Order order = ordersRepository.findById(orderId).orElse(null);
        return ResponseEntity.ok(order);
    }

    @PostMapping(value = "/api/order")
    @ResponseBody
    public ResponseEntity<String> addNewOrder(@RequestBody OrderRequestBody orderRequestBody){
        Customer customer = customersRepository.findByCompanyName(orderRequestBody.getCustomerCompanyName());
        if (customer == null) {
            return ResponseEntity.ok("\"status\": \"customer does not exists\"");
        }
        Employee employee = employeesRepository.findByLastNameAndFirstName(orderRequestBody.getEmployeeLastName(),
                orderRequestBody.getEmployeeFirstName());
        if (employee == null) {
            return ResponseEntity.ok("\"status\": \"employee does not exists\"");
        }
        Shipper shipper = shippersRepository.findByCompanyName(orderRequestBody.getShipperCompanyName());
        if (shipper == null) {
            return ResponseEntity.ok("\"status\": \"shipper does not exists\"");
        }

        Order order = new Order(orderRequestBody);
        order.setCustomerID(customer.getId());
        order.setEmployeeID(employee.getId());
        order.setShipperID(shipper.getId());
        order.setOrderDate( Calendar.getInstance().getTime());
        System.out.println("dsadsasdasadasd");
        //System.console().writer().println("Dsaasdadsdsadasasd");
        for(OrderDetailsRequestBody orderDetailsRequestBody : orderRequestBody.getOrderDetails()) {
            Product product = productsRepository.findByProductName(orderDetailsRequestBody.getProductName());
            if(product != null && product.isDiscontinued() == false) {
                if(product.getUnitsInStock() >= orderDetailsRequestBody.getQuantity()) {


                    if(product.getUnitsInStock() - orderDetailsRequestBody.getQuantity() >= 0) {

                        OrderDetails orderDetails = new OrderDetails(product.getProductName(), orderDetailsRequestBody);
                        orderDetails.setUnitPrice(product.getUnitPrice() - orderDetails.getDiscount());
                        order.getOrderDetails().add(orderDetails);
                        product.setUnitsInOrder(product.getUnitsInOrder() + orderDetailsRequestBody.getQuantity());
                        product.setUnitsInStock(product.getUnitsInStock() - orderDetailsRequestBody.getQuantity());
                        productsRepository.save(product);
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"status\": \"not enough units in stock\": " + product.getProductName());
                    }
                } else {
                    System.out.println(product.getQuantityPerUnit() + "dasadsads" + orderDetailsRequestBody.getQuantity());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"status\": \"invalid quantityPerUnit\": " + product.getProductName());
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"status\": \"product does not exists\": " + product.getProductName());
            }
        }
        ordersRepository.save(order);
        return ResponseEntity.ok("\"status\": \"added\"");
    }

    @GetMapping(value = "/api/order/{customerCompanyName}/{employeeLastName}/{employeeFirstName}")
    @ResponseBody
    public ResponseEntity<List<Order>> getOrdersByCustomerAndEmployee(@PathVariable String customerCompanyName,
                                                                      @PathVariable String employeeLastName,
                                                                      @PathVariable String employeeFirstName){
        Customer customer = customersRepository.findByCompanyName(customerCompanyName);
        Employee employee = employeesRepository.findByLastNameAndFirstName(employeeLastName, employeeFirstName);
        if (customer != null && employee != null) {
            return ResponseEntity.ok(ordersRepository.findByCustomerIDAndEmployeeID(customer.getId(), employee.getId()));
        }
        return null;
    }

    @DeleteMapping(value = "/api/order/{orderID}")
    @ResponseBody
    public ResponseEntity<String> deleteOrder(@PathVariable String orderID){
        Order order = ordersRepository.findById(orderID).orElse(null);
        if(order != null){
            ordersRepository.delete(order);
            return ResponseEntity.ok("\"status\": \"removed\"");
        }
        return ResponseEntity.ok("\"status\": \"order not exists\"");
    }
}
