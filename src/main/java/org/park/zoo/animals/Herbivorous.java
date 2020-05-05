package org.park.zoo.animals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Herbivorous {
    final Logger logger = LogManager.getLogger(Herbivorous.class);

    default void eatPlants() {
        logger.info("Animal ate plants ");
    }

}
