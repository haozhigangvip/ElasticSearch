<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
	<script src="js/jquery-3.1.1.min.js"></script>
</head>
 
<style>
    .sel {
        height: 25px;
        width: 150px;
        margin: 10px;
        padding: 0px;
    }
 
    .inp {
        height: 20px; /*attention*/
        width: 145px; /*attention*/
        margin: 10px;
        padding: 0px;
    }
</style>
<body>
 
<select class="sel" οnkeypress="editAbleSelect.start(this,event)">
    <option value=""></option>
    <option value="m">张三</option>
    <option value="cm">李四</option>
    <option value="mm">王五</option>
</select>
<input class="inp" type="text" style="display: none;" οnblur="editAbleSelect.end(this,event)"/>
 
</body>
 
<script type="text/javascript">
    /**
     * <select/>å¿é¡»æåå¨<input/>ä¹å
     * <select/> è³å°æä¸ä¸ªoption
     * å»ºè®®ç¬¬ä¸ä¸ªoptionä¸º <option value=""></option>
     */
    var editAbleSelect = {
        start: function (obj, e) {//åè½¦äºä»¶
            if ((e.keyCode || e.which || e.charCode) == 13) {
                var select = $(obj);
                var input = select.next();
                var value = select.val();
 
                input.val(value);
                select.hide();
                input.show();
                input.focus();
            }
        },
        end: function (obj, e) {//å¤±ç¦ç¹äºä»¶
            var input = $(obj);//dom â jdom
            var select = input.prev();
            var value = input.val();
 
            var option = select.children(":first");
            option.attr("selected", true);
            option.text(value);
            option.val(value);
 
            select.show();
            input.hide();
        }
    };
 
</script>
