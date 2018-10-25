<%@ page language="java" import="FreightVolume.connDB,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<String[]> list = connDB.everyProvinceTakeVolume();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ECharts 可视化分析火车货运</title>
<link href="./css/style.css" type='text/css' rel="stylesheet"/>
<script src="./js/echarts.min.js"></script>
<script src="./js/china.js"></script>
</head>
<body>
	<div class='header'> 
        <p>ECharts 可视化分析火车货运</p>
    </div>
    <div class="content">
        <div class="nav">
            <ul>
                <li><a href="./index.jsp">A站总收发货量对比</a></li>
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
                <li class="current"><a href="#">区域收货量热力图</a></li>
                <li><a href="./index24.jsp">区域发货量热力图</a></li>
            </ul>
        </div>
        <div class="container">
            <div class="title">区域收货量热力图</div>
            <div class="show">
                <div class='chart-type'>热力图</div>
                <div id="main"></div>
            </div>
        </div>
    </div>
<script>
//基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
// 指定图表的配置项和数据

option = {
	    tooltip: {},
	    visualMap: {
	        min: 0,
	        max: 450000000,
	        text:['High','Low'],
	        realtime: false,
	        calculable: true,
	        inRange: {
	            color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
	        }
	    },
	    geo: {
	        map: 'china',
	        roam: true,
	        label: {
	            normal: {
	                show: true,
	                textStyle: {
	                    color: 'rgba(0,0,0,0.4)'
	                }
	            }
	        },
	        itemStyle: {
	            normal:{
	                borderColor: 'rgba(0, 0, 0, 0.2)'
	            },
	            emphasis:{
	                areaColor: null,
	                shadowOffsetX: 0,
	                shadowOffsetY: 0,
	                shadowBlur: 20,
	                borderWidth: 0,
	                shadowColor: 'rgba(0, 0, 0, 0.5)'
	            }
	        }
	    },
	    series : [
	        {
	            name: '收货量',
	            type: 'map',
	            roam: true,
	            coordinateSystem: 'geo',
	            geoIndex: 0,
	            // tooltip: {show: false},
	            label: {
	                normal: {
	                    formatter: '{b}',
	                    position: 'right',
	                    show: false
	                },
	                emphasis: {
	                    show: true
	                }
	            },
	            itemStyle: {
	                normal: {
	                    color: '#F06C00'
	                }
	            },
	            data:[
	            	{name: '重庆', value: <%=list.get(0)[1]%>},
	            	{name: '云南', value: <%=list.get(1)[1]%>},
	            	{name: '新疆', value: <%=list.get(2)[1]%>},
	            	{name: '贵州', value: <%=list.get(3)[1]%>},
	            	{name: '四川', value: <%=list.get(4)[1]%>},
	            	{name: '上海', value: <%=list.get(5)[1]%>},
	            	{name: '广东', value: <%=list.get(6)[1]%>},
	            	{name: '江苏', value: <%=list.get(7)[1]%>},
	            	{name: '北京', value: <%=list.get(8)[1]%>},
	            	{name: '浙江', value: <%=list.get(9)[1]%>},
	            	{name: '河北', value: <%=list.get(10)[1]%>},
	            	{name: '陕西', value: <%=list.get(11)[1]%>},
	            	{name: '安徽', value: <%=list.get(12)[1]%>},
	            	{name: '湖北', value: <%=list.get(14)[1]%>},
	            	{name: '山东', value: <%=list.get(15)[1]%>},
	            	{name: '西藏', value: <%=list.get(16)[1]%>},
	            	{name: '河南', value: <%=list.get(17)[1]%>},
	            	{name: '内蒙古', value: <%=list.get(18)[1]%>},
	            	{name: '海南', value: <%=list.get(19)[1]%>},
	            	{name: '甘肃', value: <%=list.get(20)[1]%>},
	            	{name: '广西', value: <%=list.get(21)[1]%>},
	            	{name: '天津', value: <%=list.get(22)[1]%>},
	            	{name: '福建', value: <%=list.get(23)[1]%>},
	            	{name: '江西', value: <%=list.get(24)[1]%>},
	            	{name: '黑龙江', value: <%=list.get(25)[1]%>},
	            	{name: '辽宁', value: <%=list.get(26)[1]%>},
	            	{name: '宁夏', value: <%=list.get(27)[1]%>},
	            	{name: '山西', value: <%=list.get(28)[1]%>},
	            	{name: '吉林', value: <%=list.get(29)[1]%>},       	
	            ]
	        }
	    ]
	};
	var chart=echarts.init(document.getElementById("main"))
	chart.setOption(option)
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
</script>
</body>
</html>