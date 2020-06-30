/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ethicalengine;

/**
 * Scenario Generator
 *
 * @author:Adnan
 */
import java.util.ArrayList;
import java.util.Random;

public class ScenarioGenerator {


    private Random random;

    private long seeds;
    private int passengerCountMinimum = 3;




    private int passengerCountMaximum = 7;
    private int pedestrianCountMinimum = 3;
    private int pedestrianCountMaximum = 9;
    private int MIN_ANIMAL =1;
    private int MAX_ANIMAL =2;
    private boolean[] YOU_CHANCE = {true, false, true, false};
    private boolean[] LEGAL_CROSSING_CHANCE = {true, false};
    private boolean[] PEDS_IN_LANE_CHANCE = {true, false};
    private boolean[] SAME_NUM_CHANCE = {true, false};
    private String[] CHAR_TYPES = {"human", "human", "human", "animal", "human"};
    private String[] ANIMAL_TYPES = {"cat", "dog","bird"};
    private boolean[] PET_CHANCE = {true, false, true, false};
    private String[] AGE_TYPES = {"baby", "child", "adult", "senior", "senior"};
    private String[] PROF_TYPES = {"doctor", "CEO", "criminal", "homeless", "unemployed", "unknown"};
    private String[] GENDER_TYPES = {"male", "female","unknown"};
    private boolean[] PREGNANT_CHANCE = {true, false, true, false};
    private String[] BODYWEIGHT_CHANCE = {"overweight", "athletic", "average", "unspecified"};

    public ScenarioGenerator() {
         random = new Random();
    }

    public ScenarioGenerator(long seeds) {
        random = new Random();
        this.seeds = seeds;
        random.setSeed(seeds);
    }

    /**
     * Parameterized constructor
     * @param seeds
     * @param passengerCountMinimum
     * @param passengerCountMaximum
     * @param pedestrianCountMinimum
     * @param pedestrianCountMaximum
     * @param MIN_ANIMAL
     * @param MAX_ANIMAL
     */
    public ScenarioGenerator(long seeds, int passengerCountMinimum, int passengerCountMaximum, int pedestrianCountMinimum, int pedestrianCountMaximum, int MIN_ANIMAL, int MAX_ANIMAL) {
        random = new Random();
        this.seeds = seeds;
        random.setSeed(seeds);
        this.passengerCountMinimum = passengerCountMinimum;
        this.passengerCountMaximum = passengerCountMaximum;
        this.pedestrianCountMinimum = pedestrianCountMinimum;
        this.pedestrianCountMaximum = pedestrianCountMaximum;
        this.MIN_ANIMAL = MIN_ANIMAL;
        this.MAX_ANIMAL = MAX_ANIMAL;
    }

    /**
     *  Parameterized constructor
     * @param seeds
     * @param passengerCountMinimum
     * @param pedestrianCountMinimum
     * @param passengerCountMaximum
     */
    public ScenarioGenerator(long seeds, int passengerCountMinimum,int passengerCountMaximum ,int pedestrianCountMinimum, int pedestrianCountMaximum) {
        random = new Random();
        this.seeds = seeds;
        random.setSeed(seeds);
        if (passengerCountMinimum >= passengerCountMaximum) {
            System.out.println("Warning! minimum number of passenger should be less than maximum");
            passengerCountMinimum = passengerCountMaximum - 1;
        }
        if (pedestrianCountMinimum >= pedestrianCountMaximum) {
            System.out.println("Warning! minimum number of passenger should be less than maximum");
            pedestrianCountMinimum = pedestrianCountMaximum - 1;
        }
        this.passengerCountMinimum = passengerCountMinimum;
        this.passengerCountMaximum = passengerCountMaximum;
        this.pedestrianCountMinimum = pedestrianCountMinimum;
        this.pedestrianCountMaximum = pedestrianCountMaximum;
    }
    public int getPassengerCountMin() {
        return passengerCountMinimum;
    }

