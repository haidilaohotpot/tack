var ArchivesOperation = {

    listArchivesUrl:"/article/archives",

    //根据年月日获取文章
    listArticleByYearAndMonthUrl:"/article/list",


    onPageLoad: function () {

        //获取归档日期信息
        $.getJSON(ArchivesOperation.listArchivesUrl, function (result) {


            if (200 == result.code) {

                var data = result.data;

                var html = '';

                $.each(data, function (index, archive) {

                    html += '<li data-v-dba35d6c="" class="me-month-item">';
                    html += '<div data-v-dba35d6c="" class="el-badge">';
                    html += '<button data-v-dba35d6c="" type="button" class="el-button el-button--default el-button--small" onclick="ArchivesOperation.getArticleByYearAndMonth('+archive.year+','+archive.month+')">';
                    html += '<span>'+archive.year+'年'+archive.month+'月</span></button>';
                    html += '<sup class="el-badge__content is-fixed">'+archive.count+'</sup></div>';
                    html += '</li>';

                });

                $(".me-month-list").html(html);

            }

            // todo

        });

    },

    current:1,

    pages:1,

    pageNo:1,


    loadPages:function(year,month) {

        var pageData = {};

        pageData.pageNo = ArchivesOperation.pageNo;

        //获取文章
        $.getJSON(ArchivesOperation.listArticleByYearAndMonthUrl+"?year="+year+"&month="+month, pageData, function (result) {

            // console.log(result);
            if (200 == result.code) {

                var data = result.data;

                var page = result.page;

                ArchivesOperation.pages = parseInt(page.pages);


                ArchivesOperation.current = parseInt(page.current);


                if (ArchivesOperation.hasPage()) {

                    var loadHtml = '<button type="button" class="btn btn-primary btn-lg btn-block" id="next_page" onclick="ArchivesOperation.onClinkNextPage('+year+','+month+');" >加载更多</button>';
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


    //根据年月日获取
    getArticleByYearAndMonth: function (year,month) {


        $("#scroll-page").html(" ");
        $("#load").html(" ");
        ArchivesOperation.current=1;
        ArchivesOperation.pageNo=1;
        ArchivesOperation.pages=1;

        // alert(year + 'and '+month);
        ArchivesOperation.loadPages(year,month);


    },

    //点击加载更多

    onClinkNextPage: function (year,month) {


        if(ArchivesOperation.current < ArchivesOperation.pages){

            ArchivesOperation.pageNo++;

            ArchivesOperation.loadPages(year,month);

            if(ArchivesOperation.current >= ArchivesOperation.pages || ArchivesOperation.pageNo >= ArchivesOperation.pages){

                $("#next_page").hide();

            }

        }else{

            $("#next_page").hide();
        }


    },

    //判断是否还有页数
    hasPage:function(){

        return ArchivesOperation.current < ArchivesOperation.pages;

    },




};


$(function () {

    var year  = common.getQueryVariable("year");

    var month = common.getQueryVariable("month");
    if(year==false||year==""||month==false||month==""){

        IndexOperation.loadPages(ArchivesOperation.listArticleByYearAndMonthUrl);
    }else{

        ArchivesOperation.getArticleByYearAndMonth(year, month);
    }

    ArchivesOperation.onPageLoad();


});