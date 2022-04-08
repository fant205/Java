package ru.gb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gb.annotation.BeforeSuite;
import ru.gb.annotation.Test;

public class NoAfterSuite {

    private static final Logger LOGGER = LogManager.getLogger(AllGood.class);

    @BeforeSuite
    public void before(){
        LOGGER.debug("before");
    }

    @Test(priority = 1)
    public void eat(){
        LOGGER.debug("eat");
    }






}
