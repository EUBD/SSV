package CBuilder;

import Tree.STree;

import java.io.IOException;

/**
 * Created by den on 11/19/16.
 */
public class CompareSignaturs implements ICompareSignaturs {


    @Override
    public boolean Compare(STree one, STree two) throws IOException {
        int epsilon = one.getEpsilon() < two.getEpsilon() ? one.getEpsilon()  : two.getEpsilon();
        return true;
    }
}
