package com.kodilla.good.patterns.challenges;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductShopOrderServiceTestSuite {

    private List<Item> productList;

    @BeforeEach
     void setUp() {
        productList = new ArrayList<>();
        productList.add(new Item("Notebook", 20, 10.99));
        productList.add(new Item("Lamp", 5, 54.32));
        productList.add(new Item("Computer", 1, 599.99));
    }

    @DisplayName("Tests for Item class")
    @Nested
    class ItemTests {
        private Item item;

        @BeforeEach
        void setUp() {
            item = new Item("Notebook", 20, 10.99);
        }

        @Test
        void testItemIsNotNull() {
            //when
            boolean actual = item.getName() != null;
            //then
            assertTrue(actual);
        }

        @Test
        void testItemPrice() {
            //when
            Double actual = item.getPrice();
            //then
            assertEquals(10.99, actual);
        }

        @Test
        void testItemQuantity() {
            //when
            Integer actual = item.getQuantity();
            //then
            assertEquals(20, actual);
        }
    }

    @DisplayName("Tests for ProductRequest class")
    @Nested
    class ProductRequestTests {

        @Test
        void testEmptyProductsList() {
            //given
            ProductsRequest productsRequest = new ProductsRequest(new ArrayList<>());
            //when
            List<Item> actual = productsRequest.getProductList();
            //then
            assertTrue(actual.isEmpty());
        }

        @Test
        void testProductQuantity() {
            //given
            ProductsRequest productsRequest = new ProductsRequest(productList);
            //when
            int actual = productsRequest.getProductQuantityByName("Notebook");
            //then
            assertEquals(20, actual);
        }

        @Test
        void testProductQuantityWhenWrongItem() {
            //given
            ProductsRequest productsRequest = new ProductsRequest(productList);
            //when
            int actual = productsRequest.getProductQuantityByName("No such thing");
            //then
            assertEquals(0, actual);
        }
    }

    @DisplayName("Tests for ProductsRequestRetriever class")
    @Nested
    class ProductsRequestRetrieverTests {

        @Test
        void testWhenProductsRequestIsEqual() {
            //given
            ProductsRequestRetriever productsRequestRetriever = new ProductsRequestRetriever();
            //when
            ProductsRequest productsRequestActual = productsRequestRetriever.retrieve();
            ProductsRequest productsRequestExpected = new ProductsRequest(productList);
            //then
            assertEquals(productsRequestExpected, productsRequestActual);
        }
    }

    @DisplayName("Tests for ProductOrder class")
    @Nested
    class ProductOrderTests {

        @Test
        void testNewOrder() {
            //given
            ProductOrder order = new ProductOrder("Notebook", 2);
            //when
            int actual = order.getAmount();
            //then
            assertEquals(2, actual);
        }
    }

    @DisplayName("Tests for OrderRequest")
    @Nested
    class OrderRequestTests {

        @Test
        void testOrderRequestGetAmountByName() {
            //given
            OrderRequest orderRequest = new OrderRequest(new ProductOrder("Notebook", 2));
            //when
            int actual = orderRequest.getOrderItemAmount();
            //then
            assertEquals(2, actual);
        }
    }

    @DisplayName("Tests for OrderRequestRetriever")
    @Nested
    class OrderRequestRetrieverTests {

        @Test
        void testWhenOrderRequestIsEqual() {
            //given
            OrderRequestRetriever orderRequestRetriever = new OrderRequestRetriever();
            //when
            OrderRequest orderRequestActual = new OrderRequest(new ProductOrder("Notebook", 2));
            OrderRequest orderRequestExpected = orderRequestRetriever.retrieve();
            //then
            assertEquals(orderRequestExpected, orderRequestActual);
        }
    }

    @DisplayName("Tests for ShopOrderService class")
    @Nested
    class ShopOrderServiceTests {

        @Test
        void testOrderServiceWhenAmountLessThanQuantity() {
            //given
            OrderRequest orderRequest = new OrderRequest(new ProductOrder("Notebook", 2));
            ProductsRequest productsRequest = new ProductsRequest(productList);
            ShopOrderService shopOrderService = new ShopOrderService();
            //when
            boolean isOrdered = shopOrderService.order(productsRequest, orderRequest);
            //then
            assertTrue(isOrdered);
        }

        @Test
        void testOrderServiceWhenAmountGreaterThanQuantity() {
            //given
            OrderRequest orderRequest = new OrderRequest(new ProductOrder("Notebook", 30));
            ProductsRequest productsRequest = new ProductsRequest(productList);
            ShopOrderService shopOrderService = new ShopOrderService();
            //when
            boolean isOrdered = shopOrderService.order(productsRequest, orderRequest);
            //then
            assertFalse(isOrdered);
        }
    }

    @DisplayName("Tests for OrderDto")
    @Nested
    class OrderDtoTests {
        @Test
        void testOrderDtoGetOrder() {
            //given
            ProductOrder order = new ProductOrder("Notebook", 2);
            OrderRequest orderRequest = new OrderRequest(order);
            ShopOrderService shopOrderService = new ShopOrderService();
            ProductsRequest productsRequest = new ProductsRequest(productList);
            OrderDto orderDto = new OrderDto(order, shopOrderService.order(productsRequest, orderRequest));
            //when
            ProductOrder actual = orderDto.getOrder();
            //then
            assertEquals(order, actual);
        }

        @Test
        void testOrderDtoIsOrderedTrue() {
            //given
            ProductOrder order = new ProductOrder("Notebook", 2);
            OrderRequest orderRequest = new OrderRequest(order);
            ShopOrderService shopOrderService = new ShopOrderService();
            ProductsRequest productsRequest = new ProductsRequest(productList);
            OrderDto orderDto = new OrderDto(order, shopOrderService.order(productsRequest, orderRequest));
            //when
            boolean actual = orderDto.isOrdered();
            //then
            assertTrue(actual);
        }
    }


    @DisplayName("Tests for ProductOrderService class")
    @Nested
    class ProductOrderServiceTests {
        @Test
        void testProductOrderServiceWhenOrderIsTrue() {
            //given
            ProductOrder order = new ProductOrder("Notebook", 2);
            OrderRequestRetriever orderRequestRetriever = new OrderRequestRetriever();
            OrderRequest orderRequest = orderRequestRetriever.retrieve();
            ProductsRequest productsRequest = new ProductsRequest(productList);
            OrderDto orderDto = new OrderDto(order, true);

            ProductOrderService productOrderService =
                    new ProductOrderService(new MailService(),
                            new ShopOrderService(),
                            new ShopOrderRepository());
            //when
            OrderDto actual = productOrderService.process(productsRequest, orderRequest);
            //then
            assertEquals(orderDto, actual);
        }

        @Test
        void testProductOrderServiceWhenOrderIsFalse() {
            //given
            ProductOrder order = new ProductOrder("Notebook", 30);
            OrderRequest orderRequest = new OrderRequest(order);
            ProductsRequest productsRequest = new ProductsRequest(productList);
            OrderDto orderDto = new OrderDto(order, false);

            ProductOrderService productOrderService =
                    new ProductOrderService(new MailService(),
                            new ShopOrderService(),
                            new ShopOrderRepository());
            //when
            OrderDto actual = productOrderService.process(productsRequest, orderRequest);
            //then
            assertEquals(orderDto, actual);
        }
    }
}
