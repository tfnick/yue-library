package ai.yue.library.rule;

import ai.yue.library.rule.fact.CreditRouteFact;
import ai.yue.library.rule.recommend.XRecommend;

import java.util.List;

public interface RouteService {

    List<XRecommend> route(CreditRouteFact fact);

}
