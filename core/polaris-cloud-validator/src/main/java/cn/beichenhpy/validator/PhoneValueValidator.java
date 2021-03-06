package cn.beichenhpy.validator;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验手机号是否合法
 *
 * @author aaronuu
 * @see <a href="https://github.com/matevip/matecloud/tree/dev/mate-core/mate-starter-validator">mate-starter-validator</a>
 */
public class PhoneValueValidator implements ConstraintValidator<PhoneValue, String> {

	private Boolean required;

	@Override
	public void initialize(PhoneValue constraintAnnotation) {
		this.required = constraintAnnotation.required();
	}

	@Override
	public boolean isValid(String phoneValue, ConstraintValidatorContext context) {
		if (StrUtil.isEmpty(phoneValue)) {
			return !required;
		} else {
			return ReUtil.isMatch(Validator.MOBILE, phoneValue);
		}
	}

}
