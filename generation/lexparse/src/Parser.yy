%{
	import java.io.*;
	import parse.lexer.Lexer;
	import parse.errhandler.ErrorHandler;
%}

%token context 
%token map
%token exists
%token instanceOf
%token source
%token eq_classes



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


%%

ldl : Contexts | Predicates

Contexts : Context
Contexts : Context Contexts

Predicates : Predicate
Predicates : Predicate Predicates

Context : context PathName "{" 
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

Binary : not Binary %prec UNARY
Binary : "(" Binary ")"
Binary : BinaryExp
Binary : Binary SetOp BinaryExp
Binary : Binary SetOp "(" Binary ")"

BinaryExp : Predicate
BinaryExp : Expression Relation Expression


Expression : AttributeCall
Expression : OperationCall
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

Predicate : Identifier "(" Parametres ")" 

Parametres :

Identifier : identifier
Type : identifier
SimpleName : identifier
String : string
Number : number

SetOp : and | or | xor
Relation : "<" | ">" | "=" | notEqual | lessEqual | moreEqual

%%

private Lexer lexer;
private double value;

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
	/* КАК ЭТО РАБОТАЕТ????? P.S. Восстановление парсера после фейла.*/
	yyerrflag = 3;
}
  
public void setYylval(ParserVal yylval) {
	this.yylval = yylval;
}

public Parser(java.io.Reader in, ErrorHandler errHandler){
	lexer = new Lexer(in, this, errHandler);
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