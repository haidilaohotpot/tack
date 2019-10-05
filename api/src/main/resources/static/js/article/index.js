
var IndexOperation = {

    //获取文章的URL
    listArticleUrl:"/article/list",

    //获取最热标签的URL
    listHotTagUrl:"/tags/hot",

    //获取最热文章的URL

    listHotArticleUrl:"/article/hot",

    //获取最新文章的URL

    listNewArticleUrl:"/article/new",


    //获取文章归档日期的URL

    listArchivesUrl:"/article/archives",


    current:1,

    pages:1,

    pageNo:1,


    loadPages:function(url){

        var pageData = {};

        pageData.pageNo=IndexOperation.pageNo;

        //获取文章
        $.getJSON(url, pageData,function (result) {

            // console.log(result);
            if (200 == result.code) {

                var data = result.data;

                var page = result.page;

                IndexOperation.pages = parseInt(page.pages);


                IndexOperation.current = parseInt(page.current);


                if(IndexOperation.hasPage()){

                    var loadHtml = '<button type="button" class="btn btn-primary btn-lg btn-block" id="next_page" onclick="IndexOperation.onClinkNextPage();" >加载更多</button>';
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



    //当页面加载是调用
    onPageLoad: function () {

        //加载文章
        IndexOperation.loadPages(IndexOperation.listArticleUrl);

        //获取热门标签

        $.getJSON(IndexOperation.listHotTagUrl, function (result) {


            if (200 == result.code) {

                var data = result.data;

                var html = '';

                $.each(data, function (index, tag) {

                    html += '<li data-v-ccb4f3bc="" class="me-tag-item">';
                    html += '<button data-v-ccb4f3bc="" type="button" class="el-button el-button--primary el-button--mini is-plain is-round" onclick="IndexOperation.gotoViewPage('+tag.tagId+')">';
                    html += '<span>' + tag.tagName + '</span>';
                    html += '</button>';
                    html += '</li>';

                });

                $(".me-tag-list").html(html);

            }

            // todo


        });


        //获取最热文章

        $.getJSON(IndexOperation.listHotArticleUrl, function (result) {


            if (200 == result.code) {

                var data = result.data;

                var html = '';

                $.each(data, function (index, article) {

                    html += '<li data-v-f5446920="" class="me-category-item">';
                    html += '<a data-v-f5446920="" onclick="common.gotoArticleView('+article.id+')">' + article.title + '</a>';
                    html += '</li>';

                });

                $("#hot_article").html(html);

            }

            // todo

        });

        //获取文章归档日期

        $.getJSON(IndexOperation.listArchivesUrl, function (result) {


            if (200 == result.code) {

                var data = result.data;

                var html = '';

                $.each(data, function (index, archive) {

                    html += '<li data-v-275373eb="" class="me-category-item"><a data-v-275373eb="" onclick="IndexOperation.gotoArchivesPage('+archive.year+','+archive.month+')">'+archive.year+'年'+archive.month+'月</a></li>';
                });

                $("#article_archives").html(html);

            }

            // todo

        });



        //获取最新文章

        $.getJSON(IndexOperation.listNewArticleUrl, function (result) {


            if (200 == result.code) {

                var data = result.data;

                var html = '';

                $.each(data, function (index, article) {

                    html += '<li data-v-f5446920="" class="me-category-item"><a data-v-f5446920="" onclick="common.gotoArticleView('+article.id+')">'+article.title+'</a></li>';

                });

                $("#new_article").html(html);

            }

            // todo

        });


    },


    //点击加载更多

    onClinkNextPage: function () {


        if(IndexOperation.current < IndexOperation.pages){

            IndexOperation.pageNo++;

            IndexOperation.loadPages(IndexOperation.listArticleUrl);

            if(IndexOperation.current >= IndexOperation.pages || IndexOperation.pageNo >= IndexOperation.pages){

                $("#next_page").hide();

            }

        }else{

            $("#next_page").hide();
        }


    },

    //判断是否还有页数
    hasPage:function(){

        return IndexOperation.current < IndexOperation.pages;

    },


    //跳转
    gotoViewPage: function (tagId) {

        window.location.href = "/view/tag/view?tagId="+tagId;

    },
    //跳转
    gotoArchivesPage: function (year,month) {

        window.location.href = "/view/article/archive?year="+year+"&month="+month;

    },








};




