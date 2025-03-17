package com.kodilla.food2door;

import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class FoodDistributionServiceTestSuite {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("Vegetable Products", 100);
    }

    @DisplayName("Tests for Product class")
    @Nested
    class ProductClass {

        @Test
        void testGetProductType() {
            //given
            //when
            String actual = product.getProductType();
            //then
            assertEquals("Vegetable Products", actual);

        }

        @Test
        void testGetProductAmount() {
            //given
            //when
            int actual = product.getAmount();
            //then
            assertEquals(100, actual);
        }
    }

    @DisplayName("Tests for Shop abstract class")
    @Nested
    class ShopAbstractClass {

        private Courier extraFoodShop;

        @BeforeEach
        void setUp() {
            extraFoodShop = new ExtraFoodShop(LocalTime.now());
        }

        @Test
        void testGetShopName() {
            //given
            //when
            String actual = extraFoodShop.getName();
            //then
            assertEquals("Extra Food Shop", actual);
        }

        @Test
        void testShopGetProduct() {
            //given
            //when
            Product actual = extraFoodShop.getProduct();
            //then
            assertEquals(product, actual);
        }

    }

    @DisplayName("Tests for Courier Process method with time")
    @Nested
    class CourierProcessMethod {

        @Test
        void testCourierProcessMethodWhenTimeIsBefore() {
            //given
            Courier extraFoodShop = new ExtraFoodShop(LocalTime.parse("11:00"));
            //when
            boolean actual = extraFoodShop.process();
            //then
            assertTrue(actual);
        }

        @Test
        void testCourierProcessMethodWhenTimeIsAfter() {
            //given
            Courier extraFoodShop = new ExtraFoodShop(LocalTime.parse("13:00"));
            //when
            boolean actual = extraFoodShop.process();
            //then
            assertFalse(actual);
        }
    }

    @DisplayName("Tests for ShopInfo class")
    @Nested
    class ShopInfoClass {

        @Test
        void testGetDisplayInfo() {
            //given
            Courier extraFoodShop = new ExtraFoodShop(LocalTime.parse("13:00"));
            ShopInfo shopInfo = new ShopInfo(extraFoodShop);
            //when
            String actual = shopInfo.toString();
            //then
            assertEquals("Extra Food Shop # Product: Vegetable Products # Amount: 100", actual);

        }
    }

    @DisplayName("Tests for ShopDto class")
    @Nested
    class ShopDtoClass {

        @Test
        void testShopDto() {
            //given
            Courier extraFoodShop = new ExtraFoodShop(LocalTime.parse("11:00"));
            boolean isTransactionDone = extraFoodShop.process();
            ShopDto shopDto = new ShopDto(extraFoodShop.getName(), isTransactionDone);
            //when
            String actualCourier = shopDto.getCourierName();
            boolean actualIsTransactionDone = shopDto.isTransactionDone();
            //then
            assertEquals(extraFoodShop.getName(), actualCourier);
            assertTrue(actualIsTransactionDone);

        }
    }

    @DisplayName("Tests for OrderService class")
    @Nested
    class OrderServiceClass {

        @Test
        void testOrderServiceEFS() {
            //given
            Courier extraFoodShop = new ExtraFoodShop(LocalTime.parse("11:00"));
            ShopDto shopDto = new ShopDto(extraFoodShop.getName(), true);

            OrderService orderService = new OrderService(extraFoodShop, new DiscordService());
            //when
            ShopDto actual = orderService.order();
            //then
            assertEquals(shopDto, actual);
        }

        @Test
        void testOrderServiceHS() {
            //given
            Courier healthyShop = new HealthyShop(160);
            ShopDto shopDto = new ShopDto(healthyShop.getName(), true);

            OrderService orderService = new OrderService(healthyShop, new DiscordService());
            //when
            ShopDto actual = orderService.order();
            //then
            assertEquals(shopDto, actual);
        }

        @Test
        void testOrderServiceGFS() {
            //given
            Courier glutenFreeShop = new GlutenFreeShop(10);
            ShopDto shopDto = new ShopDto(glutenFreeShop.getName(), true);

            OrderService orderService = new OrderService(glutenFreeShop, new DiscordService());
            //when
            ShopDto actual = orderService.order();
            //then
            assertEquals(shopDto, actual);
        }
    }



}
