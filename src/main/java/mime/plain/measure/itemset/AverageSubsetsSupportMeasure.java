package mime.plain.measure.itemset;

import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import be.uantwerpen.adrem.fim.model.Item;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.Transaction;
import be.uantwerpen.adrem.fim.model.TransactionDB;

public class AverageSubsetsSupportMeasure extends ItemSetMeasureBase {

	private boolean excludeSingletons = false;
	private final List<Transaction> transactions;

	public AverageSubsetsSupportMeasure(TransactionDB pdb) {
		super(pdb);
		this.transactions = pdb.getTransactions();
	}

	@Override
	public String getName() {
		return "Average Subsets Support Measure";
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
		BitSet tids = getDisjunctTids(itemSet);
		Iterator<Transaction> it = transactions.iterator();
		int next = -1;
		int curr = 0;

		double score = 0;

		while (it.hasNext()) {
			next = tids.nextSetBit(next + 1);
			if (next == -1) {
				break;
			}
			Transaction t = it.next();
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

	private int getSizeUnion(Transaction t, Itemset itemSet) {
		Collection<Item> tSet = t.asCollection();
		int size = 0;
		for (Item item : itemSet) {
			size += tSet.contains(item) ? 1 : 0;
		}
		return size;
	}

	public void setExcludeSingletons(boolean excludeSingletons) {
		this.excludeSingletons = excludeSingletons;
	}

	@Override
	public double evaluate(Itemset itemSet, Item extension) {
		Itemset i = new Itemset(itemSet);
		i.add(extension);
		return evaluate(i);
	}
}
