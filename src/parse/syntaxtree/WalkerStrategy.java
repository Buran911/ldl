package parse.syntaxtree;

import parse.syntaxtree.nodes.*;

public abstract class WalkerStrategy {
	public abstract void accept( TreeWalker walker, AttributeCallAST attrCall );
	
	public abstract void accept( TreeWalker walker, BinaryExpressionAST binaryExp );
	
	public abstract void accept( TreeWalker walker, BinaryOpAST binaryOp );
	
	public abstract void accept( TreeWalker walker, BooleanAST bool );
	
	public abstract void accept( TreeWalker walker, ConditionAST condition );
	
	public abstract void accept( TreeWalker walker, ContextAST context );
	
	public abstract void accept( TreeWalker walker, DescriptionAST description );
	
	public abstract void accept( TreeWalker walker, FormalParamsAST formalParams );
	
	public abstract void accept( TreeWalker walker, FunctionalPartAST funcPart );
	
	public abstract void accept( TreeWalker walker, IdentifierAST id );
	
	public abstract void accept( TreeWalker walker, ldlAST ldl);
	
	public abstract void accept( TreeWalker walker, NumberAST number );
	
	public abstract void accept( TreeWalker walker, OperationCallAST operationCall );
	
	public abstract void accept( TreeWalker walker, ParametresAST params );
	
	public abstract void accept( TreeWalker walker, PathNameAST pathName );
	
	public abstract void accept( TreeWalker walker, PredicateAST predicate );
	
	public abstract void accept( TreeWalker walker, PredicateImplAST impl );
	
	public abstract void accept( TreeWalker walker, RelationAST relation );
	
	public abstract void accept( TreeWalker walker, SetAST set );
	
	public abstract void accept( TreeWalker walker, SetOpAST setOp );
	
	public abstract void accept( TreeWalker walker, SimpleNameAST simpleName );
	
	public abstract void accept( TreeWalker walker, SourceAST src );
	
	public abstract void accept( TreeWalker walker, srcBlockAST block );
	
	public abstract void accept( TreeWalker walker, srcExprAST expr );
	
	public abstract void accept( TreeWalker walker, StringAST string );
	
	public abstract void accept( TreeWalker walker, TypeAST type );
	
	public abstract void accept( TreeWalker walker, VariableAST var );
}
