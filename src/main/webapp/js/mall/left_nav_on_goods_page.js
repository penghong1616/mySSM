/**
 * Created by alone on 2017/5/14.
 */
$(function () {
    insertShopCar();
    var sortCid=0;
    selectByCounts(0,0);
    var type_list = getTypeList();
    $(window).scroll(function () {
        var temp = $(this).scrollTop();
        if (temp > 100) {
            $('.my_type_div').css({"margin-top": "8%"});
            $('.particular_type_div').css({"margin-top": "8%"});
        } else {
            $('.my_type_div').css({"margin-top": "15%"});
            $('.particular_type_div').css({"margin-top": "15%"});
        }
    });
    $('.my_type_div ul li').hover(function () {
        var temp_class = $(this).attr("class");
        if (temp_class == 'type_1') {
            addList(0);
        } else if (temp_class == 'type_2') {
            addList(1);
        } else if (temp_class == 'type_3') {
            addList(2);
        } else if (temp_class == 'type_4') {
            addList(3);
        } else if (temp_class == 'type_5') {
            addList(4);
        } else if (temp_class == 'type_6') {
            addList(5);
        }
        function addList(id) {
            var which = type_list[id];
            var my_string = "";
            for (j = 0; j < which.length; j++) {
                var type_i = which[j];
                var arr = type_i.content;
                var a_list = "";
                for (i = 0; i < arr.length; i++) {
                    a_list += "<a id = '" + arr[i].id + "' class='shop_sort'>" + arr[i].name + "</a>";
                }
                my_string += "<div class='one_part'><div class='type_title_div'>" +
                    "<span class='type_border_span'>1</span><h3>" + type_i.name + "</h3></div><div " +
                    "class='type_goods_list'>" + a_list + "</div></div>";
            }
            $('.particular_type_div').html(my_string);
            //  点击事件
            $('.type_goods_list a.shop_sort').click(function () {
                var wsk = $(this).attr('id');
                selectByCounts(wsk,0);

            })
        }

        $('.particular_type_div').show(0);
    });
    $('header').click(function () {
        hideParticular();
    });
    $('.detail_product_name').click(function () {
        var id = $(this).attr('value');
        window.location.href='selectById.do?id='+id;
    });
    function insertShopCar() {
        $('.detail_buy').click(function () {
            var id = $(this).attr('value');
            $.ajax({
                url:'insertGoodsCar.do',
                dataType:'JSON',
                type:'post',
                data:{id:id},
                success:function (data) {
                    var result = data.result;
                    if (result == '2'){
                        alert('您还未登录，请先登录！！！');
                    } else if (result == '1'){
                        alert('加入购物车成功');
                    } else if (result == '0'){
                        alert('加入购物车失败');
                    } else {
                        alert('发生了错误，请检测网络');
                    }
                }
            })
        });
    }
    function selectByCounts(cId,pageNum) {
        var $all_product = $('.all_product');
        var page_ul=$('.page_ul');<!--变量分页导航栏-->
        $.ajax({
            url: 'selectByPageNum.do',
            type: 'post',
            dataType: 'JSON',
            data: {cId:cId,pageNum:pageNum},
            success: function (pageInfo) {
                var data=pageInfo.list;
                $all_product.html('');
                page_ul.html('');
                if (data.length === 0) {
                    $all_product.append("<div class='product_content_div' >" +
                        "<div class='detail_product'>" +
                        "<input type='hidden' value= ''/>" +
                        "<div class='product_img_div'><img src='' title='暂时没有该分类的商品' /></div>" +
                        "<span class='detail_product_name'></span><br/>" +
                        "<span class='detail_product_cost'></span><br/>" +
                        "<span class='detail_buy product_1'>加入购物车</span>" +
                        "</div>" +
                        "</div>");
                }
                for (var i = 0; i < data.length; i++) {
                    $all_product.append("<div class='product_content_div'>" +
                        "<div class='detail_product'>" +
                        "<input type='hidden' value=" + data[i].id + " '/>" +
                        "<div class='product_img_div'><img class='show_img' src='" + data[i].image + "' title=" + data.name + "'/></div>" +
                        "<p class='show_tip'>"+data[i].remark+"</p>"+
                        "<span class='detail_product_name' value='"+data[i].id+"'>" + data[i].name + "</span><br/>" +
                        "<span class='detail_product_cost'>￥" + data[i].price + "</span><br/>" +
                        "<span class='detail_buy product_1' value='"+data[i].id+"'>加入购物车</span>" +
                        "</div>" +
                        "</div>");
                }
                <!--设置循环导航页-->
                if(pageInfo.hasPreviousPage) {
                    page_ul.append("<li class='page'  id='"+pageInfo.prePage+"'>上一页</li>");
                }else {
                    page_ul.append("<li>没有上一页</a></li>");
                }
                page_ul.append("<li class=\"current_page\"><a >"+pageInfo.pageNum+"</a></li>");
                if(pageInfo.hasNextPage){
                    page_ul.append("<li class='page' id='"+pageInfo.nextPage+"'>下一页</li>");
                }else{
                    page_ul.append("<li>没有下一页</li>");
                }

                //进入查看商品的详情,通过id
                $('.detail_product_name').click(function () {
                    var id = $(this).attr('value');
                    window.location.href='/selectById.do?id='+id;
                });
                //为分类标签和分页栏添加点击事件
                $('shop_sort').click(function () {
                    var wsk = $(this).attr('id');
                    sortCid=wsk;
                    selectByCounts(wsk,0);
                });
                $('.page ').click(function (){
                    var pageNum=$(this).attr('id');
                    selectByCounts(sortCid,pageNum);

                } );
                insertShopCar();
            }
        });

    }
});
function hideParticular() {
    if ($('.particular_type_div').is(":visible")) {
        $('.particular_type_div').hide(0);
    }
}