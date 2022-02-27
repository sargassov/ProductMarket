package com.geekbrains.spring.web.core.services;

import com.geekbrains.spring.web.api.carts.CartDto;
import com.geekbrains.spring.web.api.core.OrderStatus;
import com.geekbrains.spring.web.api.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.api.core.OrderDetailsDto;
import com.geekbrains.spring.web.core.entities.Order;
import com.geekbrains.spring.web.core.entities.OrderItem;
import com.geekbrains.spring.web.core.exceptions.IncorrectAddressException;
import com.geekbrains.spring.web.core.integrations.CartServiceIntegration;
import com.geekbrains.spring.web.core.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductsService productsService;

    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto) {
        CartDto currentCart = cartServiceIntegration.getUserCart(username);
        Order order = new Order();
        order.setAddress(splitAddress(orderDetailsDto));
        order.setPhone(orderDetailsDto.getPhone());
        order.setUsername(username);
        order.setTotalPrice(currentCart.getTotalPrice());
        order.setStatus(OrderStatus.CREATED);
        List<OrderItem> items = currentCart.getItems().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setQuantity(o.getQuantity());
                    item.setPricePerProduct(o.getPricePerProduct());
                    item.setPrice(o.getPrice());
                    item.setProduct(productsService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
                    return item;
                }).collect(Collectors.toList());
        order.setItems(items);
        ordersRepository.save(order);
        cartServiceIntegration.clearUserCart(username);
    }

    private String splitAddress(OrderDetailsDto orderDetailsDto) {
        List<String> addressArr = List.of(orderDetailsDto.getIndex(), orderDetailsDto.getCity(),
                orderDetailsDto.getStreet(), orderDetailsDto.getHouse());


        if(addressArr.stream().anyMatch(s -> s.length() < 3)){
            throw new IncorrectAddressException("Адрес введен некорректно");
        }

        StringBuilder finalAddress = new StringBuilder();
        for(String s : addressArr){
            finalAddress.append(s);
            if(!s.equals(addressArr.get(addressArr.size() - 1)))
                finalAddress.append(", ");
        }

        return finalAddress.toString();
    }

    public List<Order> findOrdersByUsername(String username) {
        return ordersRepository.findAllByUsername(username);
    }

    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }

    public Optional<Order> findByIdAndStatus(Long id, OrderStatus status) {
        return ordersRepository.findByIdAndStatus(id, status);
    }

    public void changeStatus(Order order, OrderStatus status) {
        order.setStatus(status);
        ordersRepository.save(order);
    }
}
