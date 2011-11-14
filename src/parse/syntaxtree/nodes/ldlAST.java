package parse.syntaxtree.nodes;

/**
 * Класс содержит ссылки на основные конструкции языка: контексты и описания предикатов.
 * */
import generation.walkers.TreeWalker;

import java.util.LinkedList;
import java.util.List;

import parse.syntaxtree.NodeAST;

public class ldlAST extends NodeAST implements Cloneable{
    private List<ContextAST> contexts;
    private List<PredicateImplAST> impls;

    {
	contexts = new LinkedList<ContextAST>();
	impls = new LinkedList<PredicateImplAST>();
    }

    public void addContext(ContextAST context) {
	contexts.add(context);
	addSuccessor(context);
    }

    public void addPredicateImpl(PredicateImplAST impl) {
	impls.add(impl);
	addSuccessor(impl);
    }

    public List<ContextAST> getContexts() {
	return contexts;
    }

    public List<PredicateImplAST> getImpls() {
	return impls;
    }

    @Override
    public void accept(TreeWalker walker) {
	walker.accept(this);

    }

    public ldlAST clone(){
	   ldlAST copy = new ldlAST();
	   for(ContextAST context : contexts){
	       copy.addContext(context.clone());
	   }
	   for(PredicateImplAST predicateImpl : impls){
	       copy.addPredicateImpl(predicateImpl.clone());
	   }
	   return copy;
    }
}
