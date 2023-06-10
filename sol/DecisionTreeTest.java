package sol;

import static org.junit.Assert.assertEquals;


import org.junit.Assert;
import org.junit.Test;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class to test methods
 */
public class DecisionTreeTest {

    public DecisionTreeTest() {
    }

    /**
     * tests if getDecision works for a small dataset
     */
    @Test
    public void getDecisionTest1() {
        //make Dataset
        List<Row> dataObjects = DecisionTreeCSVParser.parse
                ("data/fruitsandveg/fruits-and-vegetables.csv");
        Set<String> data = dataObjects.get(0).getAttributes();
        List<String> attributeList = new ArrayList<>(data);
        Dataset training = new Dataset(attributeList, dataObjects);

        //build TreeGenerator object and generate tree
        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(training, "foodType");

        //makes new partial Row (tangerine, but can be changed)
        Row tangerine = new Row("tangerine");
        tangerine.setAttributeValue("color", "orange");
        tangerine.setAttributeValue("highProtein", "FALSE");
        tangerine.setAttributeValue("calories", "high");

        Row banana = new Row("banana");
        banana.setAttributeValue("color", "yellow");
        banana.setAttributeValue("highProtein", "TRUE");
        banana.setAttributeValue("calories", "high");

        //use decision tree to make decision
        String decision = generator.getDecision(tangerine);

        assertEquals(decision, "fruit");
    }
    /**
     * tests if getDecision works for a large dataset
     */
    @Test
    public void getDecisionTest2() {
        //make Dataset
        List<Row> dataObjects = DecisionTreeCSVParser.parse
                ("data/songs/training.csv");
        Set<String> data = dataObjects.get(0).getAttributes();
        List<String> attributeList = new ArrayList<>(data);
        Dataset training = new Dataset(attributeList, dataObjects);

        //build TreeGenerator object and generate tree
        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(training, "isLoud");

        //makes new partial Row
        Row mySong = new Row("mySong");
        mySong.setAttributeValue("topGenre", "afropop");
        mySong.setAttributeValue("year", "2000");
        mySong.setAttributeValue("isHighEnergy", "TRUE");
        mySong.setAttributeValue("isDanceable", "FALSE");
        mySong.setAttributeValue("isLively", "TRUE");
        mySong.setAttributeValue("isHighValence", "FALSE");
        mySong.setAttributeValue("isAcoustic", "FALSE");
        mySong.setAttributeValue("isSpeechy", "TRUE");
        mySong.setAttributeValue("isPopular", "FALSE");

        //use decision tree to make decision
        String decision = generator.getDecision(mySong);

        assertEquals(decision, "FALSE");
    }

    /**
     * tests if makeUniqueList works on list with repeats
     */
    @Test
    public void uniqueTest() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(
                "data/fruitsandveg/fruits-and-vegetables.csv");
        List<String> attributeList = new ArrayList<>(
                dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);

        ArrayList<String> solution = new ArrayList<>();
        solution.add("fruit");
        solution.add("vegetable");

        Assert.assertEquals(training.makeUniqueList("foodType"),
                solution);
    }
    /**
     * tests if makeUniqueList works on list with 3 distinct elements
     */
    @Test
    public void uniqueTest2() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(
                "data/fruitsandveg/fruits-and-vegetables.csv");
        List<String> attributeList = new ArrayList<>(
                dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);

        ArrayList<String> solution = new ArrayList<>();
        solution.add("orange");
        solution.add("green");
        solution.add("yellow");

        Assert.assertEquals(training.makeUniqueList("color"), solution);
    }
    /**
     * tests if makeUniqueList works on list with no repeats
     */
    @Test
    public void uniqueTest3() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(
                "data/fruitsandveg/same.csv");
        List<String> attributeList = new ArrayList<>(
                dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);

        ArrayList<String> solution = new ArrayList<>();
        solution.add("orange");
        solution.add("yellow");
        solution.add("green");
        solution.add("red");
        solution.add("purple");

        Assert.assertEquals(training.makeUniqueList("color"), solution);
    }

    /**
     * tests if size works on a dataset
     */
    @Test
    public void sizeTest() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(
                "data/fruitsandveg/fruits-and-vegetables.csv");
        List<String> attributeList = new ArrayList<>(
                dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);

        Assert.assertEquals(training.size(), 6);
    }

    /**
     * tests if defaultAtt works
     */
    @Test
    public void defaultTest() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse(
                "data/fruitsandveg/fruits-and-vegetables.csv");
        List<String> attributeList = new ArrayList<>(
                dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);

        Assert.assertEquals(training.defaultAtt("foodType"),
                "vegetable");
    }
}