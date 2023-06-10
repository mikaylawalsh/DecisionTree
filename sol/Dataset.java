package sol;

import src.IDataset;
import src.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the IDataset interface,
 * representing a training data set.
 */
public class Dataset implements IDataset {
    private List<String> attributeNames;
    private List<Row> rowList;

    /**
     * Constructor for Dataest
     * @param attributes the list of attributes
     * @param rList the list of rows
     */
    public Dataset(List<String> attributes, List<Row> rList) {
        this.rowList = rList;
        this.attributeNames = attributes;
    }

    /**
     * produces the list of attribute names
     * @return the list of attribute names
     */
    @Override
    public List<String> getAttributeList() {
        return this.attributeNames;
    }

    /**
     * produces the list of rows
     * @return the list of row objects
     */
    @Override
    public List<Row> getDataObjects() {
        return this.rowList;
    }

    /**
     * produces the size of the row list
     * @return an int which is the size of the list
     */
    @Override
    public int size() {
        return this.rowList.size();
    }

    /**
     * Produces a unique list from a list that may contain repeats
     * @param attribute the attribute we want a distinct list for
     * @return a list of strings which is columnLst with no repeats
     */
    public List<String> makeUniqueList(String attribute) {
        ArrayList<String> uniqueList = new ArrayList<>();
        for (Row r : this.rowList) {
            String val = r.getAttributeValue(attribute);
            if(!uniqueList.contains(val)){
                uniqueList.add(val);
            }
        }
        return uniqueList;
    }
    /**
     * splits the dataset up into a list of datasets
     * @return a list of datasets
     */
    public ArrayList<Dataset> partition(String att) {
        ArrayList<Dataset> datasets = new ArrayList<>();
        ArrayList<String> newAtts = new ArrayList(this.attributeNames);
        List<String> distinctVals = this.makeUniqueList(att);
        newAtts.remove(att);
        List<Row> rows = this.rowList;
        this.getAttributeList().size();
            for (String currentVal : distinctVals) {
                ArrayList<Row> rList = new ArrayList<>();
                for (Row currentRow : rows) {
                    if (currentRow.getAttributeValue(att).equals(currentVal)) {
                        rList.add(currentRow);
                    }
                } //if the attribute value of the current row is equal to
                 // the value, we add that element to our list of rows
                datasets.add(new Dataset(newAtts, rList)); //adds all the newly
                // created lists to the dataset--> this makes the tree
        }
        return datasets;
    }
    /**
     * produces a string which is the most common value for a given attribute
     * @param targ the attribute
     * @return a string which is the most common
     */
    public String defaultAtt(String targ) {
        ArrayList<Dataset> dList = this.partition(targ);
        String finData = null;
        int count = 0;
        for(Dataset finDat : dList) {
            if (finDat.size() > count) {
                finData = finDat.rowList.get(0).getAttributeValue(targ);
                count = finDat.size();
            }
        } //finds the most common attribute and sets that to our default node
        return finData;
    }
}