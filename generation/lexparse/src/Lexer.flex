package parse.lexer;
import parse.parser.*;
import parse.errhandler.*;

%%

%public
%class Lexer
%implements ParserTokens
%byaccj
%unicode
%line
%column 


%{
	private Parser yyparser;
	private ErrorHandler errHandler;

  /* constructor taking an additional parser object */
	public Lexer(java.io.Reader r, Parser yyparser, ErrorHandler errHandler) {
		this(r);
		this.yyparser = yyparser;
		this.errHandler = errHandler;
	}
	
	public int getCurrentLineNo(){
		return yyline;
	}
	
	public int getCurrentColumnNo(){
		return yycolumn;
	}
	
	public String getCurrentToken(){
		return yytext();
	}
	
	public String getCurrentFileName(){
		// TODO ������� ��� ����� �� �������, ���� �����, ��� ������� ���� �����
		return "";
	}
%}

context = "context"
extends = "extends"
when = "when"
instanceOf = "instanceof"
map = "map"
exists = "exists"
source = "source"
eq_classes = "eq_classes"
If = "if"

ldltrue = "ldltrue"
ldlfalse = "ldlfalse"

set = "Set"

notEqual = "!=" 
lessEqual = "<="
moreEqual = ">="

and = "&&"
or = "||"
not = "!"
xor = "^"

ldltrue = "true"
ldlfalse = "false"

badidentifier = [0-9]+[_a-zA-Z]+[_a-zA-Z0-9]*

comment = "--"[^\n\r]*
identifier = [_a-zA-Z][_a-zA-Z0-9]*
string = "'"[^']*"'"
number = [0-9]+ | [0-9]+"."[0-9]+ 

whitespace = [ \t\n\r]+

errorQuote = "\""
error = .



%%
":" |
";" |
"." |
"'" |
"," |
"=" |
"<" |
">" |
"(" |
")" |
"{" |
"}" |
"[" |
"]" |
"+" |
"-" |
"*" |
"/" { return (int) yycharat(0); }

{ context } { return context; }
{ instanceOf} { return instanceOf; }
{ map } { return map; }
{ exists } { return exists; }
{ source } { return source; }
{ eq_classes } { return eq_classes; }

{ If } { return If; }

{ ldltrue } { return ldltrue; }
{ ldlfalse } { return ldlfalse; }

{ set } { return set; }

{ notEqual } { return notEqual; }
{ lessEqual } { return lessEqual; }
{ moreEqual } { return moreEqual; }

{ and } { return and; }
{ or } { return or; }
{ not } { return not; }
{ xor } { return xor; }

{ badidentifier } { 
	errHandler.addError( new ParseError( ErrorClass.syntax, ErrorType.InvalidIdentifier,
		  getCurrentLineNo(), getCurrentColumnNo(), "������������� ������ ���������� � ����� ��� ����� �������������."));
}

{ number } {  
	ParserVal yylval = new ParserVal(Double.parseDouble(yytext()));
	yyparser.setYylval(yylval);
	return number; }
	
{ identifier } { 
	ParserVal yylval = new ParserVal(yytext());
	yyparser.setYylval(yylval);
	return identifier; }
	
{ string  } { 
	ParserVal yylval = new ParserVal(yytext().substring(1, yytext().length() - 1));
	yyparser.setYylval(yylval);
	return string; }

{ comment } { }
{ whitespace } { }

{ errorQuote } { 
	errHandler.addError( new ParseError(  ErrorClass.syntax, ErrorType.Quote,
		getCurrentLineNo(), getCurrentColumnNo(),"�������� ���������� ������ �������."));
}
	

{ error } { 
	errHandler.addError( new ParseError(  ErrorClass.syntax, ErrorType.UnknownToken, 
		getCurrentLineNo(), getCurrentColumnNo(),
		"����������� �����: " + getCurrentToken() +  "."));
}