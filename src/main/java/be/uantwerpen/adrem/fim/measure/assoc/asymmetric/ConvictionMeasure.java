package be.uantwerpen.adrem.fim.measure.assoc.asymmetric;

import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_A;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_NOT_A;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_NOT_A_B;
import be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

/**
 * @author Sandy Moens
 */
public class ConvictionMeasure extends RuleMeasureBase {
	
	public ConvictionMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Conviction";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		return P_A(lhs, dbSize) * P_NOT_A(rhs, dbSize)
				/ P_NOT_A_B(rhs, lhs, dbSize);
	}
}
