package mime.plain.measure.assoc.asymmetric;

import static mime.plain.measure.tool.Probability.P_NOT_A_GIVEN_NOT_B;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

public class SpecificityMeasure extends RuleMeasureBase {

	public SpecificityMeasure(PlainTransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Specificity";
	}

	@Override
	public double evaluate(PlainItemSet lhs, PlainItemSet rhs) {
		return P_NOT_A_GIVEN_NOT_B(rhs, lhs, dbSize);
	}
}
