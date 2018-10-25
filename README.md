统计分析说明
======

通过对A站火车货运数据进行分析，将数据在四个维度进行货运量统计分析，分别为：时间、货物类别、运输方式以及货运区域。

一、时间维度

1 每天货运量分析：每天货运总量，每天收货量，每天发货量

2 每月货运量分析：每月货运总量，每月收货量，每月发货量

3 总货运量分析：总货运量，总收货量，总发货量

二、货物类别维度

1 每类货物货运量：A站每类货物收货量以及发货量总和

2 每类货物收货量：A站每类货物收货量，即每类货物发送至A站的货运量

3 每类货物发货量：A站每类货物发货量，即每类货物自A站发送的货运量

三、运输方式维度

1 每种运输方式货运量：A站货物通过每种运输方式的货运量

2 每种运输方式收货量：A站货物通过每种运输方式的收货量，即通过每种运输方式发送至A站的货运量

3 每种运输方式发货量：A站货物通过每种运输方式的发货量，即通过每种运输方式由A站发送的货运量

四、货运区域维度

1 各区域货运量：与A站进行货运的各区域的收货量以及发货量总和

2 各区域收货量：各区域接受自A站发送的货物货运量

3 各区域发货量：各区域发送至A站的货物货运量

可视化展示界面
======

可视化展示界面为由Eclipse开发的Web页面，使用Echarts框架将保存在mysql数据库中的分析结果通过各种图表进行可视化展示。运行该系统，打开http://localhost:8080/Echart/ 链接即可看到可视化的界面，具体图表如下。

1、A站总收发货量对比

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%871.png)

2、A站每月总货运量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%872.png)

3、A站每月总收货量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%873.png)

4、A站每月总发货量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%874.png)

5、A站每天总货运量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%875.png)

6、A站每天总收货量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%876.png)

7、A站每天总发货量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%877.png)

8、A站每类货物货运量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%878.png)

9、A站每类货物货运比例

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%879.png)

10、A站每类货物收货量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8710.png)

11、A站每类货物收货量比例

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8711.png)

12、A站每类货物发货量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8712.png)

13、A站每类货物发货量比例

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8713.png)

14、A站每种运输方式货运量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8714.png)

15、A站每种运输方式货运比例

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8715.png)

16、A站每种运输方式收货量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8716.png)

17、A站每种运输方式收货量比例

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8717.png)

18、A站每种运输方式发货量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8718.png)

19、A站每种运输方式发货量比例

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8719.png)

20、Top10区域货运量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8720.png)

21、Top10区域收货量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8721.png)

22、Top10区域发货量

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8722.png)

23、区域货运量热力图

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8723.png)

24、区域收货量热力图

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8724.png)

25、区域发货量热力图

![Image text](https://github.com/chinaup/FreightVolumeAnalyze/blob/master/picture/%E5%9B%BE%E7%89%8725.png)
















