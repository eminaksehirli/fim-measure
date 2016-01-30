package be.uantwerpen.adrem.fim.measure.assoc.symmetric;

import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_AB;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_NOT_A_B;
import static be.uantwerpen.adrem.fim.measure.tool.Probability.P_NOT_A_NOT_B;
import be.uantwerpen.adrem.fim.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

/**
 * @author Sandy Moens
 */
public class YulesQMeasure extends RuleMeasureBase {

	public YulesQMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Yule's Q";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		double pAB = P_AB(lhs, rhs, dbSize);
		double pNotANotB = P_NOT_A_NOT_B(lhs, rhs, dbSize);
		double pANotB = P_NOT_A_B(rhs, lhs, dbSize);
		double PNotAB = P_NOT_A_B(lhs, rhs, dbSize);
		return ((pAB * pNotANotB) - (pANotB * PNotAB))
				/ ((pAB * pNotANotB) + (pANotB * PNotAB));
	}
}
