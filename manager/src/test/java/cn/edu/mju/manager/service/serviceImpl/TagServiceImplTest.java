package cn.edu.mju.manager.service.serviceImpl;

import cn.edu.mju.common.entity.Tag;
import cn.edu.mju.manager.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagServiceImplTest{


    @Autowired
    private TagService tagService;

    @Test
    public void list(){

        List<Tag> list = tagService.list();


    }


}