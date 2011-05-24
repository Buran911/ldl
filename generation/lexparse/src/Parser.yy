%{
  import java.io.*;
  import parse.lexer.Lexer;
 %}

%token <ival> intNumber
%token <dval> realNumber

%left "+" "-"
%left "*" "/"

%type <dval> Number
%type <dval> Expr

%left UMINUS 
%%

Expr : Expr "+" Expr{
	$$ = $1 + $3;
}

Expr : Expr "-" Expr{
	$$ = $1 - $3;
}

Expr : Expr "*" Expr{
	$$ = $1 * $3;
}

Expr : Expr "/" Expr{
	$$ = $1 / $3;
}


Expr : Number{
	$$ = $1;
}

Expr : "(" Expr ")"{
	$$ = $2;
}

Expr : "-" Expr %prec UMINUS {
	$$ = -1 * $2;
}

Number : intNumber{
	$$ = $1;
}
Number : realNumber{
	$$ = $1;
}

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
}
  
public void setYylval(ParserVal yylval) {
	this.yylval = yylval;
}

public Parser(java.io.Reader in){
	lexer = new Lexer(in, this);
}

public void parse(){
	yyparse();
	value = yyval.dval;
}

public double getValue(){
	return value;
}