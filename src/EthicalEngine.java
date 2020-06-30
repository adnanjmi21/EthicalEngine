/**
 * Ethical  Engine
 *
 * @author:Adnan
 */

import ethicalengine.*;
import ethicalengine.Character;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
//import sun.tools.tree.ThisExpression;


public class EthicalEngine {

    /**
     * Decides whether to save the passengers or the pedestrians
     *
     * @param scenario
     * @param Scenario scenario: the ethical dilemma
     * @return Decision: which group to save
     */
    String[] header;
    String token;
    StringTokenizer st;
    int count = 0; // counter
    boolean scenarioStart = false;
    boolean legalCrossing = false;
    ArrayList<Person> carPersons;
    ArrayList<Person> pedestrianPersons;
    ArrayList<Scenario> scenarios;
    Scenario scenario;
    Scanner scanner;
    boolean push = false;
    Person p;
    Animal a;
    Map<Decision, ArrayList<Person>> scenarioResult;

    /**
     * Decision Enum
     */
    public enum Decision {

        PASSENGERS,
        PEDESTRIANS;

        @Override
        public String toString() {
            if (this == PASSENGERS) {
                return "passengers";
            } else {
                return "pedestrians";
            }
        }

    }

    /**
     * default constructor
     */
    public EthicalEngine() {

        this.pedestrianPersons = new ArrayList<>();
        this.carPersons = new ArrayList<>();
        this.scenarios = new ArrayList<>();
        scenarioResult = new HashMap<>();
    }

