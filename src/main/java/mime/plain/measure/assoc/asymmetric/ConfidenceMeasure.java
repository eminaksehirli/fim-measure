package mime.plain.measure.assoc.asymmetric;

import static mime.plain.measure.tool.Probability.P_A_GIVEN_B;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

public class ConfidenceMeasure extends RuleMeasureBase {

	public ConfidenceMeasure(PlainTransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Confidence";
	}

	@Override
	public double evaluate(PlainItemSet lhs, PlainItemSet rhs) {
		return P_A_GIVEN_B(rhs, lhs, dbSize);
	}
}
