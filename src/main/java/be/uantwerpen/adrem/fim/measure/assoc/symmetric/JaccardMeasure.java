package be.uantwerpen.adrem.fim.measure.assoc.symmetric;

import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_A;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_AB;
import be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

public class JaccardMeasure extends RuleMeasureBase {

	public JaccardMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Jaccard";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		double pAB = P_AB(lhs, rhs, dbSize);
		return pAB / (P_A(lhs, dbSize) + P_A(rhs, dbSize) - pAB);
	}
}
