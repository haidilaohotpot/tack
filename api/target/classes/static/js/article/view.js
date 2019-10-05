
var ArticleOperation = {


    //获取文章
    getArticleUrl:"/article/view",



    //获取文章
    getArticleById: function (articleId) {



        $.getJSON(ArticleOperation.getArticleUrl + '/' + articleId, function (result) {



            if (result.code == 200) {

                // console.log(result);
                var html = "";

                var data = result.data;

                $("title").html(data.title);
                html += '<div class="me-view-card">';
                html += '<h1 class="me-view-title">'+data.title+'</h1>';
                html += '<div class="me-view-author">';
                html += '<div class="me-view-info">';
                html += '<a><span>'+data.userName+'</span></a>';
                html += '<div class="me-view-meta">';
                html += '<span>'+data.createTime+' </span>';
                html += '<span> 阅读 '+data.viewNum+'</span>';
                html += '</div>';
                html += '</div>';
                html += '</div>';
                html += '<div class="me-view-content">';
                html += '<div data-v-7a63e4b3="" data-v-39621e65="" class="v-note-wrapper markdown-body me-editor" value="">';
                html += '<div data-v-7a63e4b3="" class="v-note-panel shadow">';
                html += '<div data-v-7a63e4b3="" class="v-note-show single-show">';
                html += '<div data-v-7a63e4b3="" class="v-show-content scroll-style">';
                html +=  data.contentHtml;
                html += '</div>';
                html += '<div data-v-7a63e4b3="" class="v-show-content-html scroll-style" style="display: none;">基础知识</div></div>';
                html += '<div data-v-7a63e4b3="" class="v-note-navigation-wrapper shadow" style="display: none;">';
                html += '<div data-v-7a63e4b3="" class="v-note-navigation-title shadow">导航目录';
                html += '<i data-v-7a63e4b3="" aria-hidden="true" class="fa fa-mavon-times v-note-navigation-close"></i></div>';
                html += '<div data-v-7a63e4b3="" class="v-note-navigation-content scroll-style"></div>';
                html += '</div></div>';
                html += '</div>';
                html += '</div>';
                html += '<div class="me-view-end">';
                html += '<div role="alert" class="el-alert el-alert--success is-center">';
                html += '<div class="el-alert__content">';
                html += '<span class="el-alert__title">The End</span>';
                html += '<i class="el-alert__closebtn el-icon-close" style="display: none;"></i>';
                html += '</div>';
                html += '</div>';
                html += '</div>';

                var tags = data.tags;

                html += '<div class="me-view-tag">标签：';
                $.each(tags, function (index, tag) {

                    html += '<button type="button" class="el-button el-button--primary el-button--mini is-plain is-round" onclick="ArticleOperation.gotoTagView('+tag.tagId+')">';
                    html += '<span>'+tag.tagName+'</span></button>';

                });



                var category = data.category;
                html += '</div>';
                html += '<div class="me-view-tag">文章分类：';
                html += '<button type="button" class="el-button el-button--primary el-button--mini is-plain is-round" onclick="ArticleOperation.gotoCategoryView('+category.categoryId+')">';
                html += '<span>'+category.categoryName+'</span></button>';
                html += '</div>';
                html += '</div>';

                $(".el-main").html(html);
            }

            // todo



        });



    },


    //去标签页面

    gotoTagView: function (tagId) {

        window.location.href = "/view/tag/view?tagId="+tagId;

    },


    //去分类页面
    gotoCategoryView: function (categoryId) {

        window.location.href = "/view/category/view?categoryId="+categoryId;

    },








};

