
/**
 * Concrete class Scenario
 * @author Adnan
 */
package ethicalengine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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

    /**
     * parameterized constructor with Caharcter array as arguments
     * @param passengers
     * @param pedestrians
     * @param legalCrossing
     */
    public Scenario(Character[] passengers,Character[] pedestrians,boolean legalCrossing){
        this.passengers = Arrays.stream(passengers).map(p-> (Person)p).collect(Collectors.toCollection(ArrayList::new));
       this.pedestrians = Arrays.stream(pedestrians).map(p-> (Person)p).collect(Collectors.toCollection(ArrayList::new));;
        this.legalCrossing = legalCrossing;

    }
    public Scenario(ArrayList<Person> passengersList, ArrayList<Person> pedestriansList, boolean legalCrossing) {
        this.passengers = passengersList;
        this.pedestrians = pedestriansList;
        this.legalCrossing = legalCrossing;

    }

    public Scenario(ArrayList<Person> passengersList, ArrayList<Person> pedestriansList, boolean legalCrossing, boolean pedsInLane) {
        this.passengers = passengersList;
        this.pedestrians = pedestriansList;
        this.legalCrossing = legalCrossing;
        this.pedsInLane = pedsInLane;
    }

    /**Methods and getter setter   to get animal passengers and pedestrians
     *
     * @return
     */
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

    /**
     *  MEthod check whether You is amongs the passenger and in Car
     * @return
     */
    public boolean hasYouInCar() {

        return this.passengers.stream().anyMatch((person) -> (person.isYou()));
    }

    /**
     * set paassengers list to scenario
     * @param p
     */
    public void setPassengers(ArrayList<Person> p) {
        passengers=p;
    }

    /**
     * set pedestrians list to scenario
     * @param p
     */
    public void setPedestrians(ArrayList<Person> p) {
        pedestrians=p;
    }


    /**
     * Get paasengers list
     * @return
     */
    public ArrayList<Person> getPassengers() {
        return this.passengers;
    }

    /**
     * Get pedestrians list
     * @return
     */
    public ArrayList<Person> getPedestrians() {
        return this.pedestrians;
    }

    /**
     *
     * @param legal
     */
    public void setLegalCrossing(boolean legal){
        this.legalCrossing=legal;
    }
    
    public boolean getLegalCrossing() {
        return this.legalCrossing;
    }
    
    public boolean hasPedestriansInLane() {
        return this.pedsInLane;
    }

    /**
     *  method return no of passengers in car
     * @return
     */
    public int getPassengerCount(){
        return this.getPassengers().size();
    }

    /**
     *  method return no of pedestrians in lane
     * @return
     */
    public int getPedestrianCount(){
        return this.getPassengers().size();
    }
    /**
     *  MEthod check whether You is amonga the pedestrians an in Lane
     * @return
     */
    public boolean hasYouInLane(){
        return this.pedestrians.stream().anyMatch((person) -> (person.isYou()));

    }

    public boolean isLegalCrossing(){
        return this.getLegalCrossing();
    }
    @Override
    public String toString() {

        int numAnimalPassenger=0;
        int numAnimalPedestrian=0;
        if(animalPassengers!=null && animalPassengers.size()>0)
            numAnimalPassenger=animalPassengers.size();
        if(animalPedestrian!=null && animalPedestrian.size()>0)
            numAnimalPedestrian=animalPedestrian.size();


        String readable="======================================";
        readable += "\n# Scenario";
        readable += "\n======================================";

        readable += "\nLegal Crossing: " + (this.legalCrossing ? "Yes" : "No");
        readable += String.format("\nPassengers (%d)", this.passengers.size()+numAnimalPassenger);
        readable = this.passengers.stream().map((p) -> "\n- " + p).reduce(readable, String::concat);

        if(numAnimalPassenger>0)
            readable = this.animalPassengers.stream().map((p) -> "\n- " + p).reduce(readable, String::concat);
        readable += String.format("\nPedestrians (%d)", this.pedestrians.size()+numAnimalPedestrian);

        readable = this.pedestrians.stream().map((p) -> "\n- " + p).reduce(readable, String::concat);
        if(numAnimalPedestrian>0)
            readable = this.animalPedestrian.stream().map((p) -> "\n- " + p).reduce(readable, String::concat);

        return readable.toLowerCase().replace("scenario","Scenario").replace("legal","Legal")
                .replace("crossing","Crossing").replace("passengers","Passengers")
                .replace("pedestrians","Pedestrians");
    }
    
}