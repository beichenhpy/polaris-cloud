package cn.beichenhpy.service.impl;

import cn.beichenhpy.mapper.ApplyMapper;
import cn.beichenhpy.modal.Apply;
import cn.beichenhpy.service.ApplyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ApplyMapper applyMapper;

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
                .remark("地补100更新-4").build());
        result.add(Apply.builder()
                .vid("LG102")
                .applyType("1002")
                .remark("地补102更新-4").build());
        result.add(Apply.builder()
                .vid("LG116")
                .applyType("1002")
                .remark("地补116插入").build());
        result.add(Apply.builder()
                .vid("LG117")
                .applyType("1001")
                .remark("国补117插入").build());
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
        long start = System.nanoTime();
        //filter id = null to insert 8225750ns 底层拼接sql
        List<Apply> insertList = importDatas.parallelStream()
                .filter(data -> data.getId() == null)
                .collect(Collectors.toList());
        if (!insertList.isEmpty()){
            //这种只有Mysql支持
            applyMapper.insertBatchSomeColumn(insertList);
        }
        //update 4111708ns
        importDatas.removeAll(insertList);
        updateBatchById(importDatas);

        //cost 18730958ns 底层用的for循环 还是比较慢的，但是支持全部的数据库
        //saveOrUpdateBatch(importDatas);
        log.info("end:{}",System.nanoTime() - start);
    }

    private String assemble(String applyType ,String vid){
        return applyType + "-" + vid;
    }
}
