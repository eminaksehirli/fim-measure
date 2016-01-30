package be.uantwerpen.adrem.fim.measure.assoc.asymmetric;

import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_AB;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_NOT_A_NOT_B;
import be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

public class AccuracyMeasure extends RuleMeasureBase {

	public AccuracyMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Accuracy";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		return P_AB(lhs, rhs, dbSize) + P_NOT_A_NOT_B(lhs, rhs, dbSize);
	}
}
