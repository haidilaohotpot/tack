package cn.edu.mju.manager.service;

import cn.edu.mju.common.entity.Tag;
import cn.edu.mju.common.dto.TagDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author <a href="https://github.com/haidilaohotpot">wonder4work</a>
 * @since 1.0.0 2019/9/26
 */
public interface TagService extends IService<Tag> {

    /**
     * 根据id获取热门标签详情
     *
     * @param tagIds
     * @return
     */
    List<Tag> queryHotTagDetails(Integer[] tagIds);


    /**
     * 查询标签详情
     *
     * @return
     */
    List<TagDTO> queryTagDetails();

    /**
     * 查询单条标签详情
     *
     * @return
     */
    TagDTO queryOneTagDetail(Integer tagId);

}
