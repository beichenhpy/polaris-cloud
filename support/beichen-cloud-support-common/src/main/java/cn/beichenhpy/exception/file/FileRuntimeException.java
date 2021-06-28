package cn.beichenhpy.exception.file;

import cn.beichenhpy.exception.NoNeedRollback;

/**
 * @author beichenhpy
 * @apiNote File相关业务的RuntimeException
 */
public class FileRuntimeException extends RuntimeException implements NoNeedRollback {
}
