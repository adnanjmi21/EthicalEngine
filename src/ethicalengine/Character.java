/**
 *Abstract Class Character.java
 *
 * Enums Gender,BodyType
 *
 * @author Adnan
 */
package ethicalengine;


public abstract class Character {
    public enum BodyType {
        AVERAGE, ATHLETIC, OVERWEIGHT, UNSPECIFIED;
    }
    public enum Gender {
        MALE, FEMALE, UNKNOWN;
    }
    private int age;
    private Gender gender;
    private BodyType bodyType;

    /**
     * parameterized constructor
     * @param age
     * @param gender
     * @param bodyType
     */
    public Character(int age,Gender gender,BodyType bodyType){
        this.age=age;
        this.gender=gender;
        this.bodyType=bodyType;
    }

    /**
     * copy constructor
     * @param c
     */
    public Character(Character c){
        this.age=c.age;
        this.gender=c.gender;
        this.bodyType=c.bodyType;
    }

    /**
     * default constructor
     */
    public Character(){
        this.age=0;
        this.gender=Gender.UNKNOWN;
        this.bodyType=BodyType.UNSPECIFIED;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }
    
    
}
