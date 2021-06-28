package cn.beichenhpy.controller;

import cn.beichenhpy.modal.Comment;
import cn.beichenhpy.service.ProviderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping("/info/{aid}")
    public ResponseEntity<List<Comment>> getCommentInfoByArticleId(@PathVariable("aid") String aid) {
        LambdaQueryWrapper<Comment> queryWrapper = Wrappers.lambdaQuery(new Comment());
        queryWrapper.eq(Comment::getArticleId, aid);
        List<Comment> list = providerService.list(queryWrapper);
        return ResponseEntity.ok(list);
    }
}
