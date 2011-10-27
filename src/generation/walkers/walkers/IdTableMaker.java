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

public class IdTableMaker extends TreeWalker {
	private IdTable table;
	private String contextName;
	
	public IdTableMaker(WalkerStrategy strategy, IdTable table) {
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
		// ������������ ��������� ��� ��������� ����������
		for(Identifier id : table.getIds()){
			if(id.getSrcData() == null){
				id.setSrcType(Type.own);
			}
		}
		
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
		
		if(impl.getFuncPart().getGroup() != null){
			predicate.setGroup(impl.getFuncPart().getGroup().getString());
		}
		if(impl.getFuncPart().getPriority() != null){
			predicate.setPriority(impl.getFuncPart().getPriority().getNumber());
		}
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
