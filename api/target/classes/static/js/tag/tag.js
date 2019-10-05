var TagOperation = {

    //根据tag 查询文章
    listArticlesByTagIdUrl:"/article/list",

    listTagDetailUrl:"/tags/detail/",

    pageNo:1,

    tagId:0,

    loadPages:function(tagId){

        var pageData = {};

        pageData.pageNo=TagOperation.pageNo;
        pageData.tagId=tagId;

        //获取文章
        $.getJSON(TagOperation.listArticlesByTagIdUrl, pageData,function (result) {

            // console.log(result);
            if (200 == result.code) {

                var data = result.data;


                if(result.page.hasPage){

                    var loadHtml = '<button type="button" class="btn btn-primary btn-lg btn-block" id="next_page" onclick="TagOperation.onClinkNextPage();" >加载更多</button>';
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

    //根据标签id查询文章

    findArticlesByTagId: function (tagId) {

        TagOperation.tagId =  tagId;

        TagOperation.loadPages(tagId);

    },


    //点击加载更多

    onClinkNextPage: function () {


        TagOperation.pageNo++;

        TagOperation.loadPages(TagOperation.tagId);



    },


    //获取参数

    tagDetail: function (tagId) {

        $.getJSON(TagOperation.listTagDetailUrl+tagId, function (result) {


            if (200 == result.code) {

                var tag = result.data;

                var html = '';

                html += '<img src="'+tag.avatar+' "class="me-ct-picture">';
                html += '<h3 class="me-ct-name">'+tag.tagName+'</h3>';
                html += '<span class="me-ct-meta">'+tag.articles+'文章</span>';

                $("#tagArea").html(html);

            }

        });


    }











};


