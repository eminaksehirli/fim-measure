package mime.plain.measure.assoc.symmetric;

import static mime.plain.measure.tool.Probability.P_AB;
import static mime.plain.measure.tool.Probability.P_NOT_A_B;
import static mime.plain.measure.tool.Probability.P_NOT_A_NOT_B;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.base.PlainItemSet;
import be.uantwerpen.adrem.fim.base.PlainTransactionDB;

public class YulesQMeasure extends RuleMeasureBase {

	public YulesQMeasure(PlainTransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Yule's Q";
	}

	@Override
	public double evaluate(PlainItemSet lhs, PlainItemSet rhs) {
		double pAB = P_AB(lhs, rhs, dbSize);
		double pNotANotB = P_NOT_A_NOT_B(lhs, rhs, dbSize);
		double pANotB = P_NOT_A_B(rhs, lhs, dbSize);
		double PNotAB = P_NOT_A_B(lhs, rhs, dbSize);
		return ((pAB * pNotANotB) - (pANotB * PNotAB))
				/ ((pAB * pNotANotB) + (pANotB * PNotAB));
	}
}
