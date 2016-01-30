package be.uantwerpen.adrem.fim.measure.assoc.asymmetric;

import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_A_GIVEN_B;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_A_GIVEN_NOT_B;
import be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

/**
 * @author Sandy Moens
 */
public class RelativeRiskMeasure extends RuleMeasureBase {

	public RelativeRiskMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Relative Risk";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		return P_A_GIVEN_B(rhs, lhs, dbSize)
				/ P_A_GIVEN_NOT_B(rhs, lhs, dbSize);
	}
}
