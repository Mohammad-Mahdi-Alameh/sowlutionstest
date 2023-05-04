import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class game {
    static List<String> cards = new ArrayList<>();
    static List<String> animals = new ArrayList<>();
    static List<String> fruits = new ArrayList<>();
    public static void main(String... args) {
        cards.addAll(Arrays.asList("Diamonds","Hearts","Spades","Clubs","Joker"));
        animals.addAll(Arrays.asList("Lion", "Fox", "Parrot", "Seal","Snake"));
        fruits.addAll(Arrays.asList("Apple", "Bananas", "Mango", "Watermelon","Papaya"));

        List<Prediction> predictions = readPredictionFromCSV("C:\\Users\\mmwal\\IdeaProjects\\sowlutions\\src\\prediction.csv");
        for (Prediction b : predictions) {
            System.out.println(b);
        }
    }
    private static List<Prediction> readPredictionFromCSV(String fileName) {
        int counter = 0;
        List<String> cards = new ArrayList<>();
        List<String> animals = new ArrayList<>();
        List<String> fruits = new ArrayList<>();
        List<Prediction> predictions = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                if (counter > 0) {
                    String[] attributes = line.split(",");
                    Prediction prediction = createPrediction(attributes);
                    predictions.add(prediction);
                    line = br.readLine();
                }
                counter++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } return predictions;
    }
    private static Prediction createPrediction(String[] metadata) {
        String card = metadata[0];
        String animal = metadata[1];
        String fruit = metadata[2];
        Boolean result = Boolean.valueOf(metadata[3]);
        return new Prediction(card, animal, fruit,result);
    }
}
class Prediction {
    private String card;
    private String animal;
    private String fruit;
    private Boolean result;
    public Prediction(String card, String animal, String fruit, Boolean result) {
        this.card = card;
        this.animal = animal;
        this.fruit = fruit;
        this.result = result;
    }
    public String getcard() {
        return card;
    }
    public void setCard(String card) {
        this.card = card;
    }
    public String getAnimal() {
        return animal;
    }
    public void setAnimal(int price) {
        this.animal = animal;
    }
    public String getFruit() {
        return fruit;
    }
    public void setFruit(String fruit) {
        this.fruit = fruit;
    }
    public Boolean getResult() {
        return result;
    }
    public void setResult(Boolean Result) {
        this.result = result;
    }
}