    public void setPassengerCountMin(int passengerCountMinimum) {
        this.passengerCountMinimum = passengerCountMinimum;
    }

    public int getPassengerCountMax() {
        return passengerCountMaximum;
    }

    public void setPassengerCountMax(int passengerCountMaximum) {
        this.passengerCountMaximum = passengerCountMaximum;
    }

    public int getPedestrianCountMin() {
        return pedestrianCountMinimum;
    }

    public void setPedestrianCountMin(int pedestrianCountMinimum) {
        this.pedestrianCountMinimum = pedestrianCountMinimum;
    }

    public int getPedestrianCountMax() {
        return pedestrianCountMaximum;
    }

    public void setPedestrianCountMax(int pedestrianCountMaximum) {
        this.pedestrianCountMaximum = pedestrianCountMaximum;
    }

    public int getMIN_ANIMAL() {
        return MIN_ANIMAL;
    }

    public void setMIN_ANIMAL(int MIN_ANIMAL) {
        this.MIN_ANIMAL = MIN_ANIMAL;
    }

    public int getMAX_ANIMAL() {
        return MAX_ANIMAL;
    }

    public void setMAX_ANIMAL(int MAX_ANIMAL) {
        this.MAX_ANIMAL = MAX_ANIMAL;
    }

    /**
     * method generate random scenarios
     * @return
     */
    public Scenario generate() {
        ArrayList<Person> passengers;
        ArrayList<Person> pedestrians;
        ArrayList<Animal> animalPassengers = new ArrayList<Animal>();
        ArrayList<Animal>  animalPedestrians = new ArrayList<Animal>();
        int numPedestrians = randomIntBetween(pedestrianCountMinimum, pedestrianCountMaximum);
        int numPassengers;
        int numAnimalPedestrians = randomIntBetween(MIN_ANIMAL,MAX_ANIMAL);
        //numPedestrians+= numAnimalPedestrians;
        // Check if scenario should have same number of passengers as pedestrians
        boolean sameNum = randomBoolean(SAME_NUM_CHANCE);
        if (!sameNum) {
            numPassengers = randomIntBetween(passengerCountMinimum, passengerCountMaximum);
        } else {
            numPassengers = numPedestrians;
        }
        //int animalpresent
        // Generate passengers
        int numAnimalPassengers = randomIntBetween(MIN_ANIMAL,MAX_ANIMAL);
        //numPassengers+=numAnimalPassengers;
        passengers = getRandomPersonArray(numPassengers);
        if(numAnimalPassengers!=0)
            animalPassengers = getRandomAnimalArray(numAnimalPassengers);


        // Determine if you are in the car
        boolean youInCar = randomBoolean(YOU_CHANCE);
        if (youInCar) {
            // If you are in the car, set a passenger to be you
            int randomIndex = 0 ;///random.nextInt(numPassengers);
            passengers.get(randomIndex).setAsYou(true);
        }
        // Generate pedestrians
        pedestrians = getRandomPersonArray(numPedestrians);
        //Generate animal pdestrians
        if(numAnimalPedestrians!=0)
            animalPedestrians= getRandomAnimalArray(numAnimalPedestrians);
        // Determine other scenario settings
        boolean legalCrossing = randomBoolean(LEGAL_CROSSING_CHANCE);
        boolean pedsInLane = randomBoolean(PEDS_IN_LANE_CHANCE);
        Scenario randomScenario = new Scenario(passengers, pedestrians, legalCrossing, pedsInLane);
        if(numAnimalPassengers>0)
            randomScenario.setAnimalPassenger(animalPassengers);
        if(numAnimalPedestrians>0)
            randomScenario.setAnimalPedestrian(animalPedestrians);

        return randomScenario;
        //return null;
    }

