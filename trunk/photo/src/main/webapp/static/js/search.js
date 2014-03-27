$(function() {
	function post(){
		var ols="";
		var searchword=$("#search").val();
		$.ajax({type: "post",
			url: "suggestwordsdb.action", 
			data: ({searchword: searchword}),
			dataType: "json",
			success: function(data){
				$("#description").remove(); 
				var words=data.suggestwords;
				var i;
				if (words.length!=0) {
					for(i=0;i<words.length;i++){
						ols=ols+"<li  id='suggestwordli'>"+words[i]+"</li>";
					}
					var description = "<div  align='center' style='background-color: white;width:153px ' id='description'><ul>"+ols+"</ul><div>"; // 创建ol元素
					$("body").append(description); // 把它追加到文档中
					$("#description").css({
						"position" : "absolute",
						"top" : "55px",
						"left" : "502px"
					}).show("fast");
					}
				}
			});
		}
		$("#search").live("keyup",function(e) {
			var searchword=$("#search").val();
			if (searchword!=' ') {
				setTimeout =(post(),1000);
			}
		});
		$("body").keydown(function(e) {
			if (e.keyCode=='0xD') {
				$("#description").remove(); // 移除
			}
		});
	});
