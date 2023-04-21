package pl.piomin.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piomin.base.domain.Order;
import pl.piomin.order.repository.OrderRepository;

@Service
public class OrderManageService {

//    @Autowired
//    private OrderRepository repository;
//
//    public OrderManageService(OrderRepository repository) {
//        this.repository = repository;
//    }

    public Order confirm(Order orderPayment, Order orderStock) {
        Order o = new Order(orderPayment.getId(),
                orderPayment.getCustomerId(),
                orderPayment.getProductId(),
                orderPayment.getProductCount(),
                orderPayment.getPrice());
        if (orderPayment.getStatus().equals("ACCEPT") &&
                orderStock.getStatus().equals("ACCEPT")) {
            o.setStatus("CONFIRMED");
        } else if (orderPayment.getStatus().equals("REJECT")){
                o.setStatus("REJECTED");
                o.setSource("PAYMENT");
        } else {
            o.setStatus("REJECTED");
            o.setSource("STOCK");
        }
//        repository.save(o);
        return o;
    }

}
