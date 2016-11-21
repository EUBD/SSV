package Tree;

import java.util.ArrayList;
import java.util.List;

public class SNode implements ISNode {

    private SNode parent;

    private List<SNode> children;
    private String name;
    private int lvl;
    private int currentChildIndex;

    private int weight;
    private int Maxheight;
    private int quantityChildren;
    SNode(){
        children = new ArrayList<SNode>();
        weight = 0;
        Maxheight = 0;
        quantityChildren = 0;
        lvl = 0;
        name = "Head";
    }

    SNode(String name,SNode parent){
        children = new ArrayList<SNode>();
        this.name = name;
        this.parent = parent;
        parent.addChild(this);
        this.lvl = parent.getLvl()+1;
    }

    public int getWeight() {
        return weight;
    }


    public SNode getParent() {
        return parent;
    }


    public int getLvl() {
        return lvl;
    }


    @Override
    public void incQuantity() {
        quantityChildren++;
        refreshValue();

    }

    @Override
    public void setHeight(int height) {
        if(height>Maxheight) {
            this.Maxheight = height;

        }
        refreshValue();
    }

    @Override
    public void addChild(SNode child) {
        incQuantity();
        children.add(child);
    }

    @Override
    public void uplvl() {
        int newMaxHeight = Maxheight+1;
        parent.setHeight(newMaxHeight);
        refreshValue();
    }

    @Override
    public String genName() {
        return name;
    }

    @Override
    public SNode nextChild() {
        if(currentChildIndex>=children.size()){
            return null;
        }
        SNode child = children.get(currentChildIndex);
        currentChildIndex++;
        return child;
    }

    @Override
    public boolean isChildrenEmpty() {
        if(children.isEmpty())
        {
            return true;
        }
        return false;
    }

    private void refreshValue()
    {
        weight = Maxheight*quantityChildren+1;
    }

}
