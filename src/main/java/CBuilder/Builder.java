package CBuilder;

import Deobfuscator.Deobfuskator;
import gen.ECMAScriptLexer;
import gen.ECMAScriptParser;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import Tree.STree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Builder {

    private static final DescriptiveBailErrorListener ERROR_LISTENER = new DescriptiveBailErrorListener();

    // No need to instantiate this class.
    private Builder() {
    }

    public static final class Lexer {

        private ECMAScriptLexer lexer;

        public Lexer(String input) {
            this(new ANTLRInputStream(input));
        }

        public Lexer(ANTLRInputStream input) {
            this.lexer = new ECMAScriptLexer(input);
            this.lexer.removeErrorListeners();
            this.lexer.addErrorListener(ERROR_LISTENER);
        }

        public Lexer withStrictMode(boolean strictMode) {
            this.lexer.setStrictMode(strictMode);
            return this;
        }

        public Lexer withErrorListener(ANTLRErrorListener listener) {
            this.lexer.removeErrorListeners();
            this.lexer.addErrorListener(listener);
            return this;
        }

        public ECMAScriptLexer build() {
            return this.lexer;
        }
    }

    public static final class Parser {

        private ECMAScriptParser parser;

        public Parser(String input) {
            this(new ANTLRInputStream(input));
        }

        public Parser(ANTLRInputStream input) {
            ECMAScriptLexer lexer = new ECMAScriptLexer(input);
            lexer.removeErrorListeners();

            lexer.addErrorListener(ERROR_LISTENER);
            this.parser = new ECMAScriptParser(new CommonTokenStream(lexer));
            this.parser.removeErrorListeners();
            this.parser.addErrorListener(ERROR_LISTENER);
        }

        public Parser(ECMAScriptLexer lexer) {
            this.parser = new ECMAScriptParser(new CommonTokenStream(lexer));
            this.parser.removeErrorListeners();
            this.parser.addErrorListener(ERROR_LISTENER);
        }

        public Parser withErrorListener(ANTLRErrorListener listener) {
            this.parser.removeErrorListeners();
            this.parser.addErrorListener(listener);
            return this;
        }

        public ECMAScriptParser build() {
            return this.parser;
        }
    }

    public static final class Tree {

        public static STree build(String input) {

            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader in = new BufferedReader(new FileReader(input));
                try {
                    String s;
                    while ((s = in.readLine()) != null) {
                        sb.append(s);
                        sb.append("\n");
                    }
                } finally {

                    in.close();
                }
            } catch(IOException e) {
                throw new RuntimeException(e);
            }


            ECMAScriptParser parser = new Builder.Parser(sb.toString()).build();
            ParseTree tree = null;
            try {
                tree = parser.program();
            }
            catch (RuntimeException ignored)
            {
                throw new RuntimeException(ignored);
            }


            StringBuilder builder = new StringBuilder();

            STree Rtree = builder(tree, builder);
            byte []arr = builder.toString().getBytes();
            return Rtree;
        }

        @SuppressWarnings("unchecked")
        private static STree builder(ParseTree tree, StringBuilder builder) {

            List<ParseTree> firstStack = new ArrayList<ParseTree>();
            firstStack.add(tree);
            int lvl = 0;
            Deobfuskator deobfuskator = new Deobfuskator();
            STree BuildTree = new STree();
            List<List<ParseTree>> childListStack = new ArrayList<List<ParseTree>>();
            childListStack.add(firstStack);

            while (!childListStack.isEmpty()) {

                List<ParseTree> childStack = childListStack.get(childListStack.size() - 1);

                if (childStack.isEmpty()) {
                    childListStack.remove(childListStack.size() - 1);
                } else {
                    tree = childStack.remove(0);

                    String node = tree.getClass().getSimpleName().replace("Context", "");
                    lvl = childListStack.size();

                    deobfuskator.Deobfuscate(node,tree.getText(),lvl);

                    StringBuilder Dbuild = deobfuskator.getString();

                    if(Dbuild != null)
                    {
                        BuildTree.add(Dbuild.toString(),lvl);
                    }


                    if (tree.getChildCount() > 0) {
                        List<ParseTree> children = new ArrayList<ParseTree>();
                        for (int i = 0; i < tree.getChildCount(); i++) {
                            children.add(tree.getChild(i));
                        }
                        childListStack.add(children);
                    }
                }
            }
            return BuildTree;
        }




    }
}