    /**
     * Method decides on basisi of bais whom to save passenger or pedestrian
     * @param scenario
     * @return
     */
    public static Decision decide(Scenario scenario) {

        int numPassengers = scenario.getPassengers().size();
        int numPedestrians = scenario.getPedestrians().size();
        int numAnimalPedestrians = scenario.getAnimalPassengers().size();
        int numAnimalPassengers = scenario.getAnimalPassengers().size();
        int pointForPassengers = 0, pointForPedestrians = 0;
        // passengers
        for (Person p : scenario.getPassengers()) {
            switch (p.getGender()) {
                case MALE:
                    if (!p.getProfession().equals(Person.Profession.CRIMINAL)) {
                        if (!p.getAgeCategory().equals(Person.AgeCategory.SENIOR)) {
                            pointForPassengers += 2; // if she/he is criminal and senior person
                            if (p.getBodyType().equals(Character.BodyType.ATHLETIC)) {
                                pointForPassengers += 1;
                                if (p.isYou()) {
                                    pointForPassengers += 1;
                                }
                            } else if (p.getBodyType().equals(Character.BodyType.AVERAGE)) {
                                if (p.isYou()) {
                                    pointForPassengers += 1;
                                }
                            } else if (p.getBodyType().equals(Character.BodyType.OVERWEIGHT)) {
                                if (p.isYou()) {
                                    pointForPassengers += 1;
                                }
                            }
                            if (p.getProfession().equals(Person.Profession.CEO)){
                                pointForPassengers+=4;

                            }
                            else if(p.getProfession().equals(Person.Profession.DOCTOR)){
                                pointForPassengers+=3;

                            }

                            if (p.isYou()) {
                                    pointForPassengers += 1;
                                }

                        } else {
                            pointForPassengers += 1;
                        }
                    }
                    break;
                case FEMALE:
                    if (!p.getProfession().equals(Person.Profession.CRIMINAL)) {
                        if (!p.getAgeCategory().equals(Person.AgeCategory.SENIOR)) {
                            pointForPassengers += 2; // if she/he is criminal and senior person
                            if (p.getBodyType().equals(Character.BodyType.ATHLETIC)) {
                                pointForPassengers += 1;
                                if (p.isYou()) {
                                    pointForPassengers += 1;
                                }
                            } else if (p.getBodyType().equals(Character.BodyType.AVERAGE)) {
                                if (p.isYou()) {
                                    pointForPassengers += 1;
                                }
                            } else if (p.getBodyType().equals(Character.BodyType.OVERWEIGHT)) {
                                if (p.isYou()) {
                                    pointForPassengers += 1;
                                }
                            }
                            if (p.getProfession().equals(Person.Profession.CEO)){
                                pointForPassengers+=4;

                            }
                            else if(p.getProfession().equals(Person.Profession.DOCTOR)){
                                pointForPassengers+=3;

                            }
                            // for unspecified
                                if (p.isYou()) {
                                    pointForPassengers += 1;

                            }
                        }
                    }
                    if (p.isPregnant()) {
                        pointForPassengers += 1;
                    }
                    break;
                default:
                    break;
            }
        }
        //Animal Passengers count points
        for(Animal a : scenario.getAnimalPassengers())
        {
            if(a.isPet())
                pointForPassengers++;
            if(a.getPregnant())
                pointForPassengers+=2;

        }
        // for pedestrians 
        for (Person p : scenario.getPedestrians()) {
            switch (p.getGender()) {
                case MALE:
                    if (!p.getProfession().equals(Person.Profession.CRIMINAL)) {
                        if (!p.getAgeCategory().equals(Person.AgeCategory.SENIOR)) {
                            pointForPedestrians += 2; // if she/he is not criminal and senior person
                            if (p.getBodyType().equals(Character.BodyType.ATHLETIC)) {
                                pointForPedestrians += 1;
                                if (p.isYou()) {
                                    pointForPedestrians += 1;
                                }
                            } else if (p.getBodyType().equals(Character.BodyType.AVERAGE)) {
                                if (p.isYou()) {
                                    pointForPedestrians += 1;
                                }
                            } else if (p.getBodyType().equals(Character.BodyType.OVERWEIGHT)) {
                                if (p.isYou()) {
                                    pointForPedestrians += 1;
                                }
                            } else { // for unspecified
                                if (p.isYou()) {
                                    pointForPedestrians += 1;
                                }
                            }
                        } else {
                            pointForPedestrians += 1;
                        }
                    }
                    break;
                case FEMALE:
                    if (!p.getProfession().equals(Person.Profession.CRIMINAL)) {
                        if (!p.getAgeCategory().equals(Person.AgeCategory.SENIOR)) {
                            pointForPedestrians += 2; // if she/he not  is criminal and senior person
                            if (p.getBodyType().equals(Character.BodyType.ATHLETIC)) {
                                pointForPedestrians += 1;
                                if (p.isYou()) {
                                    pointForPedestrians += 1;
                                }
                            } else if (p.getBodyType().equals(Character.BodyType.AVERAGE)) {
                                if (p.isYou()) {
                                    pointForPedestrians += 1;
                                }
                            } else if (p.getBodyType().equals(Character.BodyType.OVERWEIGHT)) {
                                if (p.isYou()) {
                                    pointForPedestrians += 1;
                                }
                            } else { // for unspecified
                                if (p.isYou()) {
                                    pointForPedestrians += 1;
                                }
                            }
                        }
                    }
                    if (p.isPregnant()) {
                        pointForPedestrians += 1;
                    }
                    break;
                default:
                    break;
            }
        }

        //Animal Pedestrians
        for(Animal a : scenario.getAnimalPedestrian())
        {
            if(a.isPet())
                pointForPedestrians++;
            if(a.getPregnant())
                pointForPedestrians+=2;

        }
        if (numPassengers > numPedestrians) {
            pointForPassengers += 1;
        } else {
            pointForPedestrians += 1;
        }
        if (scenario.getLegalCrossing()) {
            pointForPedestrians += 1;
        }
        if (pointForPassengers > pointForPedestrians) {
            return Decision.PASSENGERS;
        } else {
            return Decision.PEDESTRIANS;
        }
    }

