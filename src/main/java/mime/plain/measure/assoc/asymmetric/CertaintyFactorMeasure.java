package mime.plain.measure.assoc.asymmetric;

import static mime.plain.measure.tool.Probability.P_A;
import static mime.plain.measure.tool.Probability.P_A_GIVEN_B;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

public class CertaintyFactorMeasure extends RuleMeasureBase {

	public CertaintyFactorMeasure(PlainTransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Certainty Factor";
	}

	@Override
	public double evaluate(PlainItemSet lhs, PlainItemSet rhs) {
		double pB = P_A(rhs, dbSize);
		return (P_A_GIVEN_B(rhs, lhs, dbSize) - pB) / (1 - pB);
	}
}
