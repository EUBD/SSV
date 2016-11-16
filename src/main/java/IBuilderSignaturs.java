/**
 * Created by den on 11/11/16.
 */
public interface IBuilderSignaturs {


            /*
            * Main method
            */
            public void Build();

            /*
            * Save file
            * */



            public SNode DeObfuscation(SNode node);

            public boolean CompareSignature(STree tree);

            public void SaveFile();

            public boolean SentToDbase();





}
