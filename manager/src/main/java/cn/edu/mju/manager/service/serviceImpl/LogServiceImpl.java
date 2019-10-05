package cn.edu.mju.manager.service.serviceImpl;

import cn.edu.mju.common.entity.Log;
import cn.edu.mju.manager.mapper.LogMapper;
import cn.edu.mju.manager.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/27
 */
@Service
@Slf4j
public class LogServiceImpl extends ServiceImpl<LogMapper,Log> implements LogService {
}
