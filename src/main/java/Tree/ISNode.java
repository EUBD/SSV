package Tree;

public interface ISNode {

    void incQuantity();
    void setHeight(int height);
    void addChild(SNode child);
    void uplvl();
    String genName();
    SNode nextChild();
    boolean isChildrenEmpty();

}
