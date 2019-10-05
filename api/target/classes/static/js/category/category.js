var CategoryOperation = {

    //根据tag 查询文章
    listArticlesByCategoryIdUrl:"/article/list",

    listCategoryDetailUrl:"/category/detail/",

    categoryId:0,

    current:1,

    pages:1,

    pageNo:1,

    loadPages:function(categoryId){

        var pageData = {};

        pageData.pageNo=CategoryOperation.pageNo;
        pageData.categoryId=categoryId;

        //获取文章
        $.getJSON(CategoryOperation.listArticlesByCategoryIdUrl, pageData,function (result) {

            // console.log(result);
            if (200 == result.code) {

                var data = result.data;

                var page = result.page;

                CategoryOperation.pages = parseInt(page.pages);


                CategoryOperation.current = parseInt(page.current);


                if(CategoryOperation.hasPage()){

                    var loadHtml = '<button type="button" class="btn btn-primary btn-lg btn-block" id="next_page" onclick="CategoryOperation.onClinkNextPage('+CategoryOperation.categoryId+');" >加载更多</button>';
                    $("#load").html(loadHtml);

                }

                var html = '';

                $.each(data, function (index, article) {


                    html += '<div data-v-09cd9b87="" data-v-cbf3ed42="" class="el-card me-area is-always-shadow" data-v-25682a20="">';
                    html += '<div class="el-card__body" style="padding: 16px;">';
                    html += '<div data-v-09cd9b87="" class="me-article-header">';
                    html += '<a data-v-09cd9b87="" class="me-article-title" onclick="common.gotoArticleView('+article.articleId+')">' + article.title + '</a>';
                    html += '<span data-v-09cd9b87="" class="me-pull-right me-article-count">';
                    html += '<span data-v-09cd9b87="" class="me-pull-right me-article-count">';
                    html += '<i data-v-09cd9b87="" class="el-icon-view"></i>&nbsp;' + article.viewNum + '</span>';
                    html += '</div>';
                    html += '<div data-v-09cd9b87="" class="me-artile-description">' + article.summary + '</div>';
                    html += '<div data-v-09cd9b87="" class="me-article-footer">';
                    html += '<span data-v-09cd9b87="" class="me-article-author">';
                    html += '<i data-v-09cd9b87="" class="me-icon-author"></i>&nbsp;<a href="' + article.userLink + '" target="_blank">' + article.userName + '</a></span>';

                    //遍历标签

                    var tags = article.tags;

                    $.each(tags, function (index, tag) {

                        html += '<span data-v-09cd9b87="" class="el-tag el-tag--success el-tag--mini">' + tag + '</span>';

                    });

                    html += '<span data-v-09cd9b87="" class="me-pull-right me-article-count">';
                    html += '<i data-v-09cd9b87="" class="el-icon-time"></i>&nbsp;' + article.createTime + '</span>';

                    html += '</div>';
                    html += '</div>';
                    html += '</div>';


                });


                $("#scroll-page").append(html);


            }
            //    todo

        });


    },

    //根据分类id查询文章

    findArticlesByCategoryId: function (categoryId) {

        CategoryOperation.categoryId =  categoryId;

        CategoryOperation.loadPages(categoryId);

    },


    //点击加载更多

    onClinkNextPage: function () {


        if(CategoryOperation.current < CategoryOperation.pages){

            CategoryOperation.pageNo++;

            CategoryOperation.loadPages(CategoryOperation.categoryId);

            if(CategoryOperation.current >= CategoryOperation.pages || CategoryOperation.pageNo >= IndexOperation.pages){

                $("#next_page").hide();

            }

        }else{

            $("#next_page").hide();
        }


    },


    //获取参数

    categoryDetail: function (categoryId) {

        $.getJSON(CategoryOperation.listCategoryDetailUrl+categoryId, function (result) {


            if (200 == result.code) {

                var category = result.data;

                var html = '';

                html += '<img src="'+category.avatar+' "class="me-ct-picture">';
                html += '<h3 class="me-ct-name">'+category.categoryName+'</h3>';
                html += ' <p>'+category.description+'</p>';
                html += '<span class="me-ct-meta">'+category.articles+'文章</span>';

                $("#categoryArea").html(html);

            }

        });


    },


    //判断是否还有页数
    hasPage:function(){

        if(CategoryOperation.current >= CategoryOperation.pages || CategoryOperation.pageNo >= CategoryOperation.pages){

            return false;

        }
        return true;


    }











};


