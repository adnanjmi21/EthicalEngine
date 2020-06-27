/**
 * Concrete class Person.java
 *
 */


package ethicalengine;


public class Person extends Character {

    public enum Profession {
        DOCTOR, CEO, CRIMINAL, HOMELESS, UNEMPLOYED, UNKNOWN,NONE;
    }
    public enum AgeCatagory {
        BABY,CHILD,ADULT,SENIOR,NONE;
    }
    private boolean isPregnant;
    private boolean isYou;
    private Profession profession;

    //default constructor
    public Person() {
        super();
        this.profession = Profession.UNKNOWN;
        this.isPregnant = false;
        this.isYou = false;
    }

    //copy constructor
    public Person(Person person){
        super();
        this.profession = person.getProfession();
        this.isPregnant = person.isPregnant();
        this.isYou = person.isYou();

    }
    
    public Person(int age, Profession profession, Gender gender, BodyType bodyType, boolean isPregnant) {
        super(age, gender, bodyType);
        this.profession = profession;
        this.isPregnant = isPregnant;
    }
    
    public Profession getProfession() {
        if(getAgeCategory().equals(AgeCatagory.ADULT)){
            return profession;
        }
        return Profession.UNKNOWN;
    }
    
    public AgeCatagory getAgeCategory() {
        int a=getAge();
        if(a>0 && a<=4){ // if a is between 0 to 4 the person is baby
            return AgeCatagory.BABY;
        }else if(a>=5 && a<=16){ // if a is between 5 to 16 the person is Child
            return AgeCatagory.CHILD;
        }else if(a >= 17 && a<=68){ // if a is between 17 to 68 the person is ADULT
            return AgeCatagory.ADULT;
        }else{ // if a is greater then 68 then the person is SENIOR
            return AgeCatagory.SENIOR;
        }
    }
    public String checkAgeCategory(int a){
        if(a>=0 && a<=4){ // if a is between 0 to 4 the person is baby
            return "BABY";
        }else if(a>=5 && a<=16){ // if a is between 5 to 16 the person is Child
            return "Child";
        }else if(a >= 17 && a<=68){ // if a is between 17 to 68 the person is ADULT
            return "ADULT";
        }else if(a>68){ // if a is greater then 68 then the person is SENIOR
            return "SENIOR";
        }else{
            return "UNKNOWN";
        }
    }
    
    public boolean isPregnant() {
        if(getGender().equals(Gender.FEMALE)){
            if(getAgeCategory().equals(AgeCatagory.ADULT)){
                return this.isPregnant;
            }            
        }
        return false;
    }
    
    public void setPregnant(boolean pregnant){

        if(getGender().equals(Gender.FEMALE)){
            if(getAgeCategory().equals(AgeCatagory.ADULT)){
                 this.isPregnant= pregnant;
            }
        }
        // is pregnant if false for Gender types except Female and age category adult
        this.isPregnant=false;
    }
    
    public boolean isYou() {
        return this.isYou;
    }
    
    public void setAsYou(boolean isYou) {
        this.isYou = isYou;
    }
    
    @Override
    public String toString() {
        String readable="";
            if(isYou){
                readable +=" You ";
            }
            if (!"".equals(getBodyType())) {
                readable += this.getBodyType().toString().toLowerCase() + " ";
            }
                readable += getAgeCategory()+" ";
                readable += " " + getProfession().toString().toLowerCase()+" ";
            if (!"".equals(getGender())) {
                readable += " " + getGender().toString().toLowerCase() + " ";
            }
            if (this.isPregnant) {
                readable += " pregnant ";
            }
            //remove extra spaces and trim
        return readable.replaceAll("\\s+"," ").trim();
    }
    
    public void setProfession(Profession profession) {
        this.profession = profession;
    }
    
}