
var IndexOperation = {

    //查询所有的标签
    listTagUrl:"/tags/detail",


    onPageLoad: function () {

        //获取所有标签
        $.getJSON(IndexOperation.listTagUrl, function (result) {


            if (200 == result.code) {

                var data = result.data;

                var html = '';


                $.each(data, function (index, tag) {

                    html += '<li class="me-allct-item">';
                    html += '<div class="me-allct-content" onclick="IndexOperation.gotoViewPage('+tag.tagId+')">';
                    html += '<a class="me-allct-info">';
                    html += '<img src="'+tag.avatar+'" class="me-allct-img">';
                    html += '<h4 class="me-allct-name">'+tag.tagName+'</h4>';
                    html += '</a>';
                    html += '<div class="me-allct-meta">';
                    html += '<span>'+tag.articles+'文章</span></div>';
                    html += '</div>';
                    html += '</li>';

                });

                $(".me-allct-items").html(html);

            }


        })
    },

    //跳转
    gotoViewPage: function (tagId) {

        window.location.href = "/view/tag/view?tagId="+tagId;

    }


};


$(function () {

    IndexOperation.onPageLoad();


});