package mime.plain.measure.assoc.symmetric;

import static mime.plain.measure.tool.Probability.P_A;
import static mime.plain.measure.tool.Probability.P_AB;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

public class KulcinskyMeasure extends RuleMeasureBase {

	public KulcinskyMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Kulcinsky";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		return (P_AB(lhs, rhs, dbSize) / 2)
				* ((1.0 / P_A(lhs, dbSize)) + (1.0 / P_A(rhs, dbSize)));
	}
}
