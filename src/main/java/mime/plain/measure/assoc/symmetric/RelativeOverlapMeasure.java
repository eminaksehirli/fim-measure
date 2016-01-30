package mime.plain.measure.assoc.symmetric;

import static mime.plain.measure.tool.Probability.P_AB;
import mime.plain.measure.assoc.RuleMeasureBase;
import mime.plain.measure.tool.Probability;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

public class RelativeOverlapMeasure extends RuleMeasureBase {

	public RelativeOverlapMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Relative Overlap";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		return P_AB(lhs, rhs, dbSize) / Probability.P_A_OR_B(lhs, rhs, dbSize);
	}
}
