var LogOperation = {

    listLogUrl:"/log/list",


    onPageLoad: function () {

        //获取日志信息
        $.getJSON(LogOperation.listLogUrl, function (result) {


            if (200 == result.code) {

                var data = result.data;

                var html = '';

                $.each(data, function (index, log) {

                    html += '<div class="list-group">';
                    html += '<a href="#" class="list-group-item active">';
                    html += '<h4 class="list-group-item-heading">'+log.createTime+'</h4>';
                    html += '<p class="list-group-item-text">'+log.operation+'</p>';
                    html += '</a>';
                    html += '</div>';


                });

                $("#log_context").html(html);

            }

            // todo

        });

    }



};


$(function () {


    LogOperation.onPageLoad();


});