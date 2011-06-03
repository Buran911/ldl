package parse.lexer;
import parse.parser.*;
import parse.errhandler.*;
import parse.exceptions.UnknownTokenException;
import parse.exceptions.InvalidIdentifierException;

%%

%public
%class Lexer
%implements ParserTokens
%byaccj
%unicode
%line
%column 

%yylexthrow UnknownTokenException
%yylexthrow InvalidIdentifierException


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
		// TODO Забрать имя файла от парсера, если нужно, или удалить этот метод
		return "";
	}
%}

context = "context"
instanceOf = "instanceof"
map = "map"
exists = "exists"

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

{ set } { return set; }

{ notEqual } { return notEqual; }
{ lessEqual } { return lessEqual; }
{ moreEqual } { return moreEqual; }

{ and } { return and; }
{ or } { return or; }
{ not } { return not; }
{ xor } { return xor; }

{ badidentifier } { 
	errHandler.add( new ParseError( ErrorClass.syntax, ErrorType.InvalidIdentifier,
		  getCurrentLineNo(), getCurrentColumnNo(), "Идентификатор должен начинаться с буквы или знака подчеркивания.");
	);
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
	ParserVal yylval = new ParserVal(yytext());
	yyparser.setYylval(yylval);
	return string; }

{ comment } { }
{ whitespace } { }

{ errorQuote } { 
	errHandler.add( new ParseError(  ErrorClass.syntax, ErrorType.Quote,
		getCurrentLineNo(), getCurrentColumnNo(),"Возможно поставлена лишняя кавычка.");
}
	

{ error } { 
	errHandler.add( new ParseError(  ErrorClass.syntax, ErrorType.UnknownToken, 
		getCurrentLineNo(), getCurrentColumnNo(),
		"Неизвестный токен: " + getCurrentToken() +  ".");
}