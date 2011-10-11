package generation.walkers.strategys;

import generation.walkers.TreeWalker;
import parse.syntaxtree.nodes.ConstraintAST;
import parse.syntaxtree.nodes.ContextAST;
import parse.syntaxtree.nodes.DescriptionAST;
import parse.syntaxtree.nodes.EqClassAST;
import parse.syntaxtree.nodes.SourceAST;

public class IdParsigStrategy extends BottomUpWalkingStrategy {
	
	@Override
	public void accept(TreeWalker walker, ContextAST context) {
		context.getContextName().accept(walker);
		
		// Сначала узнаем имя контекста
		walker.visit(context);
		
		for(DescriptionAST desc : context.getDescriptions()){
			desc.accept(walker);
		}
		
		for(SourceAST src : context.getSources()){
			src.accept(walker);
		}
		
		for(ConstraintAST constraint : context.getConstraints()){
			constraint.accept(walker);
		}
		
		for(EqClassAST eqClass : context.getEqClasseses()){
			eqClass.accept(walker);
		}
		
		
	}
	
}
