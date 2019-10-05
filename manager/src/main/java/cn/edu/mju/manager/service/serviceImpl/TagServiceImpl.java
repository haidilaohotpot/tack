package cn.edu.mju.manager.service.serviceImpl;

import cn.edu.mju.common.dto.TagDTO;
import cn.edu.mju.common.entity.Tag;
import cn.edu.mju.manager.mapper.TagMapper;
import cn.edu.mju.manager.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/26
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {



    /**
     * 获取标签详情
     *
     * @param tagIds
     * @return
     */
    @Override
    public  List<Tag> queryHotTagDetails(Integer[] tagIds){
        if (tagIds == null || tagIds.length <= 0){
            return this.list();
        } else {
            return this.baseMapper.queryHotTagDetails(tagIds);
        }
    }


    /**
     * 查询标签详情
     *
     * @return
     */
    @Override
    public List<TagDTO> queryTagDetails() {
        return this.baseMapper.queryTagDetails();
    }

    /**
     * 查询单条标签详情
     *
     * @return
     */
    @Override
    public TagDTO queryOneTagDetail(Integer tagId) {
        return this.baseMapper.queryOneTagDetail(tagId);
    }


}
