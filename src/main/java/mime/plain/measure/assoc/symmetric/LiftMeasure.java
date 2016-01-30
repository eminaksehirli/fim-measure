package mime.plain.measure.assoc.symmetric;

import static mime.plain.measure.tool.Probability.P_A;
import static mime.plain.measure.tool.Probability.P_AB;
import mime.plain.measure.assoc.RuleMeasureBase;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

public class LiftMeasure extends RuleMeasureBase {

	public LiftMeasure(TransactionDB db) {
		super(db);
	}

	@Override
	public String getName() {
		return "Lift";
	}

	@Override
	public double evaluate(Itemset lhs, Itemset rhs) {
		return P_AB(lhs, rhs, dbSize) / (P_A(lhs, dbSize) * P_A(rhs, dbSize));
	}
}
