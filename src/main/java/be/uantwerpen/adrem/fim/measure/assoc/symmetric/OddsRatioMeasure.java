package mime.plain.measure.assoc.symmetric;

import static mime.plain.measure.tool.Probability.P_AB;
import static mime.plain.measure.tool.Probability.P_NOT_A_B;
import static mime.plain.measure.tool.Probability.P_NOT_A_NOT_B;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

public class OddsRatioMeasure extends RuleMeasureBase {

	public OddsRatioMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Odds Ratio";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		return (P_AB(lhs, rhs, dbSize) * P_NOT_A_NOT_B(lhs, rhs, dbSize))
				/ (P_NOT_A_B(lhs, rhs, dbSize) * P_NOT_A_B(rhs, lhs, dbSize));
	}
}
