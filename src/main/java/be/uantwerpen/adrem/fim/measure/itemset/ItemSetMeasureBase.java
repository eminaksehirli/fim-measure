package be.uantwerpen.adrem.fim.measure.itemset;

import be.uantwerpen.adrem.fim.model.TransactionDB;

/**
 * This class contains basic information for all itemset measures
 * 
 * @author Sandy Moens
 * @author Emin Aksehirli
 */
public abstract class ItemSetMeasureBase implements ItemSetMeasure {

	protected int dbSize;

	public ItemSetMeasureBase(TransactionDB db) {
		dbSize = db.getNumberOfTransactions();
	}
}