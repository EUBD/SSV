package UBuilder;

import gen.ECMAScriptLexer;
import gen.ECMAScriptParser;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;
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

        public static String toStringASCII(String input) {

            ECMAScriptParser parser = new Builder.Parser(input).build();
            ParseTree tree = parser.program();

            StringBuilder builder = new StringBuilder();

            walk(tree, builder);


            return builder.toString();
        }

        @SuppressWarnings("unchecked")
        private static void walk(ParseTree tree, StringBuilder builder) {

            List<ParseTree> firstStack = new ArrayList<ParseTree>();
            firstStack.add(tree);
            int lvl = 0;
            List<List<ParseTree>> childListStack = new ArrayList<List<ParseTree>>();
            childListStack.add(firstStack);

            while (!childListStack.isEmpty()) {

                List<ParseTree> childStack = childListStack.get(childListStack.size() - 1);

                if (childStack.isEmpty()) {
                    childListStack.remove(childListStack.size() - 1);
                } else {
                    tree = childStack.remove(0);

                    String node = tree.getClass().getSimpleName().replace("Context", "");
                    node = Character.toLowerCase(node.charAt(0)) + node.substring(1);


                    lvl = childListStack.size();
                    builder.append(node.startsWith("terminal") ? tree.getText() : node)
                            .append("\n");

                    if (tree.getChildCount() > 0) {
                        List<ParseTree> children = new ArrayList<ParseTree>();
                        for (int i = 0; i < tree.getChildCount(); i++) {
                            children.add(tree.getChild(i));
                        }
                        childListStack.add(children);
                    }
                }
            }
        }

        private static boolean isReservName(String name)
        {

             String[] _LITERAL_NAMES = {
                    "[", "]", "(", ")", "{", "}", ";", ",",
                    "=", "?", ":", ".", "++", "--", "+", "-", "~", "!",
                    "*", "/", "%", ">>", "<<", ">>>", "<", ">", "<=", ">=",
                    "==", "!=", "===", "!==", "&", "^", "|", "&&", "||",
                    "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=",
                    "^=", "|=", "null","break", "do", "instanceof",
                    "typeof", "case", "else", "new", "var", "catch", "finally",
                    "return", "void", "continue", "for", "switch", "while", "debugger",
                    "function", "this", "with", "default", "if", "throw", "delete",
                    "in", "try", "class", "enum", "extends", "super", "const",
                    "export", "import", "eval","alert", "replace" , "split","program"
            };
            String[] _SYMBOLIC_NAMES = {
                    "RegularExpressionLiteral", "LineTerminator", "OpenBracket", "CloseBracket",
                    "OpenParen", "CloseParen", "OpenBrace", "CloseBrace", "SemiColon", "Comma",
                    "Assign", "QuestionMark", "Colon", "Dot", "PlusPlus", "MinusMinus", "Plus",
                    "Minus", "BitNot", "Not", "Multiply", "Divide", "Modulus", "RightShiftArithmetic",
                    "LeftShiftArithmetic", "RightShiftLogical", "LessThan", "MoreThan", "LessThanEquals",
                    "GreaterThanEquals", "Equals", "NotEquals", "IdentityEquals", "IdentityNotEquals",
                    "BitAnd", "BitXOr", "BitOr", "And", "Or", "MultiplyAssign", "DivideAssign",
                    "ModulusAssign", "PlusAssign", "MinusAssign", "LeftShiftArithmeticAssign",
                    "RightShiftArithmeticAssign", "RightShiftLogicalAssign", "BitAndAssign",
                    "BitXorAssign", "BitOrAssign", "NullLiteral", "BooleanLiteral", "DecimalLiteral",
                    "HexIntegerLiteral", "OctalIntegerLiteral", "Break", "Do", "Instanceof",
                    "Typeof", "Case", "Else", "New", "Var", "Catch", "Finally", "Return",
                    "Void", "Continue", "For", "Switch", "While", "Debugger", "Function",
                    "This", "With", "Default", "If", "Throw", "Delete", "In", "Try", "Class",
                    "Enum", "Extends", "Super", "Const", "Export", "Import", "Implements",
                    "Let", "Private", "Public", "Interface", "Package", "Protected", "Static",
                    "Yield", "Identifier", "StringLiteral", "WhiteSpaces", "MultiLineComment",
                    "SingleLineComment", "UnexpectedCharacter"
            };

            for (String lit: _SYMBOLIC_NAMES) {
                if(lit.compareTo(name) == 0)
                    return true;

            }
            for (String lit: _LITERAL_NAMES) {
                if(lit.compareTo(name) == 0)
                    return true;
            }


            return false;

        }



    }
}
