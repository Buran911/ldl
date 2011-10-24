%{
	import generation.languageconstants.Logical;
	import generation.languageconstants.Ratio;
	
	import java.io.IOException;
	import java.io.StringReader;
	
	import parse.errhandler.ErrorClass;
	import parse.errhandler.ErrorHandler;
	import parse.errhandler.ErrorType;
	import parse.errhandler.ParseError;
	import parse.lexer.Lexer;
	import parse.parsetree.Node;
	import parse.parsetree.ParseTree;
	import parse.parsetree.nodes.*;
	import parse.parsetree.nodes.Number;
	import parse.util.Source;
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
%token <bval> ldltrue
%token <bval> ldlfalse
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
%type <obj> Bool
%type <obj> OperationCall
%type <obj> Parametres
%type <obj> PredicateImpl
%type <obj> FunctionalPart
%type <obj> FormalParams
%type <obj> Relation
%type <obj> Set
%type <obj> Number
%type <obj> LString
%type <obj> Identifier
%type <obj> Type
%type <obj> PathName
%type <obj> SimpleName

%type <obj> ArithmeticExpression



%%
ldl : ldlExpressions{ 
	tree.saveNode( new ldl() ); 
	$$ = tree.getLast();
	 
	// Указатель на корень дерева
	tree.setRoot((Node)$$);
	
	(( ldlExpressions ) $1).setParent( (ldl)$$ );
}

ldlExpressions : ldlExpression{ 
	tree.saveNode( new ldlExpressions() ); 
	$$ = tree.getLast(); 
	(( ldlExpression ) $1).setParent( (ldlExpressions)$$ ); 
}

ldlExpressions : ldlExpression ldlExpressions{ 
	tree.saveNode( new ldlExpressions() ); 
	$$ = tree.getLast(); 
	(( ldlExpression ) $1).setParent( (ldlExpressions)$$ ); 
	(( ldlExpressions ) $2).setParent( (ldlExpressions)$$ ); 
}

ldlExpression : Context{ 
	tree.saveNode( new ldlExpression() ); 
	$$ = tree.getLast(); 
	(( Context ) $1).setParent( (ldlExpression)$$ ); 
}  

ldlExpression : PredicateImpl{ 
	tree.saveNode( new ldlExpression() ); 
	$$ = tree.getLast(); 
	(( PredicateImpl ) $1).setParent( (ldlExpression)$$ ); 
}  


Context : context SimpleName "{" 
	Blocks
"}"{ 
	tree.saveNode( new Context() ); 
	$$ = tree.getLast(); 
	(( SimpleName ) $2).setParent( (Context)$$ ); 
	(( Blocks ) $4).setParent( (Context)$$ ); 
}  

PathName : SimpleName{ 
	tree.saveNode( new PathName() ); 
	$$ = tree.getLast(); 
	(( SimpleName ) $1).setParent( (PathName)$$ ); 
}  

PathName : SimpleName ":"":" SimpleName{ 
	tree.saveNode( new PathName() ); 
	$$ = tree.getLast(); 
	(( SimpleName ) $1).setParent( (PathName)$$ ); 
	(( SimpleName ) $4).setParent( (PathName)$$ ); 
}  
 
Blocks : Block{ 
	tree.saveNode( new Blocks() ); 
	$$ = tree.getLast(); 
	(( Block ) $1).setParent( (Blocks)$$ ); 
}  

Blocks : Block Blocks { 
	tree.saveNode( new Blocks() ); 
	$$ = tree.getLast(); 
	(( Block ) $1).setParent( (Blocks)$$ ); 
	(( Blocks ) $2).setParent( (Blocks)$$ ); 
}  
 
Block : Description{ 
	tree.saveNode( new Block() ); 
	$$ = tree.getLast(); 
	(( Description ) $1).setParent( (Block)$$ ); 
}  

Block : Source{ 
	tree.saveNode( new Block() ); 
	$$ = tree.getLast(); 
	(( Src ) $1).setParent( (Block)$$ ); 
}  

Block : Constraint{ 
	tree.saveNode( new Block() ); 
	$$ = tree.getLast(); 
	(( Constraint ) $1).setParent( (Block)$$ ); 
}  

Block : EqClasses{ 
	tree.saveNode( new Block() ); 
	$$ = tree.getLast(); 
	(( EqClasses ) $1).setParent( (Block)$$ ); 
}  
	
EqClasses : eq_classes "{"
	Constraints
"}"{ 
	tree.saveNode( new EqClasses() ); 
	$$ = tree.getLast(); 
	(( Constraints ) $3).setParent( (EqClasses)$$ ); 
}  
	
Description : Identifier instanceOf Type ";"{ 
	tree.saveNode( new Description() ); 
	$$ = tree.getLast(); 
	(( Identifier ) $1).setParent( (Description)$$ ); 
	(( Type ) $3).setParent( (Description)$$ ); 
}  

Constraints : Constraint{ 
	tree.saveNode( new Constraints() ); 
	$$ = tree.getLast(); 
	(( Constraint ) $1).setParent( (Constraints)$$ ); 
}  

Constraints : Constraint Constraints{ 
	tree.saveNode( new Constraints() ); 
	$$ = tree.getLast(); 
	(( Constraint ) $1).setParent( (Constraints)$$ ); 
	(( Constraints ) $2).setParent( (Constraints)$$ ); 
}  

Source: source "{"
	srcBlocks
"}"{ 
	tree.saveNode( new Src() ); 
	$$ = tree.getLast(); 
	(( srcBlocks ) $3).setParent( (Src)$$ ); 
}  


srcBlocks : srcBlock{ 
	tree.saveNode( new srcBlocks() ); 
	$$ = tree.getLast(); 
	(( srcBlock ) $1).setParent( (srcBlocks)$$ ); 
}  
srcBlocks : srcBlock srcBlocks{ 
	tree.saveNode( new srcBlocks() ); 
	$$ = tree.getLast();
	(( srcBlock ) $1).setParent( (srcBlocks)$$ );  
	(( srcBlocks ) $2).setParent( (srcBlocks)$$ ); 
}  

srcBlock : Identifier ":" "{" 
	srcExprs
"}"{ 
	tree.saveNode( new srcBlock() ); 
	$$ = tree.getLast();
	(( Identifier ) $1).setParent( (srcBlock)$$ );  
	(( srcExprs ) $4).setParent( (srcBlock)$$ ); 
}  

srcExprs : srcExpr{ 
	tree.saveNode( new srcExprs() ); 
	$$ = tree.getLast(); 
	(( srcExpr ) $1).setParent( (srcExprs)$$ ); 
}  

srcExprs : srcExpr srcExprs{ 
	tree.saveNode( new srcExprs() ); 
	$$ = tree.getLast(); 
	(( srcExpr ) $1).setParent( (srcExprs)$$ ); 
	(( srcExprs ) $2).setParent( (srcExprs)$$ ); 
}  

srcExpr : Identifier "=" Identifier ";"{ 
	tree.saveNode( new srcExpr() ); 
	$$ = tree.getLast(); 
	(( Identifier ) $1).setParent( (srcExpr)$$ ); 
	(( Identifier ) $3).setParent( (srcExpr)$$ ); 
}  
srcExpr : Identifier "=" Set ";"{ 
	tree.saveNode( new srcExpr() ); 
	$$ = tree.getLast(); 
	(( Identifier ) $1).setParent( (srcExpr)$$ ); 
	(( Set ) $3).setParent( (srcExpr)$$ ); 
}  

srcExpr : Identifier "=" Number ";"{ 
	tree.saveNode( new srcExpr() ); 
	$$ = tree.getLast(); 
	(( Identifier ) $1).setParent( (srcExpr)$$ ); 
	(( Number ) $3).setParent( (srcExpr)$$ ); 
}  

srcExpr : Identifier "=" LString ";"{ 
	tree.saveNode( new srcExpr() ); 
	$$ = tree.getLast(); 
	(( Identifier ) $1).setParent( (srcExpr)$$ ); 
	(( LString ) $3).setParent( (srcExpr)$$ ); 
}  

srcExpr : Identifier "=" Bool ";"{ 
	tree.saveNode( new srcExpr() ); 
	$$ = tree.getLast(); 
	(( Identifier ) $1).setParent( (srcExpr)$$ ); 
	(( Bool ) $3).setParent( (srcExpr)$$ ); 
}

Set : "{" Elements "}"{ 
	tree.saveNode( new Set() ); 
	$$ = tree.getLast(); 
	(( Elements ) $2).setParent( (Set)$$ ); 
}  

Elements : Element{ 
	tree.saveNode( new Elements() ); 
	$$ = tree.getLast(); 
	(( Elements ) $1).setParent( (Element)$$ ); 
}  
Elements : Element "," Elements{ 
	tree.saveNode( new Elements() ); 
	$$ = tree.getLast(); 
	(( Element ) $1).setParent( (Elements)$$ ); 
	(( Elements ) $3).setParent( (Elements)$$ ); 
}  

Element : LString{ 
	tree.saveNode( new Element() ); 
	$$ = tree.getLast(); 
	(( LString ) $1).setParent( (Element)$$ ); 
}  
Element : Number{ 
	tree.saveNode( new Element() ); 
	$$ = tree.getLast(); 
	(( Number ) $1).setParent( (Element)$$ ); 
}  

Constraint : Binary ";"{ 
	tree.saveNode( new Constraint() ); 
	$$ = tree.getLast(); 
	(( Binary ) $1).setParent( (Constraint)$$ ); 
}  
Constraint : Condition{ 
	tree.saveNode( new Constraint() ); 
	$$ = tree.getLast(); 
	(( Condition ) $1).setParent( (Constraint)$$ ); 
}  

Binary : not Binary %prec UNARY{ 
	Not not = new Not();
	Binary binary = new Binary();
	tree.saveNode( not );
	tree.saveNode( binary ); 
	
	
	$$ = tree.getLast(); 
	 
	not.setParent(binary);
	(( Binary ) $2).setParent( not ); 
}  

Binary : "(" Binary ")"{ 
	// Лишний узел не нужен
	$$ = $2; 
}  

Binary : BinaryExp{ 
	tree.saveNode( new Binary() ); 
	$$ = tree.getLast(); 
	(( BinaryExp ) $1).setParent( (Binary)$$ ); 
}  

Binary : Binary SetOp BinaryExp { 
	tree.saveNode( new Binary() ); 
	$$ = tree.getLast(); 
	(( Binary ) $1).setParent( (Binary)$$ ); 
	(( SetOp ) $2).setParent( (Binary)$$ );
	(( BinaryExp ) $3).setParent( (Binary)$$ );
}  

Binary : Binary SetOp "(" Binary ")"{ 
	tree.saveNode( new Binary() ); 
	$$ = tree.getLast(); 
	(( Binary ) $1).setParent( (Binary)$$ ); 
	(( SetOp ) $2).setParent( (Binary)$$ );
	(( Binary ) $4).setParent( (Binary)$$ );
}  

BinaryExp : Predicate { 
	tree.saveNode( new BinaryExp() ); 
	$$ = tree.getLast(); 
	(( Predicate ) $1).setParent( (BinaryExp)$$ ); 
}  

BinaryExp : Expression Relation Expression{ 
	tree.saveNode( new BinaryExp() ); 
	$$ = tree.getLast(); 
	(( Expression ) $1).setParent( (BinaryExp)$$ ); 
	(( Relation ) $2).setParent( (BinaryExp)$$ );
	(( Expression ) $3).setParent( (BinaryExp)$$ );
}  

Condition : If Binary "{"
	IfBlocks
"}" { 
	tree.saveNode( new Condition() ); 
	$$ = tree.getLast(); 
	(( Binary ) $2).setParent( (Condition)$$ ); 
	(( IfBlocks ) $4).setParent( (Condition)$$ );
}  

IfBlocks : IfBlock{ 
	tree.saveNode( new IfBlocks() ); 
	$$ = tree.getLast(); 
	(( IfBlock ) $1).setParent( (IfBlocks)$$ ); 
}  

IfBlocks : IfBlock IfBlocks { 
	tree.saveNode( new IfBlocks() ); 
	$$ = tree.getLast(); 
	(( IfBlock ) $1).setParent( (IfBlocks)$$ ); 
	(( IfBlocks ) $2).setParent( (IfBlocks)$$ );
}  

IfBlock : Constraint{ 
	tree.saveNode( new IfBlock() ); 
	$$ = tree.getLast(); 
	(( Constraint ) $1).setParent( (IfBlock)$$ ); 
}  

IfBlock : EqClasses{ 
	tree.saveNode( new IfBlock() ); 
	$$ = tree.getLast(); 
	(( EqClasses ) $1).setParent( (IfBlock)$$ ); 
}  


Expression : AttributeCall { 
	tree.saveNode( new Expression() ); 
	$$ = tree.getLast(); 
	(( AttributeCall ) $1).setParent( (Expression)$$ ); 
}  

Expression : ArithmeticExpression{ 
	tree.saveNode( new Expression() ); 
	$$ = tree.getLast(); 
	(( ArithmeticExpression ) $1).setParent( (Expression)$$ ); 
}  

Expression : Variable{ 
	tree.saveNode( new Expression() ); 
	$$ = tree.getLast(); 
	(( Variable ) $1).setParent( (Expression)$$ ); 
}  

Expression : Literal{ 
	tree.saveNode( new Expression() ); 
	$$ = tree.getLast(); 
	(( Literal ) $1).setParent( (Expression)$$ ); 
}  

Literal : LString { 
	tree.saveNode( new Literal() ); 
	$$ = tree.getLast(); 
	(( LString ) $1).setParent( (Literal)$$ ); 
}  

Literal : Number{ 
	tree.saveNode( new Literal() ); 
	$$ = tree.getLast(); 
	(( Number ) $1).setParent( (Literal)$$ ); 
}  

Literal : Bool{ 
	tree.saveNode( new Literal() ); 
	$$ = tree.getLast(); 
	(( Bool ) $1).setParent( (Literal)$$ ); 
}  

AttributeCall : AttributeCall "." Identifier{ 
	tree.saveNode( new AttributeCall() ); 
	$$ = tree.getLast(); 
	(( AttributeCall ) $1).setParent( (AttributeCall)$$ ); 
	(( Identifier ) $3).setParent( (AttributeCall)$$ );
}  

AttributeCall : Variable "." Identifier{ 
	tree.saveNode( new AttributeCall() ); 
	$$ = tree.getLast(); 
	(( Variable ) $1).setParent( (AttributeCall)$$ ); 
	(( Identifier ) $3).setParent( (AttributeCall)$$ );
}  


Variable : Identifier{ 
	tree.saveNode( new Variable() ); 
	$$ = tree.getLast(); 
	(( Identifier ) $1).setParent( (Variable)$$ ); 
}  

Predicate : OperationCall { 
	tree.saveNode( new Predicate() ); 
	$$ = tree.getLast(); 
	(( OperationCall ) $1).setParent( (Predicate)$$ ); 
}  

Predicate : Variable "." OperationCall{ 
	tree.saveNode( new Predicate() ); 
	$$ = tree.getLast(); 
	(( Variable ) $1).setParent( (Predicate)$$ ); 
	(( OperationCall ) $3).setParent( (Predicate)$$ ); 
}  

Predicate : AttributeCall "." OperationCall{ 
	tree.saveNode( new Predicate() ); 
	$$ = tree.getLast(); 
	(( AttributeCall ) $1).setParent( (Predicate)$$ ); 
	(( OperationCall ) $3).setParent( (Predicate)$$ ); 
}  

OperationCall : Identifier "(" Parametres ")" { 
	tree.saveNode( new OperationCall() ); 
	$$ = tree.getLast(); 
	(( Identifier ) $1).setParent( (OperationCall)$$ ); 
	(( Parametres ) $3).setParent( (OperationCall)$$ ); 
}  

Parametres : { 
	tree.saveNode( new Parametres() ); 
	$$ = tree.getLast(); 
}  




PredicateImpl : PathName FunctionalPart "(" FormalParams ")"  "{"
	Constraints 
"}"{  
	tree.saveNode( new PredicateImpl() ); 
	$$ = tree.getLast(); 
	(( PathName ) $1).setParent( (PredicateImpl)$$ ); 
	(( FunctionalPart ) $2).setParent( (PredicateImpl)$$ ); 
	(( FormalParams ) $4).setParent( (PredicateImpl)$$ ); 
	(( Constraints ) $7).setParent( (PredicateImpl)$$ ); 
}  

FunctionalPart : { 
	tree.saveNode( new FunctionalPart() ); 
	$$ = tree.getLast(); 
}  
FunctionalPart : "<" LString "," Number ">"{ 
	tree.saveNode( new FunctionalPart() ); 
	$$ = tree.getLast(); 
	(( LString ) $2).setParent( (FunctionalPart)$$ ); 
	(( Number ) $4).setParent( (FunctionalPart)$$ ); 
}  

FormalParams : { 
	tree.saveNode( new FormalParams() ); 
	$$ = tree.getLast(); 
}  

Identifier : identifier{ 
	tree.saveNode( new Identifier($1) ); 
	$$ = tree.getLast(); 
}  

Type : identifier{ 
	tree.saveNode( new Type($1) ); 
	$$ = tree.getLast(); 
}  

SimpleName : identifier { 
	tree.saveNode( new SimpleName($1) ); 
	$$ = tree.getLast(); 
}  

LString : string{ 
	tree.saveNode( new LString($1) ); 
	$$ = tree.getLast(); 
}  

Number : number { 
	tree.saveNode( new Number($1) ); 
	$$ = tree.getLast(); 
}  

Bool : ldltrue { 
	tree.saveNode( new Bool(true) ); 
	$$ = tree.getLast(); 
}  

Bool : ldlfalse{ 
	tree.saveNode( new Bool(false) ); 
	$$ = tree.getLast(); 
}  

SetOp : and { 
	tree.saveNode( new SetOp(Logical.and) ); 
	$$ = tree.getLast(); 
}  

SetOp : or { 
	tree.saveNode( new SetOp(Logical.or) ); 
	$$ = tree.getLast(); 
}  

SetOp : xor { 
	tree.saveNode( new SetOp(Logical.xor) ); 
	$$ = tree.getLast(); 
}  

Relation : "<" { 
	tree.saveNode( new Relation(Ratio.less) ); 
	$$ = tree.getLast(); 
}  

Relation : ">" { 
	tree.saveNode( new Relation(Ratio.more) ); 
	$$ = tree.getLast(); 
}  

Relation : "=" { 
	tree.saveNode( new Relation(Ratio.equal) ); 
	$$ = tree.getLast(); 
}  

Relation : notEqual { 
	tree.saveNode( new Relation(Ratio.notEqual) ); 
	$$ = tree.getLast(); 
}  

Relation : lessEqual { 
	tree.saveNode( new Relation(Ratio.lessEqual) ); 
	$$ = tree.getLast(); 
}  

Relation : moreEqual{ 
	tree.saveNode( new Relation(Ratio.moreEqual) );  
	$$ = tree.getLast(); 
}  

%%

private Lexer lexer;
private Source src;
private ErrorHandler errHandler;
private ParseTree tree;
  
{
	tree = new ParseTree();
}
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

