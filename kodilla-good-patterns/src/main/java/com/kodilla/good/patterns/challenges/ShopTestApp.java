package com.kodilla.good.patterns.challenges;

public class ShopTestApp {

    public static void main(String[] args) {
        OrderRequestRetriever orderRequestRetriever = new OrderRequestRetriever();
        OrderRequest orderRequest = orderRequestRetriever.retrieve();
        ProductsRequestRetriever productsRequestRetriever = new ProductsRequestRetriever();
        ProductsRequest productsRequest = productsRequestRetriever.retrieve();

        ProductOrderService productOrderService =
                new ProductOrderService(new MailService(),
                        new ShopOrderService(),
                        new ShopOrderRepository());
        productOrderService.process(productsRequest, orderRequest);
    }
}
