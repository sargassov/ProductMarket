package market.services;


import lombok.RequiredArgsConstructor;
import market.dto.Cart;
import market.dto.OrderDetailsDto;
import market.entities.Order;
import market.entities.OrderItem;
import market.entities.User;
import market.exceptions.ResourceNotFoundException;
import market.repositories.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final CartService cartService;
    private final ProductsService productsService;

    @Transactional
    public void createOrder(User user, OrderDetailsDto orderDetailsDto) {
        String cartKey = cartService.getCartUuidFromSuffix(user.getUsername());
        Cart currentCart = cartService.getCurrentCart(cartKey);
        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUser(user);
        order.setTotalPrice(currentCart.getTotalPrice());
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
        cartService.clearCart(cartKey);
    }

    public List<Order> findOrdersByUsername(String username) {
        return ordersRepository.findAllByUsername(username);
    }
}
