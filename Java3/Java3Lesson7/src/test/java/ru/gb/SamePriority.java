package ru.gb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gb.annotation.AfterSuite;
import ru.gb.annotation.BeforeSuite;
import ru.gb.annotation.Test;

public class SamePriority {

    private static final Logger LOGGER = LogManager.getLogger(SamePriority.class);

    @BeforeSuite
    public void before(){
        LOGGER.debug("before");
    }

    @Test(priority = 1)
    public void fly(){
        LOGGER.debug("fly");
    }

    @Test(priority = 1)
    public void see(){
        LOGGER.debug("see");
    }

    @Test(priority = 1)
    public void sing(){
        LOGGER.debug("sing");
    }

    @AfterSuite
    public void after(){
        LOGGER.debug("after");
    }
}
