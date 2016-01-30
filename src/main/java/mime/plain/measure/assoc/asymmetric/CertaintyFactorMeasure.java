package mime.plain.measure.assoc.asymmetric;

import static mime.plain.measure.tool.Probability.P_A;
import static mime.plain.measure.tool.Probability.P_A_GIVEN_B;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

public class CertaintyFactorMeasure extends RuleMeasureBase {

	public CertaintyFactorMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Certainty Factor";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		double pB = P_A(rhs, dbSize);
		return (P_A_GIVEN_B(rhs, lhs, dbSize) - pB) / (1 - pB);
	}
}
