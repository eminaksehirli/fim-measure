package mime.plain.measure.assoc.asymmetric;

import static mime.plain.measure.tool.Probability.P_A_GIVEN_B;
import static mime.plain.measure.tool.Probability.P_A_GIVEN_NOT_B;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

public class RelativeRiskMeasure extends RuleMeasureBase {

	public RelativeRiskMeasure(PlainTransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Relative Risk";
	}

	@Override
	public double evaluate(PlainItemSet lhs, PlainItemSet rhs) {
		return P_A_GIVEN_B(rhs, lhs, dbSize)
				/ P_A_GIVEN_NOT_B(rhs, lhs, dbSize);
	}
}
