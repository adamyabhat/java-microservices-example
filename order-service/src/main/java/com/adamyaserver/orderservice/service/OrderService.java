package com.adamyaserver.orderservice.service;

import com.adamyaserver.orderservice.dto.OrderItemsDto;
import com.adamyaserver.orderservice.dto.OrderRequest;
import com.adamyaserver.orderservice.model.Order;
import com.adamyaserver.orderservice.model.OrderItems;
import com.adamyaserver.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        List<OrderItems> orderItems = orderRequest.getOrderItemsDto()
                .stream()
                .map(this::mapToDto)
                .toList();
         order.setOrderItems(orderItems);
//         orderRepository.save(order);
    }

    private OrderItems mapToDto(OrderItemsDto orderItemsDto) {
        OrderItems orderItems = new OrderItems();
        orderItems.setPrice(orderItemsDto.getPrice());
        orderItems.setQuantity(orderItemsDto.getQuantity());
        orderItems.setSkuCode(orderItemsDto.getSkuCode());
        return orderItems;
    }
}