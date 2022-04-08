package ru.gb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.gb.annotation.AfterSuite;
import ru.gb.annotation.BeforeSuite;
import ru.gb.annotation.Test;

public class TwoAfterSuite {

    private static final Logger LOGGER = LogManager.getLogger(TwoAfterSuite.class);

    @BeforeSuite
    public void before(){
        LOGGER.debug("before");
    }

    @Test(priority = 1)
    public void openTheDoor(){
        LOGGER.debug("openTheDoor");
    }

    @AfterSuite
    public void after(){
        LOGGER.debug("after");
    }

    @AfterSuite
    public void after2(){
        LOGGER.debug("after2");
    }
}
