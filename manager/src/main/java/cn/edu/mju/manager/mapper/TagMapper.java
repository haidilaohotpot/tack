package cn.edu.mju.manager.mapper;

import cn.edu.mju.common.dto.TagDTO;
import cn.edu.mju.common.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author <a href="https://github.com/haidilaohotpot">wonder4work</a>
 * @since 1.0.0 2019/9/26
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {


    /**
     * 获取标签详情
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
