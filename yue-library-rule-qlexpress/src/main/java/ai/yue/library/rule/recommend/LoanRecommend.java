package ai.yue.library.rule.recommend;

import java.math.BigDecimal;

public class LoanRecommend {
    //推荐理由
    String reason;
    //资金方
    Long capitalNo;
    //全局产品编码
    Long productCode;
    //资金包(规则维度)
    Long pkgNo;
    //资金方评分
    BigDecimal score;
}
