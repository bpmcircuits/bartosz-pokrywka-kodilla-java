package com.kodilla.patterns2.facade;

import com.kodilla.patterns2.facade.api.OrderDTO;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component("facadeWatcher")
public class Watcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Watcher.class);

    @Before(value = "execution(* com.kodilla.patterns2.facade.api.OrderFacade.processOrder(..)) && args(order, userId)", argNames = "order,userId")
    public void logEvent(OrderDTO order, Long userId) {
        LOGGER.info("Process Order method was called with order: {}, userId: {}", order, userId);
    }
}
