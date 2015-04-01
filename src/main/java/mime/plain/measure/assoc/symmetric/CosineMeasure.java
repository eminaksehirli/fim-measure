package mime.plain.measure.assoc.symmetric;

import static mime.plain.measure.tool.Probability.P_A;
import static mime.plain.measure.tool.Probability.P_AB;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

public class CosineMeasure extends RuleMeasureBase {

	public CosineMeasure(PlainTransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Cosine";
	}

	@Override
	public double evaluate(PlainItemSet lhs, PlainItemSet rhs) {
		return P_AB(lhs, rhs, dbSize)
				/ Math.sqrt((P_A(lhs, dbSize) * P_A(rhs, dbSize)));
	}
}
