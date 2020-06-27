
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
    private static int totalRuns=0;
    private static int totalCharacters=0;
    private static int totalAge=0;


    public Audit() {
        scenarios = new ArrayList<>();
        auditType = "Algorithm";

        // initialize each characteristics to 0
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
        scoreCharacteristics.put("pregnant",0.0);
        scoreCharacteristics.put("cat",0.0);
        scoreCharacteristics.put("dog",0.0);
        scoreCharacteristics.put("person",0.0);
        scoreCharacteristics.put("animal",0.0);
        scoreCharacteristics.put("bird",0.0);
        scoreCharacteristics.put("red",0.0);
        scoreCharacteristics.put("green",0.0);
        scoreCharacteristics.put("pet",0.0);
        savedCharacteristicsCount= new HashMap<String,Double>();
        totalCharacteristicsCount=new HashMap<String,Double>();
        savedCharacteristicsCount.putAll(scoreCharacteristics);
        totalCharacteristicsCount.putAll(scoreCharacteristics);


    }

    public Audit(ArrayList<Scenario> scenarios)
    {
        this.scenarios = scenarios;
        auditType = "Algorithm";

        // initialize each characteristics to 0
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
        scoreCharacteristics.put("pregnant",0.0);
        scoreCharacteristics.put("cat",0.0);
        scoreCharacteristics.put("dog",0.0);
        scoreCharacteristics.put("person",0.0);
        scoreCharacteristics.put("animal",0.0);
        scoreCharacteristics.put("bird",0.0);
        scoreCharacteristics.put("red",0.0);
        scoreCharacteristics.put("green",0.0);
        scoreCharacteristics.put("pet",0.0);
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

         for(Scenario scenario : scenarios) {
             //scenarios.add(scenario);
             //System.out.println(scenario);
             Decision decide = EthicalEngine.decide(scenario);
             if (decide.equals(Decision.PASSENGERS)) {
                 countPassengers++;
                 countPassengerCharacteristics(scenario, scoreCharacteristics);

             } else if (decide.equals(Decision.PEDESTRIANS)) {
                 countPedestrians++;
                 countPedestrianCharacteristics(scenario, scoreCharacteristics);

             }
             totalPersons += scenario.getPassengers().size() + scenario.getPedestrians().size();
             if (scenario.getAnimalPassengers()!=null && scenario.getAnimalPassengers().size()>0)
                 totalPersons+=scenario.getAnimalPassengers().size();
             if (scenario.getAnimalPedestrian()!=null && scenario.getAnimalPedestrian().size()>0)
                 totalPersons+=scenario.getAnimalPedestrian().size();
             countPassengerCharacteristics(scenario, totalCharacteristicsCount);
             countPedestrianCharacteristics(scenario, totalCharacteristicsCount);


         }

        final int total = totalPersons;

        scoreCharacteristics.replaceAll((k,v)->(v/totalCharacteristicsCount.get(k)));


        //store each audit run result to a static map
        if(allScores.isEmpty())
            allScores.putAll(scoreCharacteristics);
        else
        {
            for (Map.Entry<String,Double> value : scoreCharacteristics.entrySet())
            {
                //update survival count for each characteristics
                allScores.computeIfPresent(value.getKey(),(k,v)->(v+value.getValue())/2);
            }
        }

        totalRuns+=scenarios.size();
        totalCharacters+=totalPersons;
        printStatistics();
    }

    public void run(Scenario scenario ,Decision decide)
    {
        int totalPersons=0;
        int countPassengers = 0, countPedestrians = 0;

            //scenarios.add(scenario);
            //System.out.println(scenario);
            //Decision decide = EthicalEngine.decide(scenario);
            if (decide.equals(Decision.PASSENGERS)) {
                countPassengers++;
                countPassengerCharacteristics(scenario, scoreCharacteristics);

            } else if (decide.equals(Decision.PEDESTRIANS)) {
                countPedestrians++;
                countPedestrianCharacteristics(scenario, scoreCharacteristics);

            }
            totalPersons += scenario.getPassengers().size() + scenario.getPedestrians().size();
            if (scenario.getAnimalPassengers()!=null && scenario.getAnimalPassengers().size()>0)
                totalPersons+=scenario.getAnimalPassengers().size();
            if (scenario.getAnimalPedestrian()!=null && scenario.getAnimalPedestrian().size()>0)
                totalPersons+=scenario.getAnimalPedestrian().size();
            countPassengerCharacteristics(scenario, totalCharacteristicsCount);
            countPedestrianCharacteristics(scenario, totalCharacteristicsCount);




        final int total = totalPersons;

        scoreCharacteristics.replaceAll((k,v)->(v/totalCharacteristicsCount.get(k)));


        //store each audit run result to a static map
        if(allScores.isEmpty())
            allScores.putAll(scoreCharacteristics);
        else
        {
            for (Map.Entry<String,Double> value : scoreCharacteristics.entrySet())
            {
                //update survival count for each characteristics
                allScores.computeIfPresent(value.getKey(),(k,v)->(v+value.getValue())/2);
            }
        }

        totalRuns+=1;
        totalCharacters+=totalPersons;
    }
    public void run(int runs){

        // generate random scenarios for each run
        for (int i = 0; i < runs; i++) {
            scenario = new ScenarioGenerator().generate();
            scenarios.add(scenario);
        }
        run();
        //setAuditType("Algorithm");
        /*int countPassengers = 0, countPedestrians = 0;
        int totalPersons=0;
        for (int i = 0; i < runs; i++) {
            scenario = new ScenarioGenerator().generate();
            scenarios.add(scenario);
            //System.out.println(scenario);
            Decision decide = EthicalEngine.decide(scenario);
            if (decide.equals(Decision.PASSENGERS)) {
                countPassengers++;
                countPassengerCharacteristics(scenario,scoreCharacteristics);

            } else if (decide.equals(Decision.PEDESTRIANS)) {
                countPedestrians++;
                countPedestrianCharacteristics(scenario,scoreCharacteristics);

            }
            totalPersons+=scenario.getPassengers().size()+scenario.getPedestrians().size();
            countPassengerCharacteristics(scenario,totalCharacteristicsCount);
            countPedestrianCharacteristics(scenario,totalCharacteristicsCount);

        }


        final int total = totalPersons;

        scoreCharacteristics.replaceAll((k,v)->(v/totalCharacteristicsCount.get(k)));


        //store each audit run result to a static map
        if(allScores.isEmpty())
            allScores.putAll(scoreCharacteristics);
        else
        {
        for (Map.Entry<String,Double> value : scoreCharacteristics.entrySet())
            {
            //update survival count for each characteristics
            allScores.computeIfPresent(value.getKey(),(k,v)->(v+value.getValue())/2);
            }
        }
        totalRuns+=runs;
        totalCharacters+=totalPersons;
        printStatistics();*/

    }

    public void countPassengerCharacteristics(Scenario scenario,Map<String,Double> countCharachteristicsMap )
    {
        for(Person p :scenario.getPassengers())
        {
            totalAge+=p.getAge();
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
                //add unknown
                countCharachteristicsMap.putIfAbsent(a.getSpecies().toLowerCase(),0.0);
                countCharachteristicsMap.put(a.getSpecies().toLowerCase(),countCharachteristicsMap.get(a.getSpecies().toLowerCase())+1);
                if(a.getPregnant())
                    countCharachteristicsMap.put("pregnant",countCharachteristicsMap.get("pregnant")+1);

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
            totalAge+=p.getAge();
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
                //add unknown species
                countCharachteristicsMap.putIfAbsent(a.getSpecies().toLowerCase(),0.0);
                countCharachteristicsMap.put(a.getSpecies().toLowerCase(),countCharachteristicsMap.get(a.getSpecies().toLowerCase())+1);
                if(a.getPregnant())
                    countCharachteristicsMap.put("pregnant",countCharachteristicsMap.get("pregnant")+1);

                if(a.isPet())
                    countCharachteristicsMap.put("pet",countCharachteristicsMap.get("pet")+1);
                countCharachteristicsMap.put("animal",countCharachteristicsMap.get("animal")+1);

            }
        }
    }
    public void printStatistics(){
        System.out.println("======================================");
        System.out.println("# "+auditType+" Audit");
        System.out.println("======================================");
        System.out.println("- % SAVED AFTER "+totalRuns+" RUNS");

        //filter NaN
        Map<String, Double> collect = allScores.entrySet().stream()
                .filter(x -> !Double.isNaN(x.getValue()))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        //sort values in descending order
        LinkedHashMap<String, Double> sorted = new LinkedHashMap<>();

        //Use Comparator.reverseOrder() for reverse ordering
        collect.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
        sorted.forEach((k,v)-> System.out.println(k.toLowerCase()+" : "+String.format("%.1f", v)));
        System.out.println("--");
        System.out.println("average age : "+String.format("%.1f",(double)totalAge/totalCharacters));

    }

    public void printToFile(String path) {

        File f;
        FileOutputStream fos;
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
            System.out.println("Results saved at location : "+path);
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
            Decision decide = EthicalEngine.decide(scenario);
            if (decide.equals(Decision.PASSENGERS)) {
                countPassengers++;
                //System.out.println("Save Passengers");
            } else if (decide.equals(Decision.PEDESTRIANS)) {
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

        //filter NaN
        Map<String, Double> collect = allScores.entrySet().stream()
                .filter(x -> !Double.isNaN(x.getValue()))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        //sort values in descending order
        LinkedHashMap<String, Double> sorted = new LinkedHashMap<>();

        //Use Comparator.reverseOrder() for reverse ordering
        collect.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
        //sorted.forEach((k,v)-> System.out.println(k+" : "+String.format("%.2f", v)));
        for (Map.Entry<String, Double> value : sorted.entrySet()) {
            out += value.getKey().toLowerCase() + " : " + String.format("%.1f", value.getValue()) + "\n";
        }
        out += "--\n";
        out += "average age : " + String.format("%.2f", (double) totalAge / totalCharacters);
        return out;

    }




}
