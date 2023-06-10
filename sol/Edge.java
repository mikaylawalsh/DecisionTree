package sol;

import src.ITreeGenerator;
/**
 * The class for an edge
 */
public class Edge {
    private String choice;
    private ITreeNode next;

    /**
     * constructor for an edge
     * @param choice the value of the edge
     * @param next the nodes/leaves which come after the edge
     */
    public Edge(String choice, ITreeNode next) {
        this.choice = choice;
        this.next = next;
    }

    /**
     * gets the choice of an edge
     * @return String which is the choice
     */
    public String getChoice(){
        return this.choice;
    }

    /**
     * gets the next node of an edge
     * @return an ITreeNode which is the next node/leaf
     */
    public ITreeNode getNext(){
        return this.next;
    }

}
