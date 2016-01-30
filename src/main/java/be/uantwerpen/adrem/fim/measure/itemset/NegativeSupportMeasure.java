package be.uantwerpen.adrem.fim.measure.itemset;

import be.uantwerpen.adrem.fim.model.Item;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

/**
 * This class computes the size of the database not covered by an itemset
 * 
 * NegSup(X) = 1 - P(X)
 * 
 * @author Sandy Moens
 * 
 */
public class NegativeSupportMeasure extends ItemSetMeasureBase {

	public NegativeSupportMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Negative Support";
	}

	@Override
	public double evaluate(Itemset itemSet) {
		return dbSize - itemSet.getTIDs().cardinality();
	}

	@Override
	public double evaluate(Itemset itemSet, Item extension) {
		Itemset i = new Itemset(itemSet);
		i.add(extension);
		return evaluate(i);
	}
}
