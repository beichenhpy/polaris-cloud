package cn.beichenhpy.exception;

import cn.beichenhpy.enums.exception.BizExceptionEnum;

/**
 * 业务异常
 * <pre>
 *     //enum模式
 *     public void test(String id){
 *         User user = userService.selectOne(id);
 *         if (user == null) {
 *             throw new BizException(BizExceptionEnum.COMMON_EXCEPTION);
 *         }
 *     }
 *     //code + msg模式
 *     public void test2(String id) {
 *         Paper paper = sampleService.findPaper(id);
 *         if (paper == null) {
 *             throw new BizException("99999999","重大事件，老哥的paper不见了");
 *         }
 *     }
 * </pre>
 *
 * @author beichenhpy
 * @version 1.0.0
 */
public class BizException extends RuntimeException implements NeedRollback {

    private BizExceptionEnum exceptionEnum;
    private String code;
    private String message;

    public BizException(BizExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public BizException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 打印异常信息
     *
     * @return 返回异常信息
     */
    public String print() {
        if (exceptionEnum == null) {
            return String.format("[业务异常]: 业务码:%s, 异常业务信息:%s", code, message);
        } else {
            return String.format("[业务异常]: 业务码:%s, 异常业务信息:%s", exceptionEnum.getCode(), exceptionEnum.getMsg());
        }
    }


    @Override
    public synchronized Throwable fillInStackTrace() {
        //不打印堆栈
        return this;
    }
}
