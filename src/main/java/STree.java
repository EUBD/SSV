import java.util.List;

/**
 * Created by den on 11/14/16.
 */
public class STree implements ISTree {


    public SNode headNode;
    public SNode currentNode;


    public STree(){
        headNode = new SNode();
        currentNode = headNode;
    }

    @Override
    public void SetNewNode(SNode node) {

    }

    @Override
    public boolean addToTree(SNode paren, SNode child) {
        return false;
    }

    @Override
    public SNode getNodeByIndex(int index) {
        return null;
    }

    @Override
    public void upToLevel(int lvl) {

    }
}
