package sol;

import src.Row;

/**
 * A class for leaf
 * implements ITreeNode
 */
public class Leaf implements ITreeNode {
    private String result;

    /**
     * a constructor for a leaf
     * @param res - result
     */
    public Leaf(String res) {
        this.result = res;
    }

    /**
     * produces a decision for a given leaf
     * @param forDatum the datum to lookup a decision for
     * @return the value at the leaf
     */
    @Override
    public String getDecision(Row forDatum) {
        return this.result;
    }
}
