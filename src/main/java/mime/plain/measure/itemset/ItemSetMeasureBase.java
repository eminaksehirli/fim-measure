package mime.plain.measure.itemset;

import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

/**
 * This class contains basic information for all itemset measures
 * 
 * @author Sandy Moens & Emin Aksehirli
 */
public abstract class ItemSetMeasureBase implements ItemSetMeasure {

	protected int dbSize;

	public ItemSetMeasureBase(PlainTransactionDB db) {
		dbSize = db.getNumberOfTransactions();
	}
}