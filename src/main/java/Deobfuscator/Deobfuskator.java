package Deobfuscator;

public class Deobfuskator implements IDeobfuskator,IContextDeobfuscator {

    StringBuilder beforeDeobfuscatetext;
    private StringBuilder deobfuscatetext;
    //String[] _IGNORE_SUMBOL = {};

            String[] _LITERAL_NAMES = {
            "null","break", "do", "instanceof","+",
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
            "SingleLineComment", "UnexpectedCharacter","+"
    };
    private final int LITERAL = 1;
    private final int NOLITERAL = 2;
    int BeforeType = NOLITERAL;
    boolean ready = false;




    @Override
    public StringBuilder deobfuscateContext(StringBuilder Context) {
        return null;
    }

    @Override
    public boolean isReserName(String Context) {

        for (String lit: _SYMBOLIC_NAMES) {
            if(lit.compareToIgnoreCase(Context) == 0)
                return true;

        }
        for (String lit: _LITERAL_NAMES) {
            if(lit.compareToIgnoreCase(Context) == 0)
                return true;
        }

        return false;
    }

    boolean isIgnorSumbol(String text){

        return  false;
    }
    private int GetType(String text)
    {
        return text.startsWith("Terminal") ? LITERAL : NOLITERAL;
    }

    @Override
    public void Deobfuscate(String node, String tree) {

        if(GetType(node)==LITERAL)
        {
            if(BeforeType == NOLITERAL){

                if(isReserName(tree)){

                    deobfuscatetext = new StringBuilder();
                    deobfuscatetext.append(tree);
                }
                else
                {
                    deobfuscatetext = new StringBuilder();
                    deobfuscatetext.append("literal");
                }

                BeforeType = LITERAL;
            }
            ready = false;
        }
        else
        {
            try {
                beforeDeobfuscatetext = new StringBuilder();
                beforeDeobfuscatetext.append(deobfuscatetext);

                ready = true;
            }catch (NullPointerException e)
            {
                ready = false;
            }
            deobfuscatetext = new StringBuilder();
            deobfuscatetext.append(node);
            BeforeType = NOLITERAL;
        }

    }

    @Override
    public StringBuilder getString() {
        if(ready)
        {
            return beforeDeobfuscatetext;
        }
        return null;
    }


}
