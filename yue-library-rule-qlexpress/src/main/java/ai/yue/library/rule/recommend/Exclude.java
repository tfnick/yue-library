package ai.yue.library.rule.recommend;

import java.util.List;

public class Exclude {
    //全局子产品编码
    String productCode;
    //资金方评分
    List<String> hits;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<String> getHits() {
        return hits;
    }

    public void setHits(List<String> hits) {
        this.hits = hits;
    }
}
