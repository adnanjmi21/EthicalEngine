
import ethicalengine.*;
import ethicalengine.Character;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Audit {

    private String auditType;
    private ArrayList<Scenario> scenarios;
    private Scenario scenario;
    private Map<String,Double> scoreCharacteristics ;
    private Map<String,Double> savedCharacteristicsCount ;
    private Map<String,Double> totalCharacteristicsCount ;
    private static Map<String,Double> allScores = new HashMap<String,Double>();
    private static Map<String,Double> allScoresNew = new HashMap<String,Double>();
    private static Map<String,Double> allSurvivorCharcateristics = new HashMap<String,Double>();
    private static Map<String,Double> totalOccuredChars = new HashMap<String,Double>();
    private static int totalRuns=0;
    private static int totalCharacters=0;
    private static int totalAge=0;
    private static HashSet<String> printCharacteristics = new HashSet<>();

    /**
     * default constructor
     */
    public Audit() {
        scenarios = new ArrayList<>();
        auditType = "Algorithm";
        initScores();
        // initialize each characteristics to 0
        /*scoreCharacteristics = new HashMap<String,Double>();
        for(Person.Profession value : Person.Profession.values()) {
            scoreCharacteristics.put(value.toString(),0.0);
        }

        for(Character.Gender value : Character.Gender.values()) {
            scoreCharacteristics.put(value.toString(),0.0);
        }
        for(Character.BodyType value : Character.BodyType.values()) {
            scoreCharacteristics.put(value.toString(),0.0);
        }
        for(Person.AgeCatagory value : Person.AgeCatagory.values()) {
            scoreCharacteristics.put(value.toString(),0.0);
        }
        scoreCharacteristics.put("pregnant",0.0);
        scoreCharacteristics.put("cat",0.0);
        scoreCharacteristics.put("dog",0.0);
        scoreCharacteristics.put("person",0.0);
        scoreCharacteristics.put("animal",0.0);
        scoreCharacteristics.put("bird",0.0);
        scoreCharacteristics.put("red",0.0);
        scoreCharacteristics.put("green",0.0);
        scoreCharacteristics.put("pet",0.0);
        scoreCharacteristics.put("ferret",0.0);
        scoreCharacteristics.put("you",0.0);
        savedCharacteristicsCount= new HashMap<String,Double>();
        totalCharacteristicsCount=new HashMap<String,Double>();
        savedCharacteristicsCount.putAll(scoreCharacteristics);
        totalCharacteristicsCount.putAll(scoreCharacteristics);
*/

    }

    public void initScores(){
        for(Person.Profession value : Person.Profession.values()) {
            allSurvivorCharcateristics.put(value.toString(),0.0);
        }

        for(Character.Gender value : Character.Gender.values()) {
            allSurvivorCharcateristics.put(value.toString(),0.0);
        }
        for(Character.BodyType value : Character.BodyType.values()) {
            allSurvivorCharcateristics.put(value.toString(),0.0);
        }
        for(Person.AgeCategory value : Person.AgeCategory.values()) {
            allSurvivorCharcateristics.put(value.toString(),0.0);
        }
        allSurvivorCharcateristics.put("pregnant",0.0);
        allSurvivorCharcateristics.put("cat",0.0);
        allSurvivorCharcateristics.put("dog",0.0);
        allSurvivorCharcateristics.put("person",0.0);
        allSurvivorCharcateristics.put("animal",0.0);
        allSurvivorCharcateristics.put("bird",0.0);
        allSurvivorCharcateristics.put("red",0.0);
        allSurvivorCharcateristics.put("green",0.0);
        allSurvivorCharcateristics.put("pet",0.0);
        allSurvivorCharcateristics.put("ferret",0.0);
        allSurvivorCharcateristics.put("you",0.0);
        totalOccuredChars.putAll(allSurvivorCharcateristics);
    }
    /**
     * parameterized constructor
     * @param scenarios
     */
    public Audit(ArrayList<Scenario> scenarios)
    {
        this.scenarios = scenarios;
        auditType = "Algorithm";
        initScores();
        // initialize each characteristics to 0
        /*scoreCharacteristics = new HashMap<String,Double>();
        for(Person.Profession value : Person.Profession.values()) {
            scoreCharacteristics.put(value.toString(),0.0);
        }

        for(Character.Gender value : Character.Gender.values()) {
            scoreCharacteristics.put(value.toString(),0.0);
        }
        for(Character.BodyType value : Character.BodyType.values()) {
            scoreCharacteristics.put(value.toString(),0.0);
        }
        for(Person.AgeCatagory value : Person.AgeCatagory.values()) {
            scoreCharacteristics.put(value.toString(),0.0);
        }
        scoreCharacteristics.put("pregnant",0.0);
        scoreCharacteristics.put("cat",0.0);
        scoreCharacteristics.put("dog",0.0);
        scoreCharacteristics.put("person",0.0);
        scoreCharacteristics.put("animal",0.0);
        scoreCharacteristics.put("bird",0.0);
        scoreCharacteristics.put("red",0.0);
        scoreCharacteristics.put("green",0.0);
        scoreCharacteristics.put("pet",0.0);
        scoreCharacteristics.put("ferret",0.0);
        scoreCharacteristics.put("you",0.0);
        savedCharacteristicsCount= new HashMap<String,Double>();
        totalCharacteristicsCount=new HashMap<String,Double>();
        savedCharacteristicsCount.putAll(scoreCharacteristics);
        totalCharacteristicsCount.putAll(scoreCharacteristics);*/

    }

    public void initCharcteristicsMap()
    {
        scoreCharacteristics = new HashMap<String,Double>();
        for(Person.Profession value : Person.Profession.values()) {
            scoreCharacteristics.put(value.toString(),0.0);
        }

        for(Character.Gender value : Character.Gender.values()) {
            scoreCharacteristics.put(value.toString(),0.0);
        }
        for(Character.BodyType value : Character.BodyType.values()) {
            scoreCharacteristics.put(value.toString(),0.0);
        }
        for(Person.AgeCategory value : Person.AgeCategory.values()) {
            scoreCharacteristics.put(value.toString(),0.0);
        }
        scoreCharacteristics.put("pregnant",0.0);
        scoreCharacteristics.put("cat",0.0);
        scoreCharacteristics.put("dog",0.0);
        scoreCharacteristics.put("person",0.0);
        scoreCharacteristics.put("animal",0.0);
        scoreCharacteristics.put("bird",0.0);
        scoreCharacteristics.put("red",0.0);
        scoreCharacteristics.put("green",0.0);
        scoreCharacteristics.put("pet",0.0);
        scoreCharacteristics.put("ferret",0.0);
        scoreCharacteristics.put("you",0.0);
        savedCharacteristicsCount= new HashMap<String,Double>();
        totalCharacteristicsCount=new HashMap<String,Double>();
        savedCharacteristicsCount.putAll(scoreCharacteristics);
        totalCharacteristicsCount.putAll(scoreCharacteristics);
    }
    public Map<String, Double> getScoreCharacteristics() {
        return scoreCharacteristics;
    }

    public void setScoreCharacteristics(Map<String, Double> scoreCharacteristics) {
        this.scoreCharacteristics = scoreCharacteristics;
    }
    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String AdutitType) {
        this.auditType = AdutitType;
    }
    public void run()
    {
        int countPassengers = 0, countPedestrians = 0;
        int totalPersons=0;
        HashSet<String> keysPerRun = new HashSet<>();

         for(Scenario scenario : scenarios) {
             initCharcteristicsMap();
             //scenarios.add(scenario);
             //System.out.println(scenario);
             EthicalEngine.Decision decide = EthicalEngine.decide(scenario);
             if (decide.equals(EthicalEngine.Decision.PASSENGERS)) {
                 countPassengers++;
                 countPassengerCharacteristics(scenario, scoreCharacteristics);
                 countPassengerCharacteristics(scenario, allSurvivorCharcateristics);

             } else if (decide.equals(EthicalEngine.Decision.PEDESTRIANS)) {
                 countPedestrians++;
                 countPedestrianCharacteristics(scenario, scoreCharacteristics);
                 countPedestrianCharacteristics(scenario, allSurvivorCharcateristics);

             }
             //totalPersons += scenario.getPassengers().size() + scenario.getPedestrians().size();
             /*if (scenario.getAnimalPassengers()!=null && scenario.getAnimalPassengers().size()>0)
                 totalPersons+=scenario.getAnimalPassengers().size();
             if (scenario.getAnimalPedestrian()!=null && scenario.getAnimalPedestrian().size()>0)
                 totalPersons+=scenario.getAnimalPedestrian().size();*/
             countPassengerCharacteristics(scenario, totalCharacteristicsCount);
             countPedestrianCharacteristics(scenario, totalCharacteristicsCount);
             countPassengerCharacteristics(scenario, totalOccuredChars);
             countPedestrianCharacteristics(scenario, totalOccuredChars);
             //put occured characteristics in a set
             for (Map.Entry<String,Double> count: totalCharacteristicsCount.entrySet()) {
                 if(count.getValue()>0.0)
                     //printCharacteristics.add(count.getKey());
                     keysPerRun.add(count.getKey());
             }
            //count total age fro given scenario
             countTotalAge(scenario,decide);
             printCharacteristics.addAll(keysPerRun);

             for (Map.Entry<String,Double> value : scoreCharacteristics.entrySet())
             {
                 //update survival count for each characteristics
                 if(totalCharacteristicsCount.containsKey(value.getKey()))
                     if(value.getValue()!=0.0 || totalCharacteristicsCount.get(value.getKey())!=0.0)
                         scoreCharacteristics.replace(value.getKey(),value.getValue(),value.getValue()/totalCharacteristicsCount.get(value.getKey()));
                 // scoreCharacteristics.computeIfPresent(value.getKey(),(k,v)->(v+value.getValue())/2);
             }

             //store each audit run result to a static map
             if(allScores.isEmpty())
                 allScores.putAll(scoreCharacteristics);
             else
             {
                 for (Map.Entry<String,Double> value : scoreCharacteristics.entrySet())
                 {
                     //update survival count for each characteristics
                     if(printCharacteristics.contains(value.getKey())){
                         if(allScores.get(value.getKey())!=0.0)
                             allScores.computeIfPresent(value.getKey(),(k,v)->(v+value.getValue())/2);
                         else if (totalCharacteristicsCount.get(value.getKey())>0.0 && value.getValue()==0.0 ){
                             allScores.computeIfPresent(value.getKey(), (k, v) -> (v+value.getValue())/2);
                         }
                         else
                             allScores.computeIfPresent(value.getKey(), (k, v) -> v+value.getValue());

                     }
                 }
             }


         }

        //final int total = totalPersons;

        //scoreCharacteristics.replaceAll((k,v)->(v/totalCharacteristicsCount.get(k)));


        totalRuns+=scenarios.size();
        //totalCharacters+=totalPersons;
        printStatistics();
    }

    /**
     * Method count total age for eahc character and stores in a static member
     * @param scenario
     */
    public void countTotalAge(Scenario scenario, EthicalEngine.Decision decide)
    {
       if (decide.equals(EthicalEngine.Decision.PASSENGERS)) {
           for (Person p : scenario.getPassengers()) {
               totalAge+=p.getAge();
               totalCharacters++;
           }
       }else {
           for (Person p : scenario.getPedestrians()) {
               totalAge+=p.getAge();
               totalCharacters++;
           }
       }
        // only for persons
       /* for(Animal a : scenario.getAnimalPedestrian()){
            totalAge+=a.getAge();
        }
        for (Animal a : scenario.getAnimalPassengers()){
            totalAge+=a.getAge();
        }*/


    }
    public void run(Scenario scenario ,EthicalEngine.Decision decide)
    {
        int totalPersons=0;
        int countPassengers = 0, countPedestrians = 0;
        HashSet<String> keysPerRun = new HashSet<>();

        initCharcteristicsMap();
            //scenarios.add(scenario);
            //System.out.println(scenario);
            //Decision decide = EthicalEngine.decide(scenario);
            if (decide.equals(EthicalEngine.Decision.PASSENGERS)) {
                countPassengers++;
                countPassengerCharacteristics(scenario, scoreCharacteristics);
                countPassengerCharacteristics(scenario, allSurvivorCharcateristics);

            } else if (decide.equals(EthicalEngine.Decision.PEDESTRIANS)) {
                countPedestrians++;
                countPedestrianCharacteristics(scenario, scoreCharacteristics);
                countPedestrianCharacteristics(scenario, allSurvivorCharcateristics);

            }
            //totalPersons += scenario.getPassengers().size() + scenario.getPedestrians().size();
            /*if (scenario.getAnimalPassengers()!=null && scenario.getAnimalPassengers().size()>0)
                totalPersons+=scenario.getAnimalPassengers().size();
            if (scenario.getAnimalPedestrian()!=null && scenario.getAnimalPedestrian().size()>0)
                totalPersons+=scenario.getAnimalPedestrian().size();*/
            countPassengerCharacteristics(scenario, totalCharacteristicsCount);
            countPedestrianCharacteristics(scenario, totalCharacteristicsCount);
        countPassengerCharacteristics(scenario, totalOccuredChars);
        countPedestrianCharacteristics(scenario, totalOccuredChars);

        //put occured characteristics in a set
        for (Map.Entry<String,Double> count: totalCharacteristicsCount.entrySet()) {
            if(count.getValue()>0.0)
            //printCharacteristics.add(count.getKey());
                keysPerRun.add(count.getKey());
        }

        //count total age fro given scenario
        countTotalAge(scenario,decide);

        printCharacteristics.addAll(keysPerRun);
        //final int total = totalPersons;

        //scoreCharacteristics.replaceAll((k,v)->(v/totalCharacteristicsCount.get(k)));
        for (Map.Entry<String,Double> value : scoreCharacteristics.entrySet())
        {
            //update survival count for each characteristics
            if(totalCharacteristicsCount.containsKey(value.getKey()))
                if(value.getValue()!=0.0 || totalCharacteristicsCount.get(value.getKey())!=0.0)
                    scoreCharacteristics.replace(value.getKey(),value.getValue(),value.getValue()/totalCharacteristicsCount.get(value.getKey()));
           // scoreCharacteristics.computeIfPresent(value.getKey(),(k,v)->(v+value.getValue())/2);
        }


        //store each audit run result to a static map
        if(allScores.isEmpty())
            allScores.putAll(scoreCharacteristics);
        else
        {
            for (Map.Entry<String,Double> value : scoreCharacteristics.entrySet())
            {
                //update survival count for each characteristics
                if(keysPerRun.contains(value.getKey())){
                    if(allScores.get(value.getKey())!=0.0)
                        allScores.computeIfPresent(value.getKey(),(k,v)->(v+value.getValue())/2);
                    else if (totalCharacteristicsCount.get(value.getKey())>0.0  && value.getValue()==0.0){
                        allScores.computeIfPresent(value.getKey(), (k, v) -> (v+value.getValue())/2);
                    }
                    else
                        allScores.computeIfPresent(value.getKey(), (k, v) -> v+value.getValue());
                }
                //allScores.computeIfPresent(value.getKey(),(k,v)->(v+value.getValue())/2);
                    //allScores.put(value.getKey(),(allScores.get(value.getKey())+value.getValue())/2);
            }
        }


        totalRuns+=1;
        //totalCharacters+=totalPersons;
    }

    /**
     * generate random scenarioa and run audit for default running Engine with no arguments
     * @param runs
     */

    public void run(int runs){

        // generate random scenarios for each run
        for (int i = 0; i < runs; i++) {
            scenario = new ScenarioGenerator().generate();
            scenarios.add(scenario);
            System.out.println(scenario);
        }
        run();


    }

    /**
     *
     * @param scenario
     * @param countCharachteristicsMap
     */
    public void countPassengerCharacteristics(Scenario scenario,Map<String,Double> countCharachteristicsMap )
    {
        for(Person p :scenario.getPassengers())
        {


            if(p.isYou())
                countCharachteristicsMap.put("you",countCharachteristicsMap.get("you")+1);
            if(scenario.getLegalCrossing())
                countCharachteristicsMap.put("green",countCharachteristicsMap.get("green")+1);
            else
                countCharachteristicsMap.put("red",countCharachteristicsMap.get("red")+1);

            if(countCharachteristicsMap.containsKey(p.getAgeCategory().toString()))

                countCharachteristicsMap.put(p.getAgeCategory().toString(),countCharachteristicsMap.get(p.getAgeCategory().toString())+1);
            countCharachteristicsMap.put(p.getProfession().toString(),countCharachteristicsMap.get(p.getProfession().toString())+1);
            countCharachteristicsMap.put(p.getBodyType().toString(),countCharachteristicsMap.get(p.getBodyType().toString())+1);
            countCharachteristicsMap.put(p.getGender().toString(),countCharachteristicsMap.get(p.getGender().toString())+1);
            if(p.isPregnant())
                countCharachteristicsMap.put("pregnant",countCharachteristicsMap.get("pregnant")+1);
            countCharachteristicsMap.put("person",countCharachteristicsMap.get("person")+1);

        }
        //count animal characteristics
        if (scenario.getAnimalPassengers()!=null && scenario.getAnimalPassengers().size()>0) {
            for(Animal a : scenario.getAnimalPassengers())
            {
                if(scenario.getLegalCrossing())
                    countCharachteristicsMap.put("green",countCharachteristicsMap.get("green")+1);
                else
                    countCharachteristicsMap.put("red",countCharachteristicsMap.get("red")+1);
                //add unknown
                countCharachteristicsMap.putIfAbsent(a.getSpecies().toLowerCase(),0.0);
                countCharachteristicsMap.put(a.getSpecies().toLowerCase(),countCharachteristicsMap.get(a.getSpecies().toLowerCase())+1);
//                if(a.getPregnant())
//                    countCharachteristicsMap.put("pregnant",countCharachteristicsMap.get("pregnant")+1);

                if(a.isPet())
                    countCharachteristicsMap.put("pet",countCharachteristicsMap.get("pet")+1);
                countCharachteristicsMap.put("animal",countCharachteristicsMap.get("animal")+1);

            }
        }
    }

    public void countPedestrianCharacteristics(Scenario scenario, Map<String,Double> countCharachteristicsMap )
    {
        for(Person p :scenario.getPedestrians())
        {

            if(p.isYou())
                countCharachteristicsMap.put("you",countCharachteristicsMap.get("you")+1);
            if(scenario.getLegalCrossing())
                countCharachteristicsMap.put("green",countCharachteristicsMap.get("green")+1);
            else
                countCharachteristicsMap.put("red",countCharachteristicsMap.get("red")+1);
            if(countCharachteristicsMap.containsKey(p.getAgeCategory().toString()))

                countCharachteristicsMap.put(p.getAgeCategory().toString(),countCharachteristicsMap.get(p.getAgeCategory().toString())+1);
            countCharachteristicsMap.put(p.getProfession().toString(),countCharachteristicsMap.get(p.getProfession().toString())+1);
            countCharachteristicsMap.put(p.getBodyType().toString(),countCharachteristicsMap.get(p.getBodyType().toString())+1);
            countCharachteristicsMap.put(p.getGender().toString(),countCharachteristicsMap.get(p.getGender().toString())+1);
            if(p.isPregnant())
                countCharachteristicsMap.put("pregnant",countCharachteristicsMap.get("pregnant")+1);
            countCharachteristicsMap.put("person",countCharachteristicsMap.get("person")+1);
        }

        //count animal characteristics
        if (scenario.getAnimalPedestrian()!=null && scenario.getAnimalPedestrian().size()>0) {
            for(Animal a : scenario.getAnimalPedestrian())
            {
                if(scenario.getLegalCrossing())
                    countCharachteristicsMap.put("green",countCharachteristicsMap.get("green")+1);
                else
                    countCharachteristicsMap.put("red",countCharachteristicsMap.get("red")+1);
                //add unknown species
                countCharachteristicsMap.putIfAbsent(a.getSpecies().toLowerCase(),0.0);
                countCharachteristicsMap.put(a.getSpecies().toLowerCase(),countCharachteristicsMap.get(a.getSpecies().toLowerCase())+1);
//                if(a.getPregnant())
//                    countCharachteristicsMap.put("pregnant",countCharachteristicsMap.get("pregnant")+1);

                if(a.isPet())
                    countCharachteristicsMap.put("pet",countCharachteristicsMap.get("pet")+1);
                countCharachteristicsMap.put("animal",countCharachteristicsMap.get("animal")+1);

            }
        }
    }

    /**
     *Method print audit staistics for accumlated no of runs in a session
     */
    public void printStatistics(){

        //allScores.forEach((k,v)-> System.out.println(k.toLowerCase()+": "+String.format("%.1f", v)));
        System.out.println("======================================");
        System.out.println("# "+auditType+" Audit");
        System.out.println("======================================");
        System.out.println("- % SAVED AFTER "+totalRuns+" RUNS");

        //filter NaN

        for(Map.Entry<String,Double> entry :allSurvivorCharcateristics.entrySet())
        {
            if(entry.getValue()>0)
            {
                allSurvivorCharcateristics.put(entry.getKey(), entry.getValue()/totalOccuredChars.get(entry.getKey()));
            }
            //out +=entry.getKey().toLowerCase()+": "+String.format("%.1f", entry.getValue())+"\n";
        }
        if(allScoresNew.isEmpty())
        allScoresNew.putAll(allSurvivorCharcateristics);
        else{
            for(Map.Entry<String,Double> entry :allSurvivorCharcateristics.entrySet())
            {
                if(entry.getValue()>0)
                {
                    allScoresNew.put(entry.getKey(),0.5*(allScoresNew.get(entry.getKey())+ entry.getValue()/totalOccuredChars.get(entry.getKey())));
                }
                //out +=entry.getKey().toLowerCase()+": "+String.format("%.1f", entry.getValue())+"\n";
            }
        }

        Map<String, Double> collect = allScoresNew.entrySet().stream()
                .filter(x -> !Double.isNaN(x.getValue()))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        //sort values in descending order
        LinkedHashMap<String, Double> sorted = new LinkedHashMap<>();

        //Use Comparator.reverseOrder() for reverse ordering reversed()
        /*collect.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));*/
        collect.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder())

                        .thenComparing(Map.Entry.comparingByKey()))
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));

        //sorted.forEach((k,v)-> System.out.println(k.toLowerCase()+": "+String.format("%.1f", v)));

        for(Map.Entry<String,Double> entry :sorted.entrySet())
        {
            if(printCharacteristics.toString().contains(entry.getKey()) && !entry.getKey().equalsIgnoreCase("unknown"))
                //String.valueOf( entry.getValue()).substring(0,2)
                System.out.println(entry.getKey().toLowerCase()+": "+String.valueOf( entry.getValue()).substring(0,3));
                //System.out.println(entry.getKey().toLowerCase()+": "+String.format("%.1f",(double) entry.getValue()));
        }
        double Avg=1.0* (double)totalAge/totalCharacters;
        Avg=Avg*10;
        Avg=Math.round(Avg);
        Avg=Avg/10;
        System.out.println("--");
        System.out.println("average age: "+String.format("%.1f",(double)Avg));

    }

    public void printToFile(String path) {

        File f;
        FileOutputStream fos;

       /* for(Map.Entry<String,Double> entry :allSurvivorCharcateristics.entrySet())
        {
            if(entry.getValue()>0)
            {
                System.out.println(entry.getKey().toLowerCase()+": "+String.format("%.1f", entry.getValue()/totalOccuredChars.get(entry.getKey())));
            }
                //out +=entry.getKey().toLowerCase()+": "+String.format("%.1f", entry.getValue())+"\n";
        }*/
        System.out.println(toString());
        try {
            f = new File(path);
            if (!f.exists()) {
                //create new file and write contents
                if (f.createNewFile()) {
                    fos = new FileOutputStream(f);
                    fos.write(toString().getBytes());
                    fos.close();
                }
            }
            else{
                //append if file already exists
                fos = new FileOutputStream(f,true);
                fos.write(toString().getBytes());
                fos.close();

            }
            //System.out.println("Results saved at location : "+path);
        } catch (IOException e) {
            System.out.println("ERROR: could not print results. Target directory does not exist.");
            System.exit(1);
        }
    }

    public void auditCSV(ArrayList<Scenario> scenarios){
        setAuditType("CSV");
        int countPassengers = 0, countPedestrians = 0;
        for (Scenario s : scenarios) {
            scenario = new ScenarioGenerator().generate();
            EthicalEngine.Decision decide = EthicalEngine.decide(scenario);
            if (decide.equals(EthicalEngine.Decision.PASSENGERS)) {
                countPassengers++;
                //System.out.println("Save Passengers");
            } else if (decide.equals(EthicalEngine.Decision.PEDESTRIANS)) {
                countPedestrians++;
                //System.out.println("Save Pedestrians");
            }
        }
        System.out.println("Save Passengers  " + countPassengers);
        System.out.println("Save Pedestrians  " + countPedestrians);
    }

    @Override
    public String toString() {

        if (scoreCharacteristics.isEmpty())
            return "no audit available";
        String out;

        out = "\n======================================\n" + "# " + auditType + " Audit\n" + "======================================\n";
        out += "- % SAVED AFTER " + totalRuns + " RUNS\n";
        for(Map.Entry<String,Double> entry :allSurvivorCharcateristics.entrySet())
        {
            if(entry.getValue()>0)
            {
                allSurvivorCharcateristics.put(entry.getKey(), entry.getValue()/totalOccuredChars.get(entry.getKey()));
            }
            //out +=entry.getKey().toLowerCase()+": "+String.format("%.1f", entry.getValue())+"\n";
        }
        if(allScoresNew.isEmpty())
            allScoresNew.putAll(allSurvivorCharcateristics);
        else{
            for(Map.Entry<String,Double> entry :allSurvivorCharcateristics.entrySet())
            {
                if(entry.getValue()>0)
                {
                    allScoresNew.put(entry.getKey(),0.5*(allScoresNew.get(entry.getKey())+ entry.getValue()/totalOccuredChars.get(entry.getKey())));
                }
                //out +=entry.getKey().toLowerCase()+": "+String.format("%.1f", entry.getValue())+"\n";
            }
        }

        //filter NaN
        Map<String, Double> collect = allScoresNew.entrySet().stream()
                .filter(x -> !Double.isNaN(x.getValue()))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        //sort values in descending order
        LinkedHashMap<String, Double> sorted = new LinkedHashMap<>();

        //Use Comparator.reverseOrder() for reverse ordering
        collect.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder())

                        .thenComparing(Map.Entry.comparingByKey()))
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
        //sorted.forEach((k,v)-> System.out.println(k+" : "+String.format("%.2f", v)));

        for(Map.Entry<String,Double> entry :sorted.entrySet())
        {
            if(printCharacteristics.toString().contains(entry.getKey())&& !entry.getKey().equalsIgnoreCase("unknown"))
               // out +=entry.getKey().toLowerCase()+": "+String.format("%.1f", entry.getValue())+"\n";
                out +=entry.getKey().toLowerCase()+": "+String.valueOf( entry.getValue()).substring(0,3)+"\n";
        }
//        for (Map.Entry<String, Double> value : sorted.entrySet()) {
//            out += value.getKey().toLowerCase() + ": " + String.format("%.1f", value.getValue()) + "\n";
//        }
        double Avg=1.0* (double)totalAge/totalCharacters;
        Avg=Avg*10;
        Avg=Math.round(Avg);
        Avg=Avg/10;
        out += "--\n";
        out += "average age: " + String.format("%.1f",Avg);
        return out;

    }




}
