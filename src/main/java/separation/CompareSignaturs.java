package separation;

import Tree.SNode;
import Tree.STree;

import java.io.IOException;


public class CompareSignaturs implements ICompareSignaturs {

    private int epsilon;
    @Override
    public boolean Compare(STree one, STree two) throws IOException {
        epsilon = one.getEpsilon() < two.getEpsilon() ? one.getEpsilon()  : two.getEpsilon();

        return compareNode(one.getHeadNode(),two.getHeadNode());
    }

    private boolean compareNode(SNode one, SNode two)
    {

        if(two == null)
        {
            epsilon -= one.getWeight();
            return epsilon >= 0;
        }

        int SubWeight = one.getWeight()>two.getWeight() ? one.getWeight() : two.getWeight();
        if(one.genName().equals(two.genName())){
            SNode childOne = one.nextChild();
            while(childOne != null)
            {

                if(!compareNode(childOne,two.nextChild())){
                    return false;
                }
                childOne = one.nextChild();

            }
        }
        else
        {
            epsilon -= SubWeight;
            return epsilon >= 0;
        }
        return true;
    }
}
