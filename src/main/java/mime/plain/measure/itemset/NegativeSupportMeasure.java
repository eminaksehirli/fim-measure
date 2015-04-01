package mime.plain.measure.itemset;

import be.uantwerpen.adrem.fim.base.PlainItem;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

/**
 * This class computes the size of the database not covered by an itemset
 * 
 * NegSup(X) = 1 - P(X)
 * 
 * @author Sandy Moens
 * 
 */
public class NegativeSupportMeasure extends ItemSetMeasureBase {

	public NegativeSupportMeasure(PlainTransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Negative Support";
	}

	@Override
	public double evaluate(PlainItemSet itemSet) {
		return dbSize - itemSet.getTIDs().cardinality();
	}

	@Override
	public double evaluate(PlainItemSet itemSet, PlainItem extension) {
		PlainItemSet i = new PlainItemSet(itemSet);
		i.add(extension);
		return evaluate(i);
	}
}
