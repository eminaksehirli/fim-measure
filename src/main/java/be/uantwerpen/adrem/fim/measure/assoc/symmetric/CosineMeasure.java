package be.uantwerpen.adrem.fim.measure.assoc.symmetric;

import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_A;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_AB;
import be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

/**
 * @author Sandy Moens
 */
public class CosineMeasure extends RuleMeasureBase {

	public CosineMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Cosine";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		return P_AB(lhs, rhs, dbSize)
				/ Math.sqrt((P_A(lhs, dbSize) * P_A(rhs, dbSize)));
	}
}
