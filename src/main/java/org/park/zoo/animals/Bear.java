package org.park.zoo.animals;


public class Bear extends Animal implements Herbivorous,Carnivore {
    String color;
    private boolean hibernating;

    public Bear(String name, int age, String country, int minTemperature, int maxTemperature, int weight, String color) {
        super(name, age, country, minTemperature, maxTemperature, weight);
        this.color = color;
    }

    public void startHibernate() {
        System.out.println("Bear " + name + " started hibernating");
        hibernating=true;
    }

    public void stopHibernate(){
        System.out.println("Bear " + name + " stopped hibernating ");
        hibernating=false;
    }

    public boolean getHibernate(){
        return hibernating;

    }
    @Override
    public void drinkWater() {
        System.out.println("Bear " + name + " drinks water ");
    }

    @Override
    public String toString() {
        return "Bear{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
