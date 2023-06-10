package sol;

import src.ITreeGenerator;
import src.Row;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class that implements the ITreeGenerator interface
 * used to generate a tree
 */

public class TreeGenerator implements ITreeGenerator<Dataset> {
    Dataset trainingData;
    ITreeNode finalTree;
    String targetAttribute;

    /**
     * generates a tree from a dataset and target attribute
     * @param trainingData    the dataset to train on
     * @param targetAttribute the attribute to predict
     */
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute) {
        Dataset copy = new Dataset(trainingData.getAttributeList(),
                trainingData.getDataObjects());
        copy.getAttributeList().remove(targetAttribute);
        this.trainingData = trainingData;
        this.finalTree = genTreeHelp(copy, targetAttribute);
        this.targetAttribute = targetAttribute;
    }

    /**
     * A helper for generateTree which produces a tree (this does the majority
     * of the tree making)
     * @param trainingData the dataset to train on
     * @param targetAttribute the attribute to predict
     * @return a tree based on the data
     */
    private ITreeNode genTreeHelp(Dataset trainingData, String targetAttribute){
        Random random = new Random();
        int upperBound = trainingData.getAttributeList().size();
        if (upperBound == 0) {
            return new Leaf(trainingData.defaultAtt(targetAttribute));
        } else {
            int randomNum = random.nextInt(upperBound);
            String currentAttribute =
                    trainingData.getAttributeList().get(randomNum);
            ArrayList<Edge> edges = new ArrayList<>();
            if (leafOrNot(currentAttribute, targetAttribute, trainingData)) {
                return new Leaf(trainingData.defaultAtt(targetAttribute));
            } else {
                for (Dataset set : trainingData.partition(currentAttribute)) {
                    //variable created to shorten lines
                    //creates edges
                    String lineVar =
                            set.getDataObjects().get(0).
                                    getAttributeValue(currentAttribute);
                    Edge edge1 = new Edge(lineVar,
                            genTreeHelp(set, targetAttribute));
                    edges.add(edge1);
                }
                //creates final node
                Node finalNode = new Node(currentAttribute,
                        trainingData.defaultAtt(targetAttribute), edges);
                return finalNode;
            }
        }
    }

    /**
     * determines if the next element should be a leaf or not
     * @param currAtt the attribute we are currently on
     * @param targ the attribute we are trying to predict
     * @param trainingData the dataset to train on
     * @return true if it should be a leaf, false otherwise
     */
    private boolean leafOrNot(String currAtt, String targ, Dataset trainingData)
    {
        String compare = trainingData.getDataObjects().get(0).
                getAttributeValue(targ);
        for (Row r : trainingData.getDataObjects()) {
            if (!(compare.equals(r.getAttributeValue(targ)))) {return false;}
        }//checks for equality between the attribute value of the target and the
        // first piece of training data
        return trainingData.makeUniqueList(currAtt).size() == 1 ||
                trainingData.size() == 0 || trainingData.
                getAttributeList().size() == 0;//catches exceptions/returns true
        // if any of these conditions are met
    }


    /**
     * produce the string which is predicted for a given row
     * @param datum the datum to look up a decision for
     * @return a String which is the decision
     */
    @Override
    public String getDecision(Row datum) {
        return this.finalTree.getDecision(datum);
    }
}
