package org.park.zoo.animals;

public interface Carnivore {

    default void eatMeat(){
        System.out.println("Animal ate meat ");
    }
}
