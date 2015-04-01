package mime.plain.measure.assoc.asymmetric;

import static mime.plain.measure.tool.Probability.P_AB;
import static mime.plain.measure.tool.Probability.P_NOT_A_NOT_B;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

public class AccuracyMeasure extends RuleMeasureBase {

	public AccuracyMeasure(PlainTransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Accuracy";
	}

	@Override
	public double evaluate(PlainItemSet lhs, PlainItemSet rhs) {
		return P_AB(lhs, rhs, dbSize) + P_NOT_A_NOT_B(lhs, rhs, dbSize);
	}
}
