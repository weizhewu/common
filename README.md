# common
用于存放常用工具类

## bean
### result
统一接口返回格式
## utils
### content
#### HtmlVueUtils
用于对Html、Vue格式内容的处理

**Map<String,Integer> getStartAndEndIndex(String content, String tag, String keyWords):** 获取content中，指定标签的开始位置与结束位置

**List<String> getContent(String content, String tag, String keyWords):** 获取content中，指定标签范围内的内容