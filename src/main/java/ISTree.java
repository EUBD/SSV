import java.util.List;

public interface ISTree {



    void newLvl(String name);
    boolean add(String name, int lvl);
    SNode getNodeByIndex(int index);
    boolean upToLevel(int lvl);






}
