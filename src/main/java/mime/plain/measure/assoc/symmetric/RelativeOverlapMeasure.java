package mime.plain.measure.assoc.symmetric;

import static mime.plain.measure.tool.Probability.P_AB;
import mime.plain.measure.assoc.RuleMeasureBase;
import mime.plain.measure.tool.Probability;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

public class RelativeOverlapMeasure extends RuleMeasureBase {

	public RelativeOverlapMeasure(PlainTransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Relative Overlap";
	}

	@Override
	public double evaluate(PlainItemSet lhs, PlainItemSet rhs) {
		return P_AB(lhs, rhs, dbSize) / Probability.P_A_OR_B(lhs, rhs, dbSize);
	}
}