    public ArrayList<Animal> getRandomAnimalArray(int num) {
        ArrayList<Animal> randomeList=new ArrayList<Animal>();
        for (int i = 0; i < num; i++) {
            randomeList.add(getRandomAnimal());
        }
        return randomeList;
    }

    public Animal getRandomAnimal() {
        //Animal anm = new Animal();

        int Age = 0;
        Character.Gender g = null;
        Character.BodyType bt = null;
        boolean pregnant = false;

        Age = randomIntBetween(0, 10);
        g = Character.Gender.valueOf(GENDER_TYPES[randomString(GENDER_TYPES)].toUpperCase());
        bt = Character.BodyType.valueOf(BODYWEIGHT_CHANCE[randomString(BODYWEIGHT_CHANCE)].toUpperCase());
        String species = ANIMAL_TYPES[randomString(ANIMAL_TYPES)].toUpperCase();
        if (checkAgeCategoryAnimal(Age).equals("adult")) {
            if (g.equals(Character.Gender.FEMALE)) {
                pregnant = randomBoolean(PREGNANT_CHANCE);
            }
        }

        Animal anm= new  Animal(Age, species, g, bt,  pregnant ,randomBoolean(PET_CHANCE));
        return anm;

    }

    public String checkAgeCategory(int a) {
        if (a >= 0 && a <= 4) { // if a is between 0 to 4 the person is baby
            return "baby";
        } else if (a >= 5 && a <= 16) { // if a is between 5 to 16 the person is Child
            return "child";
        } else if (a >= 17 && a <= 68) { // if a is between 17 to 68 the person is ADULT
            return "adult";
        } else { // if a is greater then 68 then the person is SENIOR
            return "senior";
        } 
    }

    public String checkAgeCategoryAnimal(int a) {
        if (a >= 0 && a <= 1) { // if a is between 0 to 4 the person is baby
            return "baby";
        } else if (a >= 1 && a <= 2) { // if a is between 5 to 16 the person is Child
            return "child";
        } else if (a >= 2 && a <= 6) { // if a is between 17 to 68 the person is ADULT
            return "adult";
        } else { // if a is greater then 68 then the person is SENIOR
            return "senior";
        }
    }

    /**
     * get random array of persons
     * @param num
     * @return
     */
    public ArrayList<Person> getRandomPersonArray(int num) {
        ArrayList<Person> randomeList=new ArrayList<>();
        for (int i = 0; i < num; i++) {
            randomeList.add(getRandomPerson());
        }
        return randomeList;
    }

    public long getSeeds() {
        return seeds;
    }
    public void setSeed(long seed) {
        random.setSeed(seed);
    }

    public boolean randomBoolean(boolean[] array) {
        int index = random.nextInt(array.length);
        return array[index];
    }

    public int randomString(String[] array) {
        int index = random.nextInt(array.length);
        return index;
    }

    public int randomIntBetween(int min, int max) {
        int diff = random.nextInt((max - min) + 1);
        return min + diff;
    }

    /**
     * Generate random person object
     * @return
     */
    public Person getRandomPerson() {
        //Random r=new Random(42);
        Person.Profession p = null;
        int Age = 0;
        Character.Gender g = null;
        Character.BodyType bt = null;
        boolean pregnant = false;
        Age = randomIntBetween(0, 70);
        g = Character.Gender.valueOf(GENDER_TYPES[randomString(GENDER_TYPES)].toUpperCase());
        bt = Character.BodyType.valueOf(BODYWEIGHT_CHANCE[randomString(BODYWEIGHT_CHANCE)].toUpperCase());
        p = Person.Profession.valueOf(PROF_TYPES[randomString(PROF_TYPES)].toUpperCase());
        if (checkAgeCategory(Age).equals("adult")) {
            if (g.equals(Character.Gender.FEMALE)) {
                pregnant = randomBoolean(PREGNANT_CHANCE);
            }
        }
        return new Person(Age, p, g, bt, pregnant);
    }

}
