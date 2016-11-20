package Tree;


public class STree implements ISTree {


    public SNode headNode;
    public SNode currentNode;
    private int QSheet;
    private int maxWeight;


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
            if(currentNode.getWeight() > maxWeight)
            {
                maxWeight = currentNode.getWeight();
            }
            currentNode.uplvl();
            currentNode = currentNode.getParent();

        }

        QSheet++;
        return true;
    }

    @Override
    public int getEpsilon() {
        upToLevel(0);
        int epsilon = maxWeight / QSheet;
        return epsilon < 1 ? 1 : epsilon;
    }
}
