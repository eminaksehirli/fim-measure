package mime.plain.measure.assoc.asymmetric;

import static mime.plain.measure.tool.Probability.P_A;
import static mime.plain.measure.tool.Probability.P_NOT_A;
import static mime.plain.measure.tool.Probability.P_NOT_A_B;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

public class ConvictionMeasure extends RuleMeasureBase {

	public ConvictionMeasure(PlainTransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Conviction";
	}

	@Override
	public double evaluate(PlainItemSet lhs, PlainItemSet rhs) {
		return P_A(lhs, dbSize) * P_NOT_A(rhs, dbSize)
				/ P_NOT_A_B(rhs, lhs, dbSize);
	}
}
