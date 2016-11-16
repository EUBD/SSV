import java.util.List;

/**
 * Created by den on 11/11/16.
 */
public interface ISTree {



    void newLvl(String name);
    boolean add(String name, int lvl);
    SNode getNodeByIndex(int index);
    boolean upToLevel(int lvl);






}
