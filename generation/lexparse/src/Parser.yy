%{
  import java.io.*;
  import parse.lexer.Lexer;
 %}

%token <ival> intNumber
%token <dval> realNumber

%left "+" "-"
%left "*" "/"

%left UMINUS 
%type <dval> Number
%type <dval> Expr

%%

Expr : Expr "+" Number{
	$$ = $1 + $3;
}

Expr : Expr "-" Number{
	$$ = $1 - $3;
}

Expr : Expr "*" Number{
	$$ = $1 * $3;
}

Expr : Expr "/" Number{
	$$ = $1 / $3;
}


Expr : Number{
	$$ = $1;
}

Expr : "(" Expr ")"

Expr : "-" Expr %prec UMINUS {
	$$ = -1 * $$;
}

Number : intNumber{
	$$ = $1;
}
Number : realNumber{
	$$ = $1;
}

%%

private Lexer lexer;

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