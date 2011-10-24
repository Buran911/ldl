package generation.walkers.walkers;

import generation.idtable.IdTable;
import generation.idtable.Identifier;
import generation.idtable.Predicate;
import generation.walkers.TreeWalker;
import generation.walkers.WalkerStrategy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.nodes.*;

public class IdConvertor extends TreeWalker {
	private IdTable table;
	private String contextName;
	private boolean attrCallVisited; // определ€ет, как будут обрабатыватьс€ вызовы атрибутов
	private boolean variableCanBeVisited;
	
	{
		attrCallVisited = false;
		variableCanBeVisited = true;
	}
	
	public IdConvertor(WalkerStrategy strategy, IdTable table) {
		super(strategy);
		this.table = table;
	}

	@Override
	public void visit(AttributeCallAST attrCall) {
		if(!attrCallVisited){
			List<String> attrChain = new LinkedList<String>();
			AttributeCallAST attrFollower = attrCall;
			while (attrFollower.getAttrCall() != null){
				attrChain.add( attrFollower.getIdentifier().getData());
				attrFollower = attrFollower.getAttrCall();
			}
			
			attrChain.add(attrFollower.getIdentifier().getData());
			attrChain.add(attrFollower.getVariable().getIdentifier().getData());
			
			Collections.reverse(attrChain);
			
			String curContext = contextName;
			Identifier id = null;
			for(String idName : attrChain){
				id = table.getId(idName, curContext);
				curContext = id.getType();
			}
			
			// ѕри обработке шаблнизатором первым будет обработан последний элемент
			attrFollower.setId(id);
			
			attrCallVisited = true;
			variableCanBeVisited = false;
		}
		
		if(attrCall.getAttrCall() == null){
			attrCallVisited = false;
		}
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
		// TODO Auto-generated method stub

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
		String predicateName = predicate.getOprCall().getIdentifier().getData();
		
		if(predicate.getAttrCall() != null){
			Predicate pred = table.getPredicate( predicateName, predicate.getAttrCall().getId().getType());
			predicate.setImpl((PredicateImplAST) pred.getImpl());
		}
		
		if(predicate.getVariable() != null){
			Predicate pred = table.getPredicate( predicateName, predicate.getVariable().getId().getType());
			predicate.setImpl((PredicateImplAST) pred.getImpl());
		}

		if((predicate.getAttrCall() == null) 
				&&(predicate.getVariable() == null)) {
			Predicate pred = table.getPredicate( predicateName, contextName);
			predicate.setImpl((PredicateImplAST) pred.getImpl());
		}
	}

	@Override
	public void visit(PredicateImplAST impl) {
		contextName = impl.getPathName().getContextName().getData();

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
		// TODO Auto-generated method stub

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
		if(variableCanBeVisited){
			String idName = var.getIdentifier().getData();
			
			Identifier id = table.getId(idName, contextName); 
			var.setId(id);
		} 
		else{
			// attrCall закончен
			variableCanBeVisited = true;
		}
	}

}
