package mime.plain.measure.assoc.asymmetric;

import static mime.plain.measure.tool.Probability.P_A_GIVEN_B;
import static mime.plain.measure.tool.Probability.P_A_GIVEN_NOT_B;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

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