    /**
     *Method to run interactive mode
     * @param configFilePath
     * @param outFilePath
     */
    public void interactiveMode(String configFilePath, String outFilePath) {
        Audit userAudit;
        Scanner scanner = new Scanner(System.in);
        String response ="";
        boolean writeConsent=false;
        boolean configFlag=false;
        printLogo();
        System.out.println("Do you consent to have your decisions saved to a file? (yes/no)");


        response = scanner.next();
        try {
            if(response.equalsIgnoreCase("yes")|| response.equalsIgnoreCase("y"))
            {
                writeConsent=true;
            }
            else if(!response.equalsIgnoreCase("no"))
            {
                throw new InvalidInputException("Invalid response. Do you consent to have your decisions saved to a file? (yes/no)");
            }
        } catch (InvalidInputException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
            response = scanner.next();
            if(response.equalsIgnoreCase("yes")|| response.equalsIgnoreCase("y"))
            {
                writeConsent=true;
            }

        }
        if(outFilePath==null ||outFilePath.equals("") || outFilePath.endsWith("results.log"))
            outFilePath="user.log"; // default file path

        if(!configFilePath.equals(""))
        {
            readCSV(configFilePath);
            userAudit = new Audit(scenarios);
            configFlag=true;
        }else
            userAudit = new Audit(scenarios);

        userAudit.setAuditType("User");


            if(configFlag)
            {configFlag=false;
                //System.out.println("Total "+scenarios.size()+" in config file ");
                int countcsv=0;
                for(Scenario scene : scenarios)
                {count++;

                        System.out.println(scene);
                        System.out.println("Who should be saved? (passenger(s) [1] or pedestrian(s) [2])");
                        response=scanner.next();
                    try {
                        if(!response.equals("2") && !response.equals("1") )
                            throw new InvalidInputException("Invalid response, Enter again");

                    } catch (InvalidInputException e) {
                        System.out.println(e.getMessage());
                        response=scanner.next();
                    }
                    if(response.equals("1"))
                    {
                        userAudit.run(scene,Decision.PASSENGERS);
                    }
                    if(response.equals("2"))
                    {
                    userAudit.run(scene,Decision.PEDESTRIANS);
                    }
                    if(count==scenarios.size())
                        break;
                    if(count%3==0){
                        userAudit.printStatistics();
                    System.out.println("Would you like to continue? (yes/no)");
                    response = scanner.next();
                    try {
                        if (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no"))
                            throw new InvalidInputException("Invalid response, Enter again");

                    } catch (InvalidInputException e) {
                        System.out.println(e.getMessage());
                        response = scanner.next();
                    }
                    if (response.equalsIgnoreCase("no")) {

                        break;
                    }
                    }
                }

            }else {
                while (true) {

                    Scenario scene = new ScenarioGenerator().generate();
                    System.out.println(scene);
                    System.out.println("Who should be saved? (passenger(s) [1] or pedestrian(s) [2])");
                    response = scanner.next();
                    try {
                        if (!response.equals("2") && !response.equals("1"))
                            throw new InvalidInputException("Invalid response, Enter again");

                    } catch (InvalidInputException e) {
                        e.printStackTrace();
                        response = scanner.next();
                    }
                    if (response.equals("1")) {
                        userAudit.run(scene, Decision.PASSENGERS);
                    }
                    if (response.equals("2")) {
                        userAudit.run(scene, Decision.PEDESTRIANS);
                    }
                    System.out.println("Would you like to continue? (yes/no)");
                    response = scanner.next();

                    if (response.equalsIgnoreCase("no")) {

                        break;
                    }
                }
            }
        if(writeConsent)
            userAudit.printToFile(outFilePath);
        else
            userAudit.printStatistics();
        System.out.println("That's all. Press Enter to quit.");
        response=scanner.nextLine();


            }


    /**
     *Method to audit csv config file
     *
     * @return
     */
    public Audit csvMode() {
        Audit a = new Audit(scenarios);
        a.setAuditType("CSV");
        a.run();
        return a;

    }




    /**
     *
     * @param path
     */
    public void readCSV(String path) {
        boolean green = false, red = false;
        boolean legalCrossing =false;
        try {
            File f = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f));
            //scanner = new Scanner(new File(path));
            header = new String[10];
            //header = scanner.nextLine().split(",");
            header = br.readLine().split(",");
            int lineNumber=1;
            String line;

                while ((line =br.readLine())!=null) {
                    lineNumber++;

                    //token = scanner.nextLine().toLowerCase();
                    token = line.toLowerCase();
                    if (token.toLowerCase().contains("scenario:green")) {
                        if (green) {
                            green = false;
                            scenarios.add(scenario);
                        } else if (red) {
                            red = false;
                            scenarios.add(scenario);
                        }
                        green = true;
                        scenario = new Scenario();
                        scenario.setLegalCrossing(true);
//                        legalCrossing=true;
//                        continue;

                    } else if (token.toLowerCase().contains("scenario:red")) {
                        if (green) {
                            green = false;
                            scenarios.add(scenario);
                        } else if (red) {
                            red = false;
                            scenarios.add(scenario);
                        }
                        red = true;
                        scenario = new Scenario();
                        scenario.setLegalCrossing(false);
//                        legalCrossing=true;
//                        continue;
                    } else {
                        String[] str = token.split(",");
                        if (str.length == 10) {
                            switch (str[0]) {
                                case "person": {
                                    p = new Person();
                                    for (int i = 0; i < header.length; i++) {

                                        switch (header[i]) {
                                            case "class": {
                                                break;
                                            }
                                            case "gender": {
                                                if(!str[i].equals(""))
                                                    //str[i]="UNKNOWN";
                                                try {

                                                    int count=0;
                                                    for(Character.Gender gen : Character.Gender.values())
                                                    {
                                                        if(gen.toString().equalsIgnoreCase(str[i]))
                                                        {
                                                            count++;
                                                            break;
                                                        }
                                                    }
                                                    if(count==0)
                                                        throw new InvalidCharacteristicException("WARNING: invalid characteristic in config file in line "+lineNumber);
                                                    p.setGender(Character.Gender.valueOf(str[i].toUpperCase()));

                                                }catch(InvalidCharacteristicException in)
                                                { System.out.println(in.getMessage());
                                                    str[i]="UNKNOWN";
                                                    p.setGender(Character.Gender.valueOf(str[i].toUpperCase()));
                                                    //in.printStackTrace();



                                                }
                                                break;
                                            }
                                            case "age": {
                                                try {
                                                    p.setAge(Integer.parseInt(str[i]));
                                                } catch (NumberFormatException e) {
                                                    System.err.println("WARNING: invalid number format in config file in line "+lineNumber);
                                                    //e.printStackTrace();
                                                }

                                                break;
                                            }
                                            case "bodyType": {
                                                if(!str[i].equals(""))
                                                    //str[i]="UNSPECIFIED";
                                                try {

                                                    int count = 0;
                                                    for (Character.BodyType gen : Character.BodyType.values()) {
                                                        if (gen.toString().equalsIgnoreCase(str[i])) {
                                                            count++;
                                                            break;
                                                        }
                                                    }
                                                    if (count == 0)
                                                        throw new InvalidCharacteristicException("WARNING: invalid characteristic in config file in line " + lineNumber);

                                                    p.setBodyType(Character.BodyType.valueOf(str[i].toUpperCase()));
                                                }catch(InvalidCharacteristicException in)
                                                { System.out.println(in.getMessage());
                                                    str[i]="UNSPECIFIED";
                                                    p.setBodyType(Character.BodyType.valueOf(str[i].toUpperCase()));
                                                    //in.printStackTrace();



                                                }
                                                break;
                                            }
                                            case "profession": {
                                                if(!str[i].equals(""))
                                                    //str[i]="UNKNOWN";
                                                try {

                                                    int count = 0;
                                                    for (Person.Profession gen : Person.Profession.values()) {
                                                        if (gen.toString().equalsIgnoreCase(str[i])) {
                                                            count++;
                                                            break;
                                                        }
                                                    }
                                                    if (count == 0)
                                                        throw new InvalidCharacteristicException("WARNING: invalid characteristic in config file in line " + lineNumber);

                                                    p.setProfession(Person.Profession.valueOf(str[i].toUpperCase()));
                                                }catch(InvalidCharacteristicException in)
                                                {   System.out.println(in.getMessage());
                                                    str[i]="UNKNOWN";
                                                    p.setProfession(Person.Profession.valueOf(str[i].toUpperCase()));
                                                    //in.printStackTrace();

                                                }

                                                break;
                                            }
                                            case "pregnant": {


                                                if (p.getGender().equals(Character.Gender.FEMALE)) {
                                                    if (str[i].toLowerCase().equals("true")) {

                                                        p.setPregnant(true);
                                                    } else {
                                                        p.setPregnant(false);
                                                    }
                                                }
                                                break;
                                            }
                                            case "isYou": {
                                                if (str[i].toLowerCase().equals("true")) {
                                                    p.setAsYou(true);
                                                } else {
                                                    p.setAsYou(false);
                                                }
                                                break;
                                            }
                                            case "role": {

                                                if (str[i].toLowerCase().equals("passenger")) {
                                                    scenario.addPassenger(p);
                                                } else if (str[i].toLowerCase().equals("pedestrian")) {
                                                    scenario.addPedestrian(p);
                                                }

                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                                case "animal":{

                                    a = new Animal();
                                    for (int i = 0; i < header.length; i++) {

                                        switch (header[i]) {
                                            case "class": {
                                                break;
                                            }
                                            case "gender": {
                                                if(!str[i].equals(""))
                                                    //str[i]="UNKNOWN";
                                                try {

                                                    int count=0;
                                                    for(Character.Gender gen : Character.Gender.values())
                                                    {
                                                        if(gen.toString().equalsIgnoreCase(str[i]))
                                                        {
                                                            count++;
                                                            break;
                                                        }
                                                    }
                                                    if(count==0)
                                                        throw new InvalidCharacteristicException("WARNING: invalid characteristic in config file in line "+lineNumber);
                                                    a.setGender(Character.Gender.valueOf(str[i].toUpperCase()));

                                                }catch(InvalidCharacteristicException in)
                                                {
                                                    System.out.println(in.getMessage());
                                                    str[i]="UNKNOWN";
                                                    a.setGender(Character.Gender.valueOf(str[i].toUpperCase()));
                                                    //in.printStackTrace();



                                                }
                                                break;
                                            }
                                            case "age": {
                                                try {
                                                    a.setAge(Integer.parseInt(str[i]));
                                                } catch (NumberFormatException e) {
                                                    System.err.println("WARNING: invalid number format in config file in line "+lineNumber);
                                                    //e.printStackTrace();
                                                }

                                                break;
                                            }
                                            case "bodyType": {
                                                if(!str[i].equals(""))
                                                    //str[i]="UNSPECIFIED";
                                                try {

                                                    int count = 0;
                                                    for (Character.BodyType gen : Character.BodyType.values()) {
                                                        if (gen.toString().equalsIgnoreCase(str[i])) {
                                                            count++;
                                                            break;
                                                        }
                                                    }
                                                    if (count == 0)
                                                        throw new InvalidCharacteristicException("WARNING: invalid characteristic in config file in line " + lineNumber);

                                                    a.setBodyType(Character.BodyType.valueOf(str[i].toUpperCase()));
                                                }catch(InvalidCharacteristicException in)
                                                { System.out.println(in.getMessage());
                                                    str[i]="UNSPECIFIED";
                                                    a.setBodyType(Character.BodyType.valueOf(str[i].toUpperCase()));
                                                    //in.printStackTrace();



                                                }
                                                break;
                                            }
                                            case "profession": {


                                                break;
                                            }
                                            case "pregnant": {
                                                if (a.getGender().equals(Character.Gender.FEMALE)) {
                                                    if (str[i].toLowerCase().equals("true")) {
                                                        a.setPregnant(true);
                                                    } else {
                                                        a.setPregnant(false);
                                                    }
                                                }
                                                break;
                                            }
                                            case "isPet": {
                                                if (str[i].toLowerCase().equals("true")) {
                                                    a.setPet(true);
                                                } else {
                                                    a.setPet(false);
                                                }

                                                break;
                                            }
                                            case "role": {

                                                if (str[i].toLowerCase().equals("passenger")) {
                                                    scenario.getAnimalPassengers().add(a);
                                                } else if (str[i].toLowerCase().equals("pedestrian")) {
                                                    scenario.getAnimalPedestrian().add(a);
                                                }
                                                break;
                                            }
                                            case "species":
                                            {
                                                a.setSpecies(str[i]);
                                                break;
                                            }
                                            default:
                                                break;
                                        }
                                    }
                                    break;
                                }
                                default: {
                                    break;
                                }
                            }
                        } else {
                            //throw InvalidDataFormatException as no. of values in CSV is not 10
                            try {
                                throw new InvalidDataFormatException("WARNING: invalid data format in config file in line "+lineNumber);
                            } catch (InvalidDataFormatException e) {
                                System.out.println(e.getMessage());
                            }

                        }
                    }

                }



            if (green) {
                scenarios.add(scenario);
            } else if (red) {
                scenarios.add(scenario);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            help();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Method print Moral machine logo to the terminal
     * @param
     */


    public void printLogo() {

        try {
            File f = new File(getClass().getResource("welcome.ascii").toURI());
            BufferedReader br = new BufferedReader(new FileReader(f));
            int iLine = 0;
            String line;
            while ((line = br.readLine()) != null) {
//                System.out.println("Line " + iLine + " has "
//                        + line.length() + " characters.");
//                iLine++;
                System.out.println(line);
            }

        } catch (IOException | URISyntaxException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Ethical Engine Main method .
     * this is program entry point
     * @param args
     */
    public static void main(String[] args) {

        EthicalEngine engine = new EthicalEngine();
        ScenarioGenerator scenarioGenerater = new ScenarioGenerator();
        //Scanner scanner = new Scanner(System.in);
        String response = "";
        //engine.printLogo();

        String configFilePath="";
        String outFilePath = System.getProperty("user.dir")+File.separator+"results.log";
        //System.out.println("out pah "+outFilePath);
        if (args.length >=1) { // checking if args lenght greater then 0 denoted that program has a arguments
            switch (args[0]) {
                case "-c": { // -c for configuration file path
                    if (args.length >= 2) {
                         configFilePath = args[1];
                        try {
                            if (!new File(args[1]).isFile()) {
                                throw new FileNotFoundException("ERROR: could not find config file.");

                            } else {


                                engine.readCSV(args[1]);
                                Audit csvAudit = engine.csvMode();
                                if (args.length >= 3) {
                                    if ("-r".equals(args[2])|| "--result".equals(args[2])) {
                                        if (args.length >= 4) {
                                            outFilePath = args[3];


                                        }
                                    }
                                    else {
                                        System.err.println("Warning! Invalid option");
                                        help();
                                    }
                                }
                                csvAudit.printToFile(outFilePath);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            help();
                        }

                    }else if(args.length==1)
                    {
                        System.err.println("Warning ! Please enter config file path");
                        help();
                    }
                    break;
                }
                case "-h": { // -h argument for showing the help
                    help();
                    break;
                }
                case "--help": {
                    help();
                    break;
                }
                case "-r": {
                    help();
                    break;
                }
                case "-i": {
                    try {
                        if (args.length >= 2) {

                            if(args[1].equals("-c")||args[1].equals("--config"))
                            {
                                if(args.length<3)
                                {
                                    System.err.println("Please specify file config file ");
                                    help();
                                }
                                if (args.length >= 4) {
                                    if ("-r".equals(args[3])|| "--result".equals(args[3])) {
                                        if (args.length >= 5) {
                                            outFilePath = args[4];

                                        }
                                    }
                                    else {
                                        System.err.println("Warning! Invalid option");
                                        help();
                                    }
                                }
                                configFilePath = args[2];
                                if (!new File(configFilePath).isFile()) {
                                    throw new FileNotFoundException("ERROR: could not find config file.");
                                }
                            }
                            else if ("-r".equals(args[1])|| "--result".equals(args[1])) {
                                if (args.length >= 3) {
                                    outFilePath = args[2];

                                }
                                else if(args.length<3)
                                {
                                    System.err.println("Please specify path for output file ");
                                    help();
                                }
                            }

                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        help();
                    }
                    engine.interactiveMode(configFilePath,outFilePath);
                    break;
                }
                case "--interactive":
                {
                    try {
                        if (args.length >= 2) {

                            if(args[1].equals("-c")||args[1].equals("--config"))
                            {
                                if(args.length<3)
                                {
                                    System.err.println("Please specify file config file ");
                                    help();
                                }
                                if (args.length >= 4) {
                                    if ("-r".equals(args[3])|| "--result".equals(args[3])) {
                                        if (args.length >= 5) {
                                            outFilePath = args[4];

                                        }
                                    }
                                    else {
                                        System.err.println("Warning! Invalid option");
                                        help();
                                    }
                                }
                                configFilePath = args[2];
                                if (!new File(configFilePath).isFile()) {
                                    throw new FileNotFoundException("ERROR: could not find config file.");
                                }
                            }
                            else if ("-r".equals(args[1])|| "--result".equals(args[1])) {
                                if (args.length >= 3) {
                                    outFilePath = args[2];

                                }
                                else if(args.length<3)
                                {
                                    System.err.println("Please specify path for output file ");
                                    help();
                                }
                            }

                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        help();
                    }
                    engine.interactiveMode(configFilePath,outFilePath);
                    break;}

                case "--config":
                    if (args.length >= 2) {
                         configFilePath = args[1];
                        try {
                            if (!new File(args[1]).isFile()) {
                                throw new FileNotFoundException("ERROR: could not find config file.");

                            } else {

                                System.out.println(" -c  default input  "+configFilePath);
                                engine.readCSV(args[1]);
                                Audit csvAudit = engine.csvMode();
                                if (args.length >= 3) {
                                    if ("-r".equals(args[2])|| "--result".equals(args[2])) {
                                        if (args.length >= 4) {
                                            outFilePath = args[3];


                                        }
                                    }
                                    else {
                                        System.err.println("Warning! Invalid option");
                                        help();
                                    }
                                }
                                csvAudit.printToFile(outFilePath);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            help();
                        }

                    }else if(args.length==1)
                    {
                        System.err.println("Warning ! Please enter config file path");
                        help();
                    }
                    break;
                default: {

                }
            }
        }else if(args.length==0)
        {/*
        Run thrre random scenarios when no arguments provided
        */
            System.out.println("Running EthicalEngine on random 3 scenarios");
            Audit defaultAudit = new Audit();
            defaultAudit.run(3);
            defaultAudit.printToFile(outFilePath);
            help();
        }

    }

    /**
     * method displays usage of Ethical Engine with command line parameters
     */
    private static void help() {
        System.out.println("EthicalEngine - COMP90041 - Final Project\n\nUsage: java EthicalEngine [arguments]\n");
        System.out.println("Arguments:");
        System.out.println("\t-c or --config\t\tOptional: path to configfile");
        System.out.println("\t-h or --help\t\tPrint Help and exit");
        System.out.println("\t-r or --results\t\tOptional path to results log file");
        System.out.println("\t-i or --interactive\t\tOptional: launches interactive mode");
        System.exit(0);
    }

}
class InvalidCharacteristicException  extends Exception {
    public InvalidCharacteristicException(String errorMessage) {
        super(errorMessage);
    }
}

class InvalidDataFormatException extends Exception {

    public InvalidDataFormatException(String errorMessage) {
        super(errorMessage);

    }
}

class InvalidInputException extends Exception{
    public InvalidInputException(String errorMessage) {
        super(errorMessage);

    }
}