package ai.yue.library.rule;

import java.util.ArrayList;
import java.util.List;
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
        test.testNoShortCircuitScore();
        System.out.println("---------------------!");
    }

    private ExpressRunner runner = new ExpressRunner();

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

}