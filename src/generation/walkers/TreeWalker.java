package generation.walkers;

import parse.syntaxtree.nodes.*;


public abstract class TreeWalker {
	private WalkerStrategy strategy;
	
	public TreeWalker(WalkerStrategy strategy) {
		this.strategy = strategy;
	}

	public abstract void visit( AttributeCallAST attrCall );
	
	public abstract void visit( BinaryExpressionAST binaryExp );
	
	public abstract void visit( BinaryOpAST binaryOp );
	
	public abstract void visit( BooleanAST bool );
	
	public abstract void visit( ConditionAST condition );
	
	public abstract void visit( ContextAST context );
	
	public abstract void visit( DescriptionAST description );
	
	public abstract void visit( EqClassAST eqClass );
	
	public abstract void visit( FormalParamsAST formalParams );
	
	public abstract void visit( FunctionalPartAST funcPart );
	
	public abstract void visit( IdentifierAST id );
	
	public abstract void visit( ldlAST ldl);
	
	public abstract void visit( NumberAST number );
	
	public abstract void visit( OperationCallAST operationCall );
	
	public abstract void visit( ParametresAST params );
	
	public abstract void visit( PathNameAST pathName );
	
	public abstract void visit( PredicateAST predicate );
	
	public abstract void visit( PredicateImplAST impl );
	
	public abstract void visit( RelationAST relation );
	
	public abstract void visit( SetAST set );
	
	public abstract void visit( SetOpAST setOp );
	
	public abstract void visit( SimpleNameAST simpleName );
	
	public abstract void visit( SourceAST src );
	
	public abstract void visit( srcBlockAST block );
	
	public abstract void visit( srcExprAST expr );
	
	public abstract void visit( StringAST string );
	
	public abstract void visit( TypeAST type );
	
	public abstract void visit( VariableAST var );
	
	
	
	
	public  void accept( AttributeCallAST attrCall ){
		strategy.accept(this, attrCall);
	}
	
	public  void accept( BinaryExpressionAST binaryExp ){
		strategy.accept(this, binaryExp);
	}
	
	public  void accept( BinaryOpAST binaryOp ){
		strategy.accept(this, binaryOp);
	}
	
	public  void accept( BooleanAST bool ){
		strategy.accept(this, bool);
	}
	
	public  void accept( ConditionAST condition ){
		strategy.accept(this, condition);
	}
	
	public  void accept( ContextAST context ){
		strategy.accept(this, context);
	}
	
	public  void accept( DescriptionAST description ){
		strategy.accept(this, description);
	}
	
	public void accept(EqClassAST eqClass) {
		strategy.accept(this, eqClass);
	}
	
	public  void accept( FormalParamsAST formalParams ){
		strategy.accept(this, formalParams);
	}
	
	public  void accept( FunctionalPartAST funcPart ){
		strategy.accept(this, funcPart);
	}
	
	public  void accept( IdentifierAST id ){
		strategy.accept(this, id);
	}
	
	public  void accept( ldlAST ldl){
		strategy.accept(this, ldl);
	}
	
	public  void accept( NumberAST number ){
		strategy.accept(this, number);
	}
	
	public  void accept( OperationCallAST operationCall ){
		strategy.accept(this, operationCall);
	}
	
	public  void accept( ParametresAST params ){
		strategy.accept(this, params);
	}
	
	public  void accept( PathNameAST pathName ){
		strategy.accept(this, pathName);
	}
	
	public  void accept( PredicateAST predicate ){
		strategy.accept(this, predicate);
	}
	
	public  void accept( PredicateImplAST impl ){
		strategy.accept(this, impl);
	}
	
	public  void accept( RelationAST relation ){
		strategy.accept(this, relation);
	}
	
	public  void accept( SetAST set ){
		strategy.accept(this, set);
	}
	
	public  void accept( SetOpAST setOp ){
		strategy.accept(this, setOp);
	}
	
	public  void accept( SimpleNameAST simpleName ){
		strategy.accept(this, simpleName);
	}
	
	public  void accept( SourceAST src ){
		strategy.accept(this, src);
	}
	
	public  void accept( srcBlockAST block ){
		strategy.accept(this, block);
	}
	
	public  void accept( srcExprAST expr ){
		strategy.accept(this, expr);
	}
	
	public  void accept( StringAST string ){
		strategy.accept(this, string);
	}
	
	public  void accept( TypeAST type ){
		strategy.accept(this, type);
	}
	
	public  void accept( VariableAST var ){
		strategy.accept(this, var);
	}

}
