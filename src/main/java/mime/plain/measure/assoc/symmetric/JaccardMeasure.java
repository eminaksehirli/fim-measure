package mime.plain.measure.assoc.symmetric;

import static mime.plain.measure.tool.Probability.P_A;
import static mime.plain.measure.tool.Probability.P_AB;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

public class JaccardMeasure extends RuleMeasureBase {

	public JaccardMeasure(PlainTransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Jaccard";
	}

	@Override
	public double evaluate(PlainItemSet lhs, PlainItemSet rhs) {
		double pAB = P_AB(lhs, rhs, dbSize);
		return pAB / (P_A(lhs, dbSize) + P_A(rhs, dbSize) - pAB);
	}
}
