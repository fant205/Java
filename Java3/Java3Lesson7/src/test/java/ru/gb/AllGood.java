package ru.gb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gb.annotation.AfterSuite;
import ru.gb.annotation.BeforeSuite;
import ru.gb.annotation.Test;

public class AllGood {

    private static final Logger LOGGER = LogManager.getLogger(AllGood.class);

    @BeforeSuite
    public void before(){
        LOGGER.debug("before");
    }

    @Test(priority = 1)
    public void openTheDoor(){
        LOGGER.debug("openTheDoor");
    }

    @Test(priority = 2)
    public void startEngine(){
        LOGGER.debug("startEngine");
    }

    @Test(priority = 3)
    public void go(){
        LOGGER.debug("go");
    }

    @AfterSuite
    public void after(){
        LOGGER.debug("after");
    }

}
