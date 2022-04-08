package ru.gb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gb.annotation.AfterSuite;
import ru.gb.annotation.BeforeSuite;
import ru.gb.annotation.Test;

public class TwoBeforeSuite {

    private static final Logger LOGGER = LogManager.getLogger(TwoBeforeSuite.class);

    @BeforeSuite
    public void before(){
        LOGGER.debug("before");
    }

    @BeforeSuite
    public void before2(){
        LOGGER.debug("before2");
    }

    @Test(priority = 1)
    public void fly(){
        LOGGER.debug("openTheDoor");
    }

    @AfterSuite
    public void after(){
        LOGGER.debug("after");
    }

}
