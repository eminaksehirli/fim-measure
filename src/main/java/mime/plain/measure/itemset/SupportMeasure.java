package mime.plain.measure.itemset;

import java.util.BitSet;
import java.util.HashMap;

import be.uantwerpen.adrem.fim.base.PlainItem;
import be.uantwerpen.adrem.fim.base.PlainItemSet;

/**
 * This measure computes the size of the database covered by an itemset
 * 
 * Sup(X) = P(X)
 * 
 * @author Sandy Moens & Emin Aksehirli
 */
public class SupportMeasure implements ItemSetMeasure {

	private final HashMap<PlainItemSet, BitSet> cache = new HashMap<PlainItemSet, BitSet>();

	@Override
	public String getName() {
		return "Support";
	}

	@Override
	public double evaluate(PlainItemSet itemSet) {
		return itemSet.getTIDs().cardinality();
	}

	@Override
	public double evaluate(PlainItemSet itemSet, PlainItem extension) {
		BitSet bs = getTids(itemSet);
		bs.and(extension.getTIDs());
		return bs.cardinality();
	}

	private BitSet getTids(PlainItemSet itemSet) {
		BitSet bs = cache.get(itemSet);
		if (bs == null) {
			bs = itemSet.getTIDs();
			cache.put(itemSet, bs);
		}
		return (BitSet) bs.clone();
	}
}
