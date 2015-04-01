package mime.plain.measure.assoc.symmetric;

import static mime.plain.measure.tool.Probability.P_A;
import static mime.plain.measure.tool.Probability.P_AB;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

public class KulcinskyMeasure extends RuleMeasureBase {

	public KulcinskyMeasure(PlainTransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Kulcinsky";
	}

	@Override
	public double evaluate(PlainItemSet lhs, PlainItemSet rhs) {
		return (P_AB(lhs, rhs, dbSize) / 2)
				* ((1.0 / P_A(lhs, dbSize)) + (1.0 / P_A(rhs, dbSize)));
	}
}
