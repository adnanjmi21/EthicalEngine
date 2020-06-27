/**
 * Concrete class Animal extends  abstract class Character
 */
package ethicalengine;


public class Animal extends Character {

    private String Species;
    private boolean pet;
    private boolean isPregnant;

    //default constructor
    public Animal() {
        super();
        this.Species = "";
        this.pet=false;
        this.isPregnant=false;
    }

    //copy constructor
    public Animal(Animal animal)
    {
        super();
        this.Species = animal.getSpecies();
        this.pet=animal.isPet();
        this.isPregnant=animal.isPregnant;
    }

    public Animal(String Species) {
        super();
        this.Species = Species;
        this.pet=true;
        this.isPregnant=false;
    }
    public Animal(int age, String species, Gender gender, BodyType bodyType, boolean isPregnant , boolean isPet) {
        super(age, gender, bodyType);
        this.Species = species;
        this.pet=isPet;
        this.isPregnant=isPregnant;
    }

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String Species) {
        this.Species = Species;
    }

    public boolean isPet() {
        return pet;
    }

    public void setPet(boolean pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        String s = "";
        s += Species + " ";
        if (pet) {
            s += "Pet ";
        }

        if (!"".equals(getBodyType())) {
            s += this.getBodyType().toString().toLowerCase() + " ";
        }


        if (!"".equals(getGender())) {
            s += " " + getGender().toString().toLowerCase() + " ";
        }
        if (this.isPregnant) {
            s += " pregnant ";
        }
        return s;
    }

    public boolean getPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean isPregnant) {
        this.isPregnant = isPregnant;
    }

}
