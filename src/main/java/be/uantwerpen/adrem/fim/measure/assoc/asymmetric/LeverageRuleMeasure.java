package be.uantwerpen.adrem.fim.measure.assoc.asymmetric;

import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_A;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_A_GIVEN_B;
import be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

/**
 * @author Sandy Moens
 */
public class LeverageRuleMeasure extends RuleMeasureBase {

	public LeverageRuleMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Leverage";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		return P_A_GIVEN_B(rhs, lhs, dbSize)
				- (P_A(lhs, dbSize) * P_A(rhs, dbSize));
	}
}
