package be.uantwerpen.adrem.fim.measure.assoc.asymmetric;

import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_NOT_A_GIVEN_NOT_B;
import be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

/**
 * @author Sandy Moens
 */
public class SpecificityMeasure extends RuleMeasureBase {

	public SpecificityMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Specificity";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		return P_NOT_A_GIVEN_NOT_B(rhs, lhs, dbSize);
	}
}
