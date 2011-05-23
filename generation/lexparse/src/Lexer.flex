package parse.lexer;
import parse.parser.*;
import static parse.parser.ParserTokens;

%%

%public
%class Lexer
%byaccj
%unicode


%{
	private Parser yyparser;

  /* constructor taking an additional parser object */
	public Lexer(java.io.Reader r, Parser yyparser) {
		this(r);
		this.yyparser = yyparser;
	}
%}

intNumber = [0-9]+ 
realNumber = [0-9]+"."[0-9]+ 

whitespace = [ \t\n\r]+

%%
"(" |
")" |
"+" |
"-" |
"*" |
"/" { return (int) yycharat(0); }

{ intNumber } { 
	ParserVal yylval = new ParserVal(Integer.parseInt(yytext()));
	yyparser.setYylval(yylval);
	return intNumber; 
}

{ realNumber } { 
	ParserVal yylval = new ParserVal(Double.parseDouble(yytext()));
	yyparser.setYylval(yylval);
	return realNumber; 
}

{ whitespace } { }