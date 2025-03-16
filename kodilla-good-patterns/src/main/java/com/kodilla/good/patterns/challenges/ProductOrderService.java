package com.kodilla.good.patterns.challenges;

public class ProductOrderService {

    private final InformationService informationService;
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public ProductOrderService(final InformationService informationService,
                           final OrderService orderService,
                           final OrderRepository orderRepository) {
        this.informationService = informationService;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }


    public OrderDto process(ProductsRequest productsRequest, OrderRequest orderRequest) {
        boolean isOrdered = orderService.order(productsRequest, orderRequest);
        if (isOrdered) {
            informationService.inform(orderRequest.getOrder().getName(), orderRequest.getOrderItemAmount());
            orderRepository.createOrder(orderRequest.getOrder().getName(), orderRequest.getOrderItemAmount());
            return new OrderDto(orderRequest.getOrder(), true);
        } else {
            return new OrderDto(orderRequest.getOrder(), false);
        }
    }
}
