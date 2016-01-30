package mime.plain.measure.assoc.asymmetric;

import static mime.plain.measure.tool.Probability.P_A_GIVEN_B;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

public class ConfidenceMeasure extends RuleMeasureBase {

	public ConfidenceMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Confidence";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		return P_A_GIVEN_B(rhs, lhs, dbSize);
	}
}
