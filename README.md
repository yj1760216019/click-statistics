# click-statistics
redis+quartz实现高频率数据统计



```
利用redis，基于天的维度实现商品的pv、uv、兑换次数、交易成功量的统计
利用redis，基于天的维度实现banner图的uv、pv统计

使用redis统计的好处是减少数据库的访问压力，使用quartz定时更新到数据库
```
