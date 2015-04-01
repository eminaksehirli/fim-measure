package mime.plain.measure.itemset;

import java.util.BitSet;

import be.uantwerpen.adrem.fim.base.PlainItem;
import be.uantwerpen.adrem.fim.base.PlainItemSet;

public class InverseNegativeSupportMeasure implements ItemSetMeasure {

	private final double size;

	public InverseNegativeSupportMeasure(int size) {
		this.size = size;
	}

	@Override
	public String getName() {
		return "Inverse Negative Support";
	}

	private BitSet getDisjunctTids(PlainItemSet itemSet) {
		BitSet tids = new BitSet();
		for (PlainItem item : itemSet) {
			tids.or(item.getTIDs());
		}
		return tids;
	}

	@Override
	public double evaluate(PlainItemSet itemSet) {
		return 1 / (size - getDisjunctTids(itemSet).cardinality());
	}

	@Override
	public double evaluate(PlainItemSet itemSet, PlainItem extension) {
		PlainItemSet i = new PlainItemSet(itemSet);
		i.add(extension);
		return evaluate(i);
	}
}
