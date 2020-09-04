package ai.yue.library.rule;

import ai.yue.library.rule.fact.CreditRouteFact;

import java.util.List;

public interface RouteService {

    List<Recommend> route(CreditRouteFact fact);

}
