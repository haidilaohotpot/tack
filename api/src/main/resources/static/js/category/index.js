
var IndexOperation = {

    //查询所有的分类
    listCategoryUrl:"/category/detail",


    onPageLoad: function () {

        //获取所有的分类
        $.getJSON(IndexOperation.listCategoryUrl, function (result) {


            if (200 == result.code) {

                var data = result.data;

                var html = '';


                $.each(data, function (index, category) {

                    html += '<li class="me-allct-item">';
                    html += '<div class="me-allct-content" onclick="IndexOperation.gotoViewPage('+category.categoryId+')">';
                    html += '<a class="me-allct-info">';
                    html += '<img src="'+category.avatar+'" class="me-allct-img">';
                    html += '<h4 class="me-allct-name">'+category.categoryName+'</h4>';
                    html += '<p class="me-allct-description">'+category.description+'</p></a>';
                    html += '<div class="me-allct-meta">';
                    html += '<span>'+category.articles+'文章</span></div>';
                    html += '</div>';
                    html += '</li>';

                });

                $(".me-allct-items").html(html);

            }


        })
    },


    //跳转
    gotoViewPage: function (categoryId) {

        window.location.href = "/view/category/view?categoryId="+categoryId;

    }





};


$(function () {


        IndexOperation.onPageLoad();


});