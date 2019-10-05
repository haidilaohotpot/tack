package cn.edu.mju.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="https://github.com/haidilaohotpot">cheng</a>
 * @since 1.0.0 2019/9/28
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/article/index")
    public String index(){
        return "article/index";
    }

    @GetMapping("/article/edit")
    public String edit(){
        return "article/edit";
    }


    @GetMapping("/article/archive")
    public String archive(){
        return "article/archive";
    }


    @GetMapping("/article/view")
    public String view(){
        return "article/view";
    }


    @GetMapping("/board/index")
    public String boardIndex(){
        return "board/index";
    }


    @GetMapping("/category/index")
    public String categoryIndex(){
        return "category/index";
    }


    @GetMapping("/category/view")
    public String categoryView(){
        return "category/view";
    }


    @GetMapping("/tag/index")
    public String tagIndex(){
        return "tag/index";
    }

    @GetMapping("/tag/view")
    public String tagView(){
        return "tag/view";
    }


    @GetMapping("/log/index")
    public String logIndex(){
        return "log/index";
    }


    @GetMapping("/manager/index")
    public String managerIndex() {

        return "manager/index";
    }

}
