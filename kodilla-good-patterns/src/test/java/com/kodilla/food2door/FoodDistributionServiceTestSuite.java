package com.kodilla.food2door;

import com.kodilla.food2door.information.DiscordService;
import com.kodilla.food2door.order.OrderService;
import com.kodilla.food2door.product.ProductOrder;
import com.kodilla.food2door.shop.*;
import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class FoodDistributionServiceTestSuite {

    @DisplayName("Tests for Courier Process method with time")
    @Nested
    class ShopProcessMethod {

        @Test
        void testCourierProcessMethodWhenTimeIsBefore() {
            //given
            ExtraFoodShop extraFoodShop = new ExtraFoodShop();
            ProductOrder order = new ProductOrder(
                    extraFoodShop.getProduct().getProductType(),
                    extraFoodShop.getName(), 150, LocalTime.parse("11:00"));
            //when
            boolean actual = extraFoodShop.process(order);
            //then
            assertTrue(actual);
        }

        @Test
        void testCourierProcessMethodWhenTimeIsAfter() {
            //given
            ExtraFoodShop extraFoodShop = new ExtraFoodShop();
            ProductOrder order = new ProductOrder(
                    extraFoodShop.getProduct().getProductType(),
                    extraFoodShop.getName(), 150, LocalTime.parse("13:00"));
            //when
            boolean actual = extraFoodShop.process(order);
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
            ExtraFoodShop extraFoodShop = new ExtraFoodShop();
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
            ExtraFoodShop extraFoodShop = new ExtraFoodShop();
            ProductOrder order = new ProductOrder(extraFoodShop.getProduct().getProductType(),
                    extraFoodShop.getName(), 150, LocalTime.parse("11:00"));

            boolean isTransactionDone = extraFoodShop.process(order);
            ShopDto shopDto = new ShopDto(extraFoodShop.getName(), isTransactionDone);
            //when
            String actualCourier = shopDto.getCourierName();
            boolean actualIsTransactionDone = shopDto.isTransactionDone();
            //then
            assertEquals(extraFoodShop.getName(), actualCourier);
            assertTrue(actualIsTransactionDone);

        }
    }

    @DisplayName("Tests for Order class")
    @Nested
    class OrderClass {

        ProductOrder order, orderWithProductArgument;

        @BeforeEach
        void setUp() {
            order = new ProductOrder("Extra Food Shop", 50);
            orderWithProductArgument = new ProductOrder(
                    "Vegetable Products", "Extra Food Shop", 50);
        }

        @Test
        void testOrderShopName() {
            //when
            String actual = order.getShopName();
            //then
            assertEquals("Extra Food Shop", actual);
        }

        @Test
        void testOrderAmount() {
            //when
            int actual = order.getAmount();
            //then
            assertEquals(50, actual);
        }

        @Test
        void testOrderProductType() {
            //when
            String actual = orderWithProductArgument.getProductType();
            //then
            assertEquals("Vegetable Products", actual);
        }
    }

    @DisplayName("Tests for OrderService class")
    @Nested
    class OrderServiceClass {

        @Test
        void testOrderServiceEFS() {
            //given
            ProductOrder order = new ProductOrder("Vegetable Products",
                    "Extra Food Shop", 150, LocalTime.parse("11:00"));
            OrderService orderService = new OrderService(new DiscordService());
            ShopDto shopDto = new ShopDto(order.getShopName(), true);
            //when
            ShopDto actual = orderService.order(order);
            //then
            assertEquals(shopDto, actual);
        }

        @Test
        void testOrderServiceHS() {
            //given
            ProductOrder order = new ProductOrder("Healthy Shop", 160);
            OrderService orderService = new OrderService(new DiscordService());
            ShopDto shopDto = new ShopDto(order.getShopName(), true);
            //when
            ShopDto actual = orderService.order(order);
            //then
            assertEquals(shopDto, actual);
        }

        @Test
        void testOrderServiceGFS() {
            //given
            ProductOrder order = new ProductOrder("Gluten Free Shop", 30);
            OrderService orderService = new OrderService(new DiscordService());
            ShopDto shopDto = new ShopDto(order.getShopName(), true);
            //when
            ShopDto actual = orderService.order(order);
            //then
            assertEquals(shopDto, actual);
        }
    }
}
