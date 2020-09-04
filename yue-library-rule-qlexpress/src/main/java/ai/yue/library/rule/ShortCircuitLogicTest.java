package ai.yue.library.rule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;

/**
 * 短路逻辑测试类
 * @author tianqiao
 *
 */
public class ShortCircuitLogicTest {

    public static void main(String[] args) throws Exception{
        ShortCircuitLogicTest test = new ShortCircuitLogicTest();
//        test.testShortCircuit();
//        System.out.println("---------------------");
        test.testComplex();
        System.out.println("---------------------!");
    }

    private ExpressRunner runner = new ExpressRunner(true, true);

    public void initial() throws Exception{
        runner.addOperatorWithAlias("小于","<","不满足期望 $1 小于 $2");
        runner.addOperatorWithAlias("大于",">","不满足期望 $1 大于 $2");
    }

    public boolean calculateLogicTest(String expression,IExpressContext<String,Object> expressContext,List<String> errorInfo) throws Exception {
        Boolean result = (Boolean)runner.execute(expression, expressContext, errorInfo, true, false);
        if(result.booleanValue() == true){
            return true;
        }
        return false;
    }

    /**
     * 测试短路逻辑,并且输出出错信息
     * @throws Exception
     */

    public void testShortCircuit() throws Exception {
        runner.setShortCircuit(true);//非端路测试

        IExpressContext<String,Object> expressContext = new DefaultContext<String,Object>();
        expressContext.put("违规天数", 100);
        expressContext.put("虚假交易扣分", 11);
        expressContext.put("VIP", false);
        List<String> errorInfo = new ArrayList<String>();
        initial();
        StringBuffer expression = new StringBuffer();
        expression.append("2 小于 1 \n");
        expression.append(" and \n");
        expression.append("(违规天数 小于 90 or 虚假交易扣分 小于 12)\n");
        System.out.println(expression.toString());
        boolean result = calculateLogicTest(expression.toString(), expressContext, errorInfo);

        if(result){
            System.out.println("result is success!");
        }else{
            System.out.println("result is fail!");
            for(String error : errorInfo){
                System.out.println(error);
            }
        }

    }

    /**
     * 测试非短路逻辑,并且输出出错信息
     * @throws Exception
     */

    public void testNoShortCircuit() throws Exception {
        runner.setShortCircuit(false);//非端路测试

        IExpressContext<String,Object> expressContext = new DefaultContext<String,Object>();
        expressContext.put("违规天数", 100);
        expressContext.put("虚假交易扣分", 11);
        expressContext.put("VIP", false);
        List<String> errorInfo = new ArrayList<String>();
        initial();
        String expression ="2 小于 1 and (违规天数 小于 90 or 虚假交易扣分 小于 10)";
        boolean result = calculateLogicTest(expression, expressContext, errorInfo);
        if(result){
            System.out.println("result is success!");
        }else{
            System.out.println("result is fail!");
            for(String error : errorInfo){
                System.out.println(error);
            }
        }

    }



    /**
     * 测试非短路逻辑,并且输出出错信息
     * @throws Exception
     */

    public void testNoShortCircuitScore() throws Exception {
        runner.setShortCircuit(false);//非端路测试

        IExpressContext<String,Object> expressContext = new DefaultContext<String,Object>();
        expressContext.put("违规天数", 100);
        expressContext.put("虚假交易扣分", 11);
        expressContext.put("VIP", false);
        List<String> errorInfo = new ArrayList<String>();
        initial();
        String expression ="if(2 小于 1 and (违规天数 小于 90 or 虚假交易扣分 小于 10)){return 1 * 违规天数}else{return 1 * 虚假交易扣分}";
        Integer result = (Integer)runner.execute(expression, expressContext, errorInfo, true, false);
        System.out.println("结果得分："+ result);
        if(result == 100){
            System.out.println("result is success!");
        }else{
            System.out.println("result is fail!");
            for(String error : errorInfo){
                System.out.println(error);
            }
        }

    }


