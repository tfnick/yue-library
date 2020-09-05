package ai.yue.library.rule.fact;

import ai.yue.library.rule.dictionary.CapitalProduct;
import ai.yue.library.rule.utils.Lists;

import java.math.BigDecimal;
import java.util.*;

public class CreditRouteFact {
    /*流量参数*/
    String utmSource = "";
    String utmMedium = "";
    String action = "credit";
    Long current = System.currentTimeMillis();
    String sex = "M";
    Integer age = 22;
    String workCityCode = "440300";
    String memberLevel = "金牌";
    String phone = "13800138000";
    String productCatgory = "201";
    /*客户端参数*/
    String fixCapitalNo = CapitalProduct.AABB201101.name();
    List<String> clientExcludes = Lists.asList(CapitalProduct.AABB201101.name(), CapitalProduct.AABB201201.name());
    /*我方规则要求-排除不合作资方，增强我方主动性*/
    List<String> serverExcludes = Lists.asList();
    List<String> productCodes = Lists.asList();
    /*资方规则要求参数*/
    boolean cptActive =true;
    BigDecimal cptBalance = BigDecimal.ZERO;
    Long cptOpenTime = 90000L;
    Long cptCloseTime = 180000L;
    List<String> cptMemberLevels = Lists.asList("金牌","银牌");
    Integer cptAgeMin = 18;
    Integer cptAgeMax = 50;
    /*评分规则要求参数*/
    Double weight = 0d;
    Double passRate = 0.10d;


    /*未匹配策略参数*/
    Boolean reserve = true;
    String reserveCapitalNo = CapitalProduct.AABB201101.name();
    Boolean demote = true;
    List<String> demoteCapitals = Lists.asList(CapitalProduct.BABB201202.name());


    public String getUtmSource() {
        return utmSource;
    }

    public void setUtmSource(String utmSource) {
        this.utmSource = utmSource;
    }

    public String getUtmMedium() {
        return utmMedium;
    }

    public void setUtmMedium(String utmMedium) {
        this.utmMedium = utmMedium;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getWorkCityCode() {
        return workCityCode;
    }

    public void setWorkCityCode(String workCityCode) {
        this.workCityCode = workCityCode;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFixCapitalNo() {
        return fixCapitalNo;
    }

    public void setFixCapitalNo(String fixCapitalNo) {
        this.fixCapitalNo = fixCapitalNo;
    }

    public List<String> getClientExcludes() {
        return clientExcludes;
    }

    public void setClientExcludes(List<String> clientExcludes) {
        this.clientExcludes = clientExcludes;
    }

    public List<String> getServerExcludes() {
        return serverExcludes;
    }

    public void setServerExcludes(List<String> serverExcludes) {
        this.serverExcludes = serverExcludes;
    }

    public boolean isCptActive() {
        return cptActive;
    }

    public void setCptActive(boolean cptActive) {
        this.cptActive = cptActive;
    }

    public BigDecimal getCptBalance() {
        return cptBalance;
    }

    public void setCptBalance(BigDecimal cptBalance) {
        this.cptBalance = cptBalance;
    }

    public Long getCptOpenTime() {
        return cptOpenTime;
    }

    public void setCptOpenTime(Long cptOpenTime) {
        this.cptOpenTime = cptOpenTime;
    }

    public Long getCptCloseTime() {
        return cptCloseTime;
    }

    public void setCptCloseTime(Long cptCloseTime) {
        this.cptCloseTime = cptCloseTime;
    }

    public List<String> getCptMemberLevels() {
        return cptMemberLevels;
    }

    public void setCptMemberLevels(List<String> cptMemberLevels) {
        this.cptMemberLevels = cptMemberLevels;
    }

    public Integer getCptAgeMin() {
        return cptAgeMin;
    }

    public void setCptAgeMin(Integer cptAgeMin) {
        this.cptAgeMin = cptAgeMin;
    }

    public Integer getCptAgeMax() {
        return cptAgeMax;
    }

    public void setCptAgeMax(Integer cptAgeMax) {
        this.cptAgeMax = cptAgeMax;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPassRate() {
        return passRate;
    }

    public void setPassRate(Double passRate) {
        this.passRate = passRate;
    }

    public Boolean getReserve() {
        return reserve;
    }

    public void setReserve(Boolean reserve) {
        this.reserve = reserve;
    }

    public String getReserveCapitalNo() {
        return reserveCapitalNo;
    }

    public void setReserveCapitalNo(String reserveCapitalNo) {
        this.reserveCapitalNo = reserveCapitalNo;
    }

    public Boolean getDemote() {
        return demote;
    }

    public void setDemote(Boolean demote) {
        this.demote = demote;
    }

    public List<String> getDemoteCapitals() {
        return demoteCapitals;
    }

    public void setDemoteCapitals(List<String> demoteCapitals) {
        this.demoteCapitals = demoteCapitals;
    }
}
