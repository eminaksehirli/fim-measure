package mime.plain.measure.assoc;

import mime.plain.measure.itemset.ItemSetMeasure;
import be.uantwerpen.adrem.fim.model.Item;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

/**
 * This class contains basic information for association rule measures
 * 
 * @author Sandy Moens & Emin Aksehirli
 */
public abstract class RuleMeasureBase implements ItemSetMeasure, RuleMeasure {

	private static Itemset empty = new Itemset();

	protected int dbSize;

	public RuleMeasureBase(TransactionDB db) {
		dbSize = db.getNumberOfTransactions();
	}

	/**
	 * Evaluates an itemset as a rule by using an empty left hand side and using
	 * the itemset as right hide side for the rule
	 */
	@Override
	public double evaluate(Itemset itemSet) {
		return evaluate(empty, itemSet);
	}

	@Override
	public double evaluate(Itemset itemSet, Item extension) {
		Itemset i = new Itemset(itemSet);
		i.add(extension);
		return evaluate(i);
	}
}
