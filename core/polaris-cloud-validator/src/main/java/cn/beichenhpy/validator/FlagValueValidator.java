package cn.beichenhpy.validator;

import cn.beichenhpy.enums.IsOkEnum;
import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


/**
 * 校验标识，只有Y和N两种状态的标识
 *
 * @author aaronuu
 * @see <a href="https://github.com/matevip/matecloud/tree/dev/mate-core/mate-starter-validator">mate-starter-validator</a>
 */
public class FlagValueValidator implements ConstraintValidator<FlagValue, String> {

	private Boolean required;

	@Override
	public void initialize(FlagValue constraintAnnotation) {
		this.required = constraintAnnotation.required();
	}

	@Override
	public boolean isValid(String flagValue, ConstraintValidatorContext context) {

		// 如果是必填的
		if (required) {
			return IsOkEnum.Y.getCode().equals(flagValue) || IsOkEnum.N.getCode().equals(flagValue);
		} else {

			//如果不是必填，可以为空
			if (StrUtil.isEmpty(flagValue)) {
				return true;
			} else {
				return IsOkEnum.Y.getCode().equals(flagValue) || IsOkEnum.N.getCode().equals(flagValue);
			}
		}
	}
}
