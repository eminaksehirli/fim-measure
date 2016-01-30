package be.uantwerpen.adrem.fim.measure.assoc.symmetric;

import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_AB;
import be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.measure.tool.Probability;
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
