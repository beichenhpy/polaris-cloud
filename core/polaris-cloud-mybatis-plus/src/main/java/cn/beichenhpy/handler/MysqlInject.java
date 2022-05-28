package cn.beichenhpy.handler;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/2 23:30
 */
public class MysqlInject extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //不插入逻辑删除的字段
        methodList.add(new InsertBatchSomeColumn(tableFieldInfo -> !tableFieldInfo.isLogicDelete()));
        methodList.add(new AlwaysUpdateSomeColumnById());
        return methodList;
    }
}
