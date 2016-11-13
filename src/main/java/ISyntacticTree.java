import java.util.List;

/**
 * Created by den on 11/11/16.
 */
public interface ISyntacticTree {

    public List<Node> nodeList = null;


    public boolean addToTree(Node paren,Node child);
    public Node getNodeByIndex(int index);





}
