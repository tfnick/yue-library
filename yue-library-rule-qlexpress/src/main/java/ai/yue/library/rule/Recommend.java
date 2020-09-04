package ai.yue.library.rule;

import java.math.BigDecimal;

public class Recommend {
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getCapitalNo() {
        return capitalNo;
    }

    public void setCapitalNo(Long capitalNo) {
        this.capitalNo = capitalNo;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public Long getPkgNo() {
        return pkgNo;
    }

    public void setPkgNo(Long pkgNo) {
        this.pkgNo = pkgNo;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
