package ai.yue.library.base.validation;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liuwenxue
 * @Description:
 * @date 2020/6/24 11:50
 */
public final class BeanValidateUtils {


    /**
     * 参数校验工具方法
     * @param target
     * @param <T>
     * @return
     */
    public static <T> String validateWithParamter(T target){
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(target);
        Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<T> error = iterator.next();
            StringBuffer buffer = new StringBuffer().append("[")
                    .append(error.getPropertyPath().toString()).append("]")
                    .append(error.getMessage());
            return buffer.toString();
        }
        return  null;
    }
}
