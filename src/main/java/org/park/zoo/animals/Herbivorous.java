package org.park.zoo.animals;

public interface Herbivorous {
  default void eatPlants(){
      System.out.println("Animal ate plants ");
  }

}
