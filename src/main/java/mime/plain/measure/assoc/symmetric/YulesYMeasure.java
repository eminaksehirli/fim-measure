package mime.plain.measure.assoc.symmetric;

import static java.lang.Math.sqrt;
import static mime.plain.measure.tool.Probability.P_AB;
import static mime.plain.measure.tool.Probability.P_NOT_A_B;
import static mime.plain.measure.tool.Probability.P_NOT_A_NOT_B;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

public class YulesYMeasure extends RuleMeasureBase {

	public YulesYMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Yule's Y";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		double pAB = P_AB(lhs, rhs, dbSize);
		double pNotANotB = P_NOT_A_NOT_B(lhs, rhs, dbSize);
		double pANotB = P_NOT_A_B(rhs, lhs, dbSize);
		double PNotAB = P_NOT_A_B(lhs, rhs, dbSize);
		return (sqrt(pAB * pNotANotB) - sqrt(pANotB * PNotAB))
				/ (sqrt(pAB * pNotANotB) + sqrt(pANotB * PNotAB));
	}
}
