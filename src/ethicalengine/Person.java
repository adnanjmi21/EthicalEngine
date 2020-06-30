/**
 * Concrete class Person.java
 * @author Adnan
 */


package ethicalengine;


public class Person extends Character {

    public enum Profession {
        DOCTOR, CEO, CRIMINAL, HOMELESS, UNEMPLOYED, UNKNOWN,NONE;
    }
    public enum AgeCategory {

        BABY,CHILD,ADULT,SENIOR,NONE;
    }
    private boolean isPregnant;
    private boolean isYou;
    private Profession profession;

    //default constructor
    public Person() {
        super();
        this.profession = Profession.NONE;
        this.isPregnant = false;
        this.isYou = false;
    }



    /**
     *  //copy constructor
     * @param person
     */
    public Person(Person person){
        super();
        this.profession = person.getProfession();
        this.isPregnant = person.isPregnant();
        this.isYou = person.isYou();

    }

    /**
     *  Parametrized Person Constructor
     * @param age
     * @param gender
     * @param bodyType
     */
    public Person(int age ,Gender gender,BodyType bodyType){
        super(age, gender, bodyType);
        this.profession = Profession.UNKNOWN;
    }

    /**
     * Parametrized Person Constructor
     * @param age
     * @param profession
     * @param gender
     * @param bodyType
     * @param isPregnant
     */
    public Person(int age, Profession profession, Gender gender, BodyType bodyType, boolean isPregnant) {
        super(age, gender, bodyType);
        this.profession = profession;
        this.isPregnant = isPregnant;
    }
    
    public Profession getProfession() {
        if(getAgeCategory().equals(AgeCategory.ADULT)){
            return profession;
        }
        return Profession.UNKNOWN;
    }

    /**
     * Get agecategaory for a person
     * @return
     */
    public AgeCategory getAgeCategory() {
        int a=getAge();
        if(a>0 && a<=4){ // if a is between 0 to 4 the person is baby
            return AgeCategory.BABY;
        }else if(a>=5 && a<=16){ // if a is between 5 to 16 the person is Child
            return AgeCategory.CHILD;
        }else if(a >= 17 && a<=68){ // if a is between 17 to 68 the person is ADULT
            return AgeCategory.ADULT;
        }else{ // if a is greater then 68 then the person is SENIOR
            return AgeCategory.SENIOR;
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

    /**
     * check if person is pregnant with contraints
     * @return
     */
    public boolean isPregnant() {
        if(getGender().equals(Gender.FEMALE)){
            if(getAgeCategory().equals(AgeCategory.ADULT)){
                return this.isPregnant;
            }            
        }
        return false;
    }
    
    public void setPregnant(boolean pregnant){

        if(getGender().equals(Gender.FEMALE)){
            if(getAgeCategory().equals(AgeCategory.ADULT)){
                 this.isPregnant= pregnant;
            }
        }else{
        // is pregnant if false for Gender types except Female and age category adult
        this.isPregnant=false;
        }
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
                if(!this.profession.equals(Profession.NONE))
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