package ai.yue.library.rule;

import ai.yue.library.rule.fact.CreditRouteFact;
import ai.yue.library.rule.recommend.XRecommend;
import ai.yue.library.rule.utils.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class RouteServiceImpl implements RouteService{
    @Override
    public List<XRecommend> route(CreditRouteFact fact) {
        List<XRecommend> recommends = Lists.asList();

        if (fact == null) {
            return recommends;
        }
        if (StringUtils.isNotEmpty(fact.getFixCapitalNo())) {

        }

        return recommends;
    }
}
