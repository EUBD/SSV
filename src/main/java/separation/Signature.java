package separation;

import Tree.STree;

import java.io.File;


public class Signature {

    private String name;
    private STree tree;
    private File file;
    private boolean ismove;
    private String path;

    public boolean ismove() {
        return ismove;
    }

    public void setIsmove(boolean ismove) {
        this.ismove = ismove;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public STree getTree() {
        return tree;
    }

    public void setTree(STree tree) {
        this.tree = tree;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
