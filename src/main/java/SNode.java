import java.util.ArrayList;
import java.util.List;

public class SNode implements ISNode {

    private SNode parent;

    private List<SNode> children;
    private String name;
    private int lvl;
    private double weight;
    private double Maxheight;
    private double quantityChildren;
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
    public void setHeight(double height) {
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
        double newMaxHeight = Maxheight+1;
        parent.setHeight(newMaxHeight);
        refreshValue();
    }

    private void refreshValue()
    {
        weight = Maxheight*quantityChildren+1;
    }

}
