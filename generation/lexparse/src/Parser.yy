%{
	import java.io.*;
	import parse.lexer.Lexer;
	import parse.errhandler.ErrorHandler;
%}

%token context 
%token map
%token exists
%token instanceOf

%left ":"
%token <sval> identifier
%token <dval> number

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
	| Condition
	| Constraint
	
Description : Identifier instanceOf Type ";"

Identifier : identifier
Type : identifier
SimpleName : identifier


%%

private Lexer lexer;
private double value;

private int yylex () {
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