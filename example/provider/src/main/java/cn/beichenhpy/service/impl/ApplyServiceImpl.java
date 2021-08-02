package cn.beichenhpy.service.impl;

import cn.beichenhpy.mapper.ApplyMapper;
import cn.beichenhpy.modal.Apply;
import cn.beichenhpy.service.ApplyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author beichenhpy
 * @version 1.0.0
 * @apiNote
 * @since 2021/8/2 20:00
 */
@Slf4j
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {

    /**
     * 数据准备
     *
     * @return mock
     */
    private List<Apply> prepare() {
        List<Apply> result = new ArrayList<>();
        result.add(Apply.builder()
                .vid("LG100")
                .applyType("1002")
                .remark("地补100更新").build());
        result.add(Apply.builder()
                .vid("LG102")
                .applyType("1002")
                .remark("地补102更新").build());
        result.add(Apply.builder()
                .vid("LG104")
                .applyType("1002")
                .remark("地补104插入").build());
        result.add(Apply.builder()
                .vid("LG104")
                .applyType("1001")
                .remark("国补104插入").build());
        return result;
    }

    @Override
    public void importData() {
        //导入的数据
        List<Apply> importDatas = prepare();
        //查询数据库的数据
        List<Apply> existDatas = list(new QueryWrapper<Apply>()
                .lambda()
                .select(Apply::getId, Apply::getVid, Apply::getApplyType));
        //create index
        Map<String, Apply> index = new HashMap<>();
        for (Apply existData : existDatas) {
            //用apply_type-vid 作为key 去唯一索引
            index.put(assemble(existData.getApplyType(),existData.getVid()), existData);
        }
        for (Apply importData : importDatas) {
            Apply existData = index.get(assemble(importData.getApplyType(),importData.getVid()));
            if (existData != null) {
                importData.setId(existData.getId());
            }
        }
        saveOrUpdateBatch(importDatas);
    }

    private String assemble(String applyType ,String vid){
        return applyType + "-" + vid;
    }
}
