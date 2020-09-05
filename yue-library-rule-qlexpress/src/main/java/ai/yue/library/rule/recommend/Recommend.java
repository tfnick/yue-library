package ai.yue.library.rule.recommend;

import java.math.BigDecimal;


public class Recommend {
    //全局子产品编码
    String productCode;
    //资金方评分
    BigDecimal score;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
