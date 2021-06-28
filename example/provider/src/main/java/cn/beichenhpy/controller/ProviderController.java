package cn.beichenhpy.controller;

import cn.beichenhpy.modal.Comment;
import cn.beichenhpy.service.ProviderService;
import cn.beichenhpy.utils.asserts.AssertToolkit;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(list);
    }

    @PostMapping("/comment")
    public ResponseEntity<Void> addComment(@RequestBody Comment comment){
        boolean saved = providerService.save(comment);
        AssertToolkit.isTrue(saved,"save comment fail");
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
