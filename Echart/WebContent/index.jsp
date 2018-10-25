<%@ page language="java" import="FreightVolume.connDB,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<String[]> list = connDB.totalVolume();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ECharts 可视化分析火车货运</title>
<link href="./css/style.css" type='text/css' rel="stylesheet"/>
<script src="./js/echarts.min.js"></script>
</head>
<body>
	<div class='header'>
        <p>ECharts 可视化分析火车货运</p>
    </div>
    <div class="content">
        <div class="nav">
            <ul>
                <li class="current"><a href="#">A站总收发货量对比</a></li>
                <li><a href="./index1.jsp">A站每月总货运量</a></li>
                <li><a href="./index2.jsp">A站每月总收货量</a></li>
                <li><a href="./index3.jsp">A站每月总发货量</a></li>
                <li><a href="./index4.jsp">A站每天总货运量</a></li>
                <li><a href="./index5.jsp">A站每天总收货量</a></li>
                <li><a href="./index6.jsp">A站每天总发货量</a></li>
                <li><a href="./index7.jsp">A站每类货物货运量</a></li>
                <li><a href="./index8.jsp">A站每类货物货运比例</a></li>
                <li><a href="./index9.jsp">A站每类货物收货量</a></li>
                <li><a href="./index10.jsp">A站每类货物收货量比例</a></li>
                <li><a href="./index11.jsp">A站每类货物发货量</a></li>
                <li><a href="./index12.jsp">A站每类货物发货量比例</a></li>
                <li><a href="./index13.jsp">A站每种运输方式货运量</a></li>
                <li><a href="./index14.jsp">A站每种运输方式货运比例</a></li>
                <li><a href="./index15.jsp">A站每种运输方式收货量</a></li>
                <li><a href="./index16.jsp">A站每种运输方式收货量比例</a></li>
                <li><a href="./index17.jsp">A站每种运输方式发货量</a></li>
                <li><a href="./index18.jsp">A站每种运输方式发货量比例</a></li>
                <li><a href="./index19.jsp">Top10区域货运量</a></li>
                <li><a href="./index20.jsp">Top10区域收货量</a></li>
                <li><a href="./index21.jsp">Top10区域发货量</a></li>
                <li><a href="./index22.jsp">区域货运量热力图</a></li>
                <li><a href="./index23.jsp">区域收货量热力图</a></li>
                <li><a href="./index24.jsp">区域发货量热力图</a></li>
            </ul>
        </div>
        <div class="container">
            <div class="title">A站总收发货量对比</div>
            <div class="show">
                <div class='chart-type'>饼图</div>
                <div id="main"></div>
            </div>
        </div>
    </div>
<script>
//基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
// 指定图表的配置项和数据
option = {
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name: '货运比例',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '50%'],
	            data:[
	            	{value:<%=list.get(0)[0]%>, name:'收货量'},
                    {value:<%=list.get(0)[1]%>, name:'发货量'},
	            ].sort(function (a, b) { return a.value - b.value}),
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};     

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
</script>
</body>
</html>