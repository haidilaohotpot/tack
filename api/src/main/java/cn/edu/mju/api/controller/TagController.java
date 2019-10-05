package cn.edu.mju.api.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import cn.edu.mju.api.enums.ResultEnum;
import cn.edu.mju.api.vo.ResultMap;
import cn.edu.mju.common.dto.TagDTO;
import cn.edu.mju.common.entity.Tag;
import cn.edu.mju.common.exception.RestException;
import cn.edu.mju.common.util.ValidateBeanUtils;
import cn.edu.mju.manager.service.ArticleTagService;
import cn.edu.mju.manager.service.TagService;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Slf4j
@RestController
@RequestMapping("/tags")
public class TagController {

    //查询热门标签的个数
    public static final int HOT_ARTICLE_TAG_NUM = 7;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleTagService articleTagService;


    /**
     * 标签列表
     */
    @GetMapping("/list")
    public ResultMap list() throws JsonProcessingException {

        List<Tag> tagList = tagService.list();
        log.info("tagList = {}",tagList.toString());
        return ResultMap.ok(tagList,ResultEnum.SUCCESS);
    }


    /**
     * 热门标签
     * @return
     */
    @GetMapping("/hot")
    public ResultMap hot() {
        List<Integer> tagIds = articleTagService.queryHotTagIds(HOT_ARTICLE_TAG_NUM);
        log.info("tagIds.size()={} {}", tagIds.size(), tagIds.toString());
        Integer[] tagIdsArray = new Integer[tagIds.size()];
        List<Tag> tagList = tagService.queryHotTagDetails(tagIds.toArray(tagIdsArray));
        log.info("tagList = {}",tagList.toString());
        return ResultMap.ok(tagList,ResultEnum.SUCCESS);
    }

    /**
     * 查询标签详情
     */
    @GetMapping("/detail")
    public ResultMap detail() {
        List<TagDTO> tagDTOList = tagService.queryTagDetails();
        log.info("tagDTOList = {}",tagDTOList.toString());
        return ResultMap.ok(tagDTOList,ResultEnum.SUCCESS);
    }


    /**
     * 单条标签详情
     */
    @GetMapping("/detail/{tagId}")
    public ResultMap detailById(@PathVariable("tagId") Integer tagId) {
        TagDTO tagDTO = tagService.queryOneTagDetail(tagId);
        return ResultMap.ok(tagDTO,ResultEnum.SUCCESS);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResultMap save(@RequestBody Tag tag) {

        if(ValidateBeanUtils.isEmpty(tag)){
            throw new RestException();
        }
        tagService.save(tag);

        return ResultMap.ok(ResultEnum.SUCCESS);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResultMap update(@RequestBody Tag tag) {

        if(ValidateBeanUtils.isEmpty(tag)){
            throw new RestException();
        }

        //全部更新
        tagService.updateById(tag);

        return ResultMap.ok(ResultEnum.SUCCESS);
    }

    /**
     * 批量化删除
     */
    @RequestMapping("/delete")
    public ResultMap delete(@RequestBody Integer[] ids) {

        tagService.removeByIds(Arrays.asList(ids));

        return ResultMap.ok(ResultEnum.SUCCESS);
    }



}
