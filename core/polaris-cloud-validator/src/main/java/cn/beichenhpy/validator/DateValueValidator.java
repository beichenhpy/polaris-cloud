package cn.beichenhpy.validator;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 日期校验格式，通过format的参数来校验格式
 *
 * @author aaronuu
 * @see <a href="https://github.com/matevip/matecloud/tree/dev/mate-core/mate-starter-validator">mate-starter-validator</a>
 */
public class DateValueValidator implements ConstraintValidator<DateValue, String> {

	private Boolean required;

	private String format;

	@Override
	public void initialize(DateValue constraintAnnotation) {
		this.required = constraintAnnotation.required();
		this.format = constraintAnnotation.format();
	}

	@Override
	public boolean isValid(String dateValue, ConstraintValidatorContext context) {
		if (StrUtil.isEmpty(dateValue)) {
			// 校验是不是必填
			return !required;
		} else {
			try {
				// 校验日期格式
				DateUtil.parse(dateValue, format);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
}
