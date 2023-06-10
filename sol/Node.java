package sol;

import src.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for a node
 * implements ITreeNode
 */
public class Node implements ITreeNode{
    private String category;
    private String def;
    private List<Edge> choices;

    /**
     * constructor for a node
     * @param cat the cateory
     * @param def the default value
     * @param choices the edges which come after the node
     */
    public Node(String cat, String def, ArrayList<Edge> choices) {
        this.category = cat;
        this.def = def;
        this.choices = choices;
    }

    /**
     * determines the end decision for a given node
     * @param forDatum the datum to lookup a decision for
     * @return a String which is the decision
     */
    @Override
    public String getDecision(Row forDatum) {
        for(Edge edges : choices) {
            if (forDatum.getAttributeValue(this.category).
                    equals(edges.getChoice())){
                return edges.getNext().getDecision(forDatum);
            }
        }
        return def;
    }
}