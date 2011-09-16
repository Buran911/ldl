%{
	import java.io.*;
	import parse.lexer.Lexer;
	import parse.errhandler.ErrorHandler;
	import parse.util.Source;
	import java.io.StringReader;
	import parse.errhandler.*;
%}

%token context 
%token map
%token exists
%token instanceOf
%token source
%token eq_classes
%token If



%left ":"
%token <sval> identifier
%token <dval> number
%token ldltrue
%token ldlfalse

%left "." "," "'" "\""

%token set

%left "+" "-"
%left "*" "/"

%token <sval> string
%type <dval> number

%left UMINUS 

%token notEqual
%token lessEqual
%token moreEqual

%token ldltrue
%token ldlfalse
%token instanceOf

%left and
%left or
%left not
%left xor

%left UNOT 


%left "="
%left "<"
%left ">"


%left "("
%left ")"
%left "{"
%left "}"
%left "["
%left "]"

%left UNARY 


%type <obj> ldl
%type <obj> ldlExpressions
%type <obj> ldlExpression
%type <obj> Context
%type <obj> PredicateImpl
%type <obj> Blocks
%type <obj> Block
%type <obj> Description
%type <obj> Source
%type <obj> Constraints
%type <obj> Constraint
%type <obj> EqClasses
%type <obj> srcBlocks
%type <obj> srcBlock
%type <obj> srcExprs
%type <obj> srcExpr
%type <obj> Elements
%type <obj> Element
%type <obj> Binary
%type <obj> Condition
%type <obj> BinaryExp
%type <obj> SetOp
%type <obj> Predicate
%type <obj> Expression
%type <obj> Relation
%type <obj> IfBlocks
%type <obj> IfBlock
%type <obj> AttributeCall
%type <obj> Variable
%type <obj> Literal
%type <obj> Boolean
%type <obj> OperationCall
%type <obj> Parametres
%type <obj> PredicateImpl
%type <obj> FunctionalPart
%type <obj> FormalParams
%type <obj> Relation
%type <obj> Set
%type <obj> Number
%type <obj> String
%type <obj> Identifier
%type <obj> Type
%type <obj> PathName
%type <obj> SimpleName



%%
ldl : ldlExpressions
ldlExpressions : ldlExpression
ldlExpressions : ldlExpression ldlExpressions

ldlExpression : Context  
ldlExpression : PredicateImpl


Context : context SimpleName "{" 
	Blocks
"}"

PathName : SimpleName
PathName : SimpleName ":"":" SimpleName
 
Blocks : Block
Blocks : Block Blocks 
 
Block : Description
	| Source
	| Constraint
	| EqClasses
	
EqClasses : eq_classes "{"
	Constraints
"}"
	
Description : Identifier instanceOf Type ";"

Constraints : Constraint
Constraints : Constraint Constraints

Source: source "{"
	srcBlocks
"}"

srcBlocks : srcBlock
srcBlocks : srcBlock srcBlocks

srcBlock : Identifier ":" "{" 
	srcExprs
"}"

srcExprs : srcExpr
srcExprs : srcExpr srcExprs

srcExpr : Identifier "=" Identifier ";"
srcExpr : Identifier "=" Set ";"
srcExpr : Identifier "=" Number ";"
srcExpr : Identifier "=" String ";"

Set : "{" Elements "}"

Elements : Element
Elements : Element "," Elements

Element : String
Element : Number

Constraint : Binary ";"
Constraint : Condition

Binary : not Binary %prec UNARY
Binary : "(" Binary ")"
Binary : BinaryExp
Binary : Binary SetOp BinaryExp
Binary : Binary SetOp "(" Binary ")"

BinaryExp : Predicate
BinaryExp : Expression Relation Expression

Condition : If Binary "{"
	IfBlocks
"}"

IfBlocks : IfBlock
IfBlocks : IfBlock IfBlocks
IfBlock : Constraint
IfBlock : EqClasses


Expression : AttributeCall
Expression : ArithmeticExpression
Expression : Variable
Expression : Literal
Literal : String
Literal : Number
Literal : Boolean
Boolean : ldltrue
Boolean : ldlfalse

AttributeCall : AttributeCall "." Identifier
AttributeCall : Variable "." Identifier



Variable : Identifier

Predicate : OperationCall
Predicate : Variable "." OperationCall
Predicate : AttributeCall "." OperationCall

OperationCall : Identifier "(" Parametres ")" 

Parametres :




PredicateImpl : PathName FunctionalPart "(" FormalParams ")"  "{"
	Constraints 
"}"

FunctionalPart :
FunctionalPart : "<" String "," Number ">"

FormalParams : 

Identifier : identifier
Type : identifier
SimpleName : identifier
String : string
Number : number

SetOp : and | or | xor
Relation : "<" | ">" | "=" | notEqual | lessEqual | moreEqual

%%

private Lexer lexer;
private Source src;
private ErrorHandler errHandler;
private ParseTree tree;
  
public ParseTree getTree(){
  	return tree;
}


private int yylex (){
	int yyl_return = -1;
	try {
		yyl_return = lexer.yylex();
	}catch (IOException e) {
		System.err.println("IO error :"+e);
	}
	return yyl_return;
}

  /* error reporting */
public void yyerror  (String error) {
	System.err.println ("Error: " + error);
	errHandler.addError( new ParseError(ErrorClass.syntax, ErrorType.Syntax, lexer.getCurrentLineNo(), lexer.getCurrentColumnNo()));
	/* КАК ЭТО РАБОТАЕТ????? P.S. Восстановление парсера после фейла.*/
	yyerrflag = 3;
}
  
public void setYylval(ParserVal yylval) {
	this.yylval = yylval;
}

public Parser(Source src, ErrorHandler errHandler){
	this.src = src;
	this.errHandler = errHandler;
	lexer = new Lexer(new StringReader(src.getProgram()), this, errHandler);
}

public void parse(){
	yyparse();
}

public void setDebugModeOn(){
	yydebug = true;
}

public double getValue(){
	return value;
}