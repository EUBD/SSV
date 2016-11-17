import static org.junit.Assert.*;

/**
 * Created by den on 11/14/16.
 */
public class IBuilderSignatursTest {
    @org.junit.Test
    public void build() throws Exception {


        STree tree = new STree();


        boolean test = tree.add("one",1);

        assertEquals(test,true);

        test = tree.add("two",2);

        assertEquals(test,true);

        test = tree.add("one",1);

        assertEquals(test,true);
    }

    @org.junit.Test
    public void deObfuscation() throws Exception {

    }

    @org.junit.Test
    public void compareSignature() throws Exception {

    }

    @org.junit.Test
    public void saveFile() throws Exception {

    }

    @org.junit.Test
    public void sentToDbase() throws Exception {

    }

}