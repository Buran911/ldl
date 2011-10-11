package generation.walkers.walkers;

import java.util.Iterator;

import generation.idtable.Database;
import generation.idtable.IdTable;
import generation.idtable.Identifier;
import generation.idtable.Predicate;
import generation.languageconstants.ReservedWord;
import generation.languageconstants.Type;
import generation.walkers.TreeWalker;
import generation.walkers.WalkerStrategy;
import parse.syntaxtree.nodes.AttributeCallAST;
import parse.syntaxtree.nodes.BinaryExpressionAST;
import parse.syntaxtree.nodes.BinaryOpAST;
import parse.syntaxtree.nodes.BooleanAST;
import parse.syntaxtree.nodes.ConditionAST;
import parse.syntaxtree.nodes.ContextAST;
import parse.syntaxtree.nodes.DescriptionAST;
import parse.syntaxtree.nodes.EqClassAST;
import parse.syntaxtree.nodes.FormalParamsAST;
import parse.syntaxtree.nodes.FunctionalPartAST;
import parse.syntaxtree.nodes.IdentifierAST;
import parse.syntaxtree.nodes.NumberAST;
import parse.syntaxtree.nodes.OperationCallAST;
import parse.syntaxtree.nodes.ParametresAST;
import parse.syntaxtree.nodes.PathNameAST;
import parse.syntaxtree.nodes.PredicateAST;
import parse.syntaxtree.nodes.PredicateImplAST;
import parse.syntaxtree.nodes.RelationAST;
import parse.syntaxtree.nodes.SetAST;
import parse.syntaxtree.nodes.SetOpAST;
import parse.syntaxtree.nodes.SimpleNameAST;
import parse.syntaxtree.nodes.SourceAST;
import parse.syntaxtree.nodes.StringAST;
import parse.syntaxtree.nodes.TypeAST;
import parse.syntaxtree.nodes.VariableAST;
import parse.syntaxtree.nodes.ldlAST;
import parse.syntaxtree.nodes.srcBlockAST;
import parse.syntaxtree.nodes.srcExprAST;

public class IdTableFiller extends TreeWalker {
	private IdTable table;
	private String contextName;
	
	public IdTableFiller(WalkerStrategy strategy, IdTable table) {
		super(strategy);
		this.table = table;
	}

	@Override
	public void visit(AttributeCallAST attrCall) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BinaryExpressionAST binaryExp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BinaryOpAST binaryOp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BooleanAST bool) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ConditionAST condition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ContextAST context) {
		contextName = context.getContextName().getName();
	}

	@Override
	public void visit(DescriptionAST description) {
		Identifier id = new Identifier();
		
		id.setNamespace(contextName);
		id.setName(description.getIdentifier().getId());
		id.setType(description.getType().getType());
		
		table.addId(id, description);
	}
	
	@Override
	public void visit(EqClassAST eqClass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FormalParamsAST formalParams) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FunctionalPartAST funcPart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IdentifierAST id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ldlAST ldl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NumberAST number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OperationCallAST operationCall) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ParametresAST params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(PathNameAST pathName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(PredicateAST predicate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(PredicateImplAST impl) {
		Predicate predicate = new Predicate();
		
		predicate.setGroup(impl.getFuncPart().getGroup().getString());
		predicate.setPriority(impl.getFuncPart().getPriority().getNumber());
		predicate.setName(impl.getPathName().getPredicateName().getName());
		predicate.setNamespace(impl.getPathName().getContextName().getName());
		predicate.setImpl(impl);
		
		table.addPredicate(predicate, impl);
	}

	@Override
	public void visit(RelationAST relation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SetAST set) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SetOpAST setOp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SimpleNameAST simpleName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SourceAST src) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(srcBlockAST block) {
		Identifier id = table.getId(block.getIdentifier().getId(), contextName);
		
		// определяем источник
		Iterator<srcExprAST> itExpr = block.getSrcExprs().iterator();
		srcExprAST expr = null;
		while(itExpr.hasNext() && !( (expr = itExpr.next()).getFirstId().getId().contentEquals(ReservedWord.type.word() )));
		
		if(expr.getFirstId().getId().contentEquals(ReservedWord.type.word() )){
			id.setSrcType(Type.valueOf(expr.getSecondId().getId()));
		}
		
		// заполняем данные об источнике
		switch(id.getSrcType()){
			case db :
				Database db = new Database();
				
				for(srcExprAST exp : block.getSrcExprs()){
					if( exp.getFirstId().getId().contentEquals(ReservedWord.table.word() )){
						db.setTable(((StringAST) exp.getLiteral()).getString() );
					}
					
					if( exp.getFirstId().getId().contentEquals(ReservedWord.column.word() )){
						db.setColumn(((StringAST) exp.getLiteral()).getString() );
					}
				}
				
				id.setSrcData(db);
				
				break;
		}
		
	}

	@Override
	public void visit(srcExprAST expr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StringAST string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TypeAST type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(VariableAST var) {
		// TODO Auto-generated method stub
		
	}

}
