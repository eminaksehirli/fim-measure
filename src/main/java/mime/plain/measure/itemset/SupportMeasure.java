package mime.plain.measure.itemset;

import java.util.BitSet;
import java.util.HashMap;

import be.uantwerpen.adrem.fim.model.Item;
import be.uantwerpen.adrem.fim.model.Itemset;

/**
 * This measure computes the size of the database covered by an itemset
 * 
 * Sup(X) = P(X)
 * 
 * @author Sandy Moens & Emin Aksehirli
 */
public class SupportMeasure implements ItemSetMeasure {

	private final HashMap<Itemset, BitSet> cache = new HashMap<Itemset, BitSet>();

	@Override
	public String getName() {
		return "Support";
	}

	@Override
	public double evaluate(Itemset itemSet) {
		return itemSet.getTIDs().cardinality();
	}

	@Override
	public double evaluate(Itemset itemSet, Item extension) {
		BitSet bs = getTids(itemSet);
		bs.and(extension.getTIDs());
		return bs.cardinality();
	}

	private BitSet getTids(Itemset itemSet) {
		BitSet bs = cache.get(itemSet);
		if (bs == null) {
			bs = itemSet.getTIDs();
			cache.put(itemSet, bs);
		}
		return (BitSet) bs.clone();
	}
}