    /**
     * 流量（utm_source(baidu.com)、utm_medium(cpc)、credit、action、time、phone、age、sex、work_city_code、money）
     * 客户端支持资金方排除，资金方指定（增强客户端的灵活性）
     * 助贷初步路由->指定流量的目标资金方（可以一个多个或全部，目的是增强助贷方的路由主动性）
     * 资方规则路由->在第一步基础上，判断流量是否符合资金方的要求
     * 资方评分->对流量匹配的所有资金方进行评分，返回N个符合要求的资金方
     * 未匹配资金方处理->未匹配任何资金方的处理策略
     * @throws Exception
     */
    public void testComplex() throws Exception {
        runner.setShortCircuit(false);//非端路测试
        //TODO 注意，所有入参必须有默认值，不允许为null
        IExpressContext<String,Object> expressContext = new DefaultContext<String,Object>();
        List<String> members = new ArrayList<>();
        members.add("金牌");
        members.add("银牌");
        List<String> excludes = new ArrayList<>();
        excludes.add("202201");
        excludes.add("202101");
        //客户端入参
        expressContext.put("排除资方", excludes);

        expressContext.put("用户申请金额", 10000);
        expressContext.put("用户会员等级", "铜牌");
        expressContext.put("用户风险等级", 1);//用于提款
        expressContext.put("当前时间", 1200);//上午12点
        expressContext.put("用户年龄", 40);

        //服务端入参或者条件参数
        expressContext.put("资金方失效", true);
        expressContext.put("资金方余额", 20000000);
        expressContext.put("资金方营业时间", 800);
        expressContext.put("资金方挂牌时间", 1800);
        expressContext.put("资金方期望会员等级", members);
        expressContext.put("资金方期望年龄下限", 18);
        expressContext.put("资金方期望年龄上限", 55);
        //其他动态维度参数
        expressContext.put("当前资金方权重", 6);
        expressContext.put("当前资金方授信通过率", 0.11);
        expressContext.put("当前资金方提款通过率", 0.96);

        StringBuffer express = new StringBuffer();
        express.append("RET = new HashMap();");
        express.append("hits = new ArrayList();");
        express.append("RET.put('hits',hits);");
        express.append("RET.put('score',0);");
        //资金方前置风控环节
        express.append("if(用户年龄 < 资金方期望年龄下限 or 用户年龄 > 资金方期望年龄上限) then {hits.add('R0'+'#@#'+'用户年龄 < 资金方期望年龄下限 or 用户年龄 > 资金方期望年龄上限');};");
        //资金方规则匹配环节
        express.append("if(资金方失效) then {hits.add('R1'+'#@#'+'用户年龄 < 资金方期望年龄下限 or 用户年龄 > 资金方期望年龄上限');};");
        express.append("if(资金方余额 - 用户申请金额 < 0) then {hits.add('R2'+'#@#'+'资金方余额 - 用户申请金额 < 0');};");
        express.append("if(!资金方期望会员等级.contains(用户会员等级)) then {hits.add('R3'+'#@#'+'!资金方期望会员等级.contains(用户会员等级)');};");
        //评分环节
        express.append("score = 当前资金方权重 * 0.5 + 当前资金方授信通过率 * 0.8;");
        express.append("RET.put('score',score);");
        express.append("return RET;");

        List<String> errorInfo = new ArrayList<String>();
        initial();
        String expression = express.toString();

        Map RET = (Map) runner.execute(expression, expressContext, errorInfo, true, false);
        List hits = (ArrayList)RET.get("hits");
        BigDecimal score = BigDecimal.valueOf(Double.valueOf(RET.get("score").toString()));
        System.out.println("命中拒绝规则："+ hits.size());
        System.out.println("资金方评分："+ score);
        for(String error : errorInfo){
            System.out.println(error);
        }
        for (Object hitRule : hits) {
            System.out.println(hitRule);
        }

    }
}