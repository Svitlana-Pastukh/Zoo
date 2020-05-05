package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Carnivore {

    Logger logger = LogManager.getLogger(Carnivore.class);

    default void eatMeat(){
        logger.info("Animal ate meat ");
    }
}
