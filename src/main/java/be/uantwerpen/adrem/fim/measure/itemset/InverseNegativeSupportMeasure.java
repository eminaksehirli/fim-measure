package be.uantwerpen.adrem.fim.measure.itemset;

import java.util.BitSet;

import be.uantwerpen.adrem.fim.model.Item;
import be.uantwerpen.adrem.fim.model.Itemset;

/**
 * @author Sandy Moens
 */
public class InverseNegativeSupportMeasure implements ItemSetMeasure {

	private final double size;

	public InverseNegativeSupportMeasure(int size) {
		this.size = size;
	}

	@Override
	public String getName() {
		return "Inverse Negative Support";
	}

	private BitSet getDisjunctTids(Itemset itemSet) {
		BitSet tids = new BitSet();
		for (Item item : itemSet) {
			tids.or(item.getTIDs());
		}
		return tids;
	}

	@Override
	public double evaluate(Itemset itemSet) {
		return 1 / (size - getDisjunctTids(itemSet).cardinality());
	}

	@Override
	public double evaluate(Itemset itemSet, Item extension) {
		Itemset i = new Itemset(itemSet);
		i.add(extension);
		return evaluate(i);
	}
}
