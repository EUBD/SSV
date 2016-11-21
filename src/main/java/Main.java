import separation.Separation;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Separation test = new Separation("/home/den/Documents/source", "/home/den/Documents/result");

        try {
            test.startSeparation();
        }catch (IOException ignored)
        {
            System.out.print(ignored.getMessage());
        }

        //System.out.print(Builder.Tree.toStringASCII(sb.toString()));




    }
}
