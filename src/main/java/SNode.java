import java.util.List;

/**
 * Created by den on 11/11/16.
 */
public class SNode implements ISNode {

    private SNode parent;
    private List<SNode> children;
    private String name;
    private double weight;
    private int Maxheight;
    private int quantity;

    SNode(){
        weight = 0;
        Maxheight = 0;
        quantity = 0;
        name = "Head";
    }

    SNode(String name,SNode parent){

        this.name = name;
        this.parent = parent;
    }

    @Override
    public void incQuantity() {
        quantity+=0.1;
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
        child.parent = this;
        children.add(child);
    }

    private void refreshValue()
    {
        weight = Maxheight*quantity+0.1;
    }

}
