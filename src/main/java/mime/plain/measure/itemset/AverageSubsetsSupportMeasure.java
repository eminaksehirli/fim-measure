package mime.plain.measure.itemset;

import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import be.uantwerpen.adrem.fim.base.PlainItem;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransaction;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

public class AverageSubsetsSupportMeasure extends ItemSetMeasureBase {

	private boolean excludeSingletons = false;
	private final List<PlainTransaction> transactions;

	public AverageSubsetsSupportMeasure(PlainTransactionDB pdb) {
		super(pdb);
		this.transactions = pdb.getTransactions();
	}

	@Override
	public String getName() {
		return "Average Subsets Support Measure";
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
		BitSet tids = getDisjunctTids(itemSet);
		Iterator<PlainTransaction> it = transactions.iterator();
		int next = -1;
		int curr = 0;

		double score = 0;

		while (it.hasNext()) {
			next = tids.nextSetBit(next + 1);
			if (next == -1) {
				break;
			}
			PlainTransaction t = it.next();
			for (int i = curr + 1; i <= next; i++) {
				t = it.next();
			}

			int sizeUnion = getSizeUnion(t, itemSet);
			if (excludeSingletons && sizeUnion == 1 && itemSet.size() != 1) {
			} else {
				if (excludeSingletons) {
					score += (Math.pow(2, getSizeUnion(t, itemSet)) - 1 - sizeUnion);
				} else {
					score += (Math.pow(2, getSizeUnion(t, itemSet)) - 1);
				}
			}
			curr = next + 1;
		}
		if (itemSet.size() == 1) {
			return score / (Math.pow(2, itemSet.size()) - 1);
		} else if (excludeSingletons) {
			return score
					/ (Math.pow(2, itemSet.size())
							- Math.pow(itemSet.size(), 1) - 1);
		}
		return score / (Math.pow(2, itemSet.size()) - 1);
	}

	private int getSizeUnion(PlainTransaction t, PlainItemSet itemSet) {
		Collection<PlainItem> tSet = t.asCollection();
		int size = 0;
		for (PlainItem item : itemSet) {
			size += tSet.contains(item) ? 1 : 0;
		}
		return size;
	}

	public void setExcludeSingletons(boolean excludeSingletons) {
		this.excludeSingletons = excludeSingletons;
	}

	@Override
	public double evaluate(PlainItemSet itemSet, PlainItem extension) {
		PlainItemSet i = new PlainItemSet(itemSet);
		i.add(extension);
		return evaluate(i);
	}
}
