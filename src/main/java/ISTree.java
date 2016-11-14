import java.util.List;

/**
 * Created by den on 11/11/16.
 */
public interface ISTree {



    void SetNewNode(SNode node);
    boolean addToTree(SNode paren,SNode child);
    SNode getNodeByIndex(int index);
    void upToLevel(int lvl);






}
