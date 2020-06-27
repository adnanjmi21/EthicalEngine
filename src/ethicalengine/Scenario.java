package ethicalengine;

import java.util.ArrayList;

public class Scenario {
    
    // Scenario model
    
    private ArrayList<Person> passengers;
    private ArrayList<Person> pedestrians;
    private ArrayList<Animal> animalPedestrian;


    private ArrayList<Animal> animalPassengers;



    private boolean legalCrossing;
    private boolean pedsInLane;

    public Scenario() {
        this.passengers=new ArrayList<Person>();
        this.pedestrians=new ArrayList<Person>();
        animalPedestrian = new ArrayList<Animal>();
        animalPassengers = new  ArrayList<Animal>();
    }

    
    public Scenario(ArrayList<Person> passengersList, ArrayList<Person> pedestriansList, boolean legalCrossing, boolean pedsInLane) {
        this.passengers = passengersList;
        this.pedestrians = pedestriansList;
        this.legalCrossing = legalCrossing;
        this.pedsInLane = pedsInLane;
    }

    public ArrayList<Animal> getAnimals() {
        return animalPedestrian;
    }

    public void setAnimalPedestrian(ArrayList<Animal> animalPedestrian) {
        this.animalPedestrian = animalPedestrian;
    }
    public void setAnimalPassengers(ArrayList<Animal> animalPassengers) {
        this.animalPassengers = animalPassengers;
    }
    public void setAnimalPassenger(ArrayList<Animal> animalPassenger) {
        this.animalPassengers = animalPassenger;
    }
    public ArrayList<Animal> getAnimalPedestrian() {
        return animalPedestrian;
    }
    public ArrayList<Animal> getAnimalPassengers() {
        return animalPassengers;
    }

    public void addPassenger(Person p){
        passengers.add(p);
    }
    public void addPedestrian(Person p){
        pedestrians.add(p);
    }
    public Character getPassenger(int index){
        if(index<passengers.size()){
            return passengers.get(index);
        }else{
            return null;
        }
    }
    public Character getPedestrian(int index){
        if(index<pedestrians.size()){
            return pedestrians.get(index);
        }else{
            return null;
        }
    }
    public boolean hasYouInCar() {
       //downcast to arraylist of persons
        //ArrayList<Person> persons = (ArrayList<Person>)(ArrayList<?>) (passengers);

        //return persons.stream().anyMatch((person) -> (person.isYou()));
        return this.passengers.stream().anyMatch((person) -> (person.isYou()));
    }
    public void setPassengers(ArrayList<Person> p) {
        passengers=p;
    }
    public void setPedestrians(ArrayList<Person> p) {
        pedestrians=p;
    }





    public ArrayList<Person> getPassengers() {
        return this.passengers;
    }
    
    public ArrayList<Person> getPedestrians() {
        return this.pedestrians;
    }
    public void setLegalCrossing(boolean legal){
        this.legalCrossing=legal;
    }
    
    public boolean getLegalCrossing() {
        return this.legalCrossing;
    }
    
    public boolean hasPedestriansInLane() {
        return this.pedsInLane;
    }
    
    @Override
    public String toString() {

        int numAnimalPassenger=0;
        int numAnimalPedestrian=0;
        if(animalPassengers!=null && animalPassengers.size()>0)
            numAnimalPassenger=animalPassengers.size();
        if(animalPedestrian!=null && animalPedestrian.size()>0)
            numAnimalPedestrian=animalPedestrian.size();


        String readable="\n=======================";
        readable += "\nScenario Overview";
        readable += "\n=======================";

        readable += "\nLegal Crossing: " + (this.legalCrossing ? "Yes" : "No");
        readable += String.format("\nPassengers (%d)", this.passengers.size()+numAnimalPassenger);
        readable = this.passengers.stream().map((p) -> "\n" + p).reduce(readable, String::concat);

        if(numAnimalPassenger>0)
            readable = this.animalPassengers.stream().map((p) -> "\n" + p).reduce(readable, String::concat);
        readable += String.format("\nPedestrians (%d)", this.pedestrians.size()+numAnimalPedestrian);

        readable = this.pedestrians.stream().map((p) -> "\n" + p).reduce(readable, String::concat);
        if(numAnimalPedestrian>0)
            readable = this.animalPedestrian.stream().map((p) -> "\n" + p).reduce(readable, String::concat);

        return readable;
    }
    
}