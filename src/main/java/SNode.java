import java.util.ArrayList;
import java.util.List;

/**
 * Created by den on 11/11/16.
 */
public class SNode implements ISNode {

    private SNode parent;

    private List<SNode> children;
    private String name;
    private int lvl;
    private double weight;
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



    public SNode getParent() {
        return parent;
    }


    public int getLvl() {
        return lvl;
    }


    @Override
    public void incQuantity() {
        quantityChildren+=0.1;
        refreshValue();

    }

    @Override
    public void setHeight(int height) {
        if(height>Maxheight) {
            this.Maxheight = height;
            refreshValue();
        }

    }

    @Override
    public void addChild(SNode child) {
        children.add(child);
    }

    private void refreshValue()
    {
        weight = Maxheight*quantityChildren+0.1;
    }

}
