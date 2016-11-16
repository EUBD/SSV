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
    public void newLvl(String name) {

        currentNode = new SNode(name,currentNode);
    }

    @Override
    public boolean add(String name, int lvl) {

        if(lvl > currentNode.getLvl()){

            newLvl(name);


        }else{

            if(upToLevel(lvl-1))
            {
                newLvl(name);
            }
            else {
                return false;
            }

        }


        return true;
    }

    @Override
    public SNode getNodeByIndex(int index) {
        return null;
    }

    @Override
    public boolean upToLevel(int lvl) {

        while (currentNode.getLvl() != lvl)
        {
            currentNode = currentNode.getParent();

        }

        return true;
    }
}
