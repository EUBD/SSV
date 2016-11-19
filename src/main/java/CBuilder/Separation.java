package CBuilder;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by den on 11/19/16.
 */
public class Separation {

    private String SourceFolder;
    private String ResultFolder;
    private CompareSignaturs comparer;
    private List<Signature> SignatureList;


    public Separation(String SourceFolder, String ResultFolder) throws IOException{

        this.SourceFolder = SourceFolder;
        this.ResultFolder = ResultFolder;
        comparer = new CompareSignaturs();
        SignatureList = new ArrayList<Signature>();
        File sourcefolder = new File(SourceFolder);

        File[] folderEntries = sourcefolder.listFiles();
        try {
            for (File entry : folderEntries) {
                if (!entry.isDirectory()) {

                    Signature signature = new Signature();
                    signature.setFile(entry);
                    signature.setName(entry.getName());

                    SignatureList.add(signature);
                }
            }
        }catch (NullPointerException ignored){

            throw new IOException("Uncorrected path\n");
        }


    }

    private File CreatePath(String folderName,String fileName)
    {
        File createFolder = new File(ResultFolder+"/"+folderName);

        if(!createFolder.exists())
        {
            if(!createFolder.mkdir()){

            }
        }

        File path =  new File(ResultFolder+"/"+folderName+"/"+fileName);
        return path;
    }

    private boolean move(File file,File to)
    {


        if(!file.renameTo(to)){
            return false;
        }

        return true;
    }

    private void buildTree(Signature signature)
    {
        signature.setTree(Builder.Tree.build(signature.getFile().getAbsolutePath()));
    }

    public void startSeparation() throws IOException {
        Integer count = 0;
        for (Signature signature: SignatureList) {
            count = 0;
            if(!signature.ismove())
            {
                if(signature.getTree() == null)
                    buildTree(signature);
                for (Signature Comsignature : SignatureList){
                    if(!signature.ismove()) {
                        if (signature.getTree() == null)
                            buildTree(signature);
                        if(comparer.Compare(signature.getTree(),Comsignature.getTree()))
                        {
                            count++;
                            move(Comsignature.getFile(), CreatePath(count.toString(),Comsignature.getName()));
                            Comsignature.setIsmove(true);
                        }
                    }

                }
            }


        }

    }




}
