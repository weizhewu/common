# common
用于存放常用工具类

## bean
### result
统一接口返回格式
## utils

### content
#### CompressUtils
**String compressWithGzip(String originContent)** 使用gzip对文本进行压缩

**String unCompressWithGzip(String compressedContent)** 对使用gzip压缩的文本进行解压缩
#### HtmlVueUtils
用于对Html、Vue格式内容的处理

**Map<String,Integer> getStartAndEndIndex(String content, String tag, String keyWords):** 获取content中，指定标签的开始位置与结束位置

**List<String> getContent(String content, String tag, String keyWords):** 获取content中，指定标签范围内的内容

### date
#### DateUtils
**Date localDateToDate(LocalDate localDate):** LocalDate转Date

**Date strToYmd(String dateTimeStr):** 时间字符串转指定日期格式：yyyy-MM-dd


### excel
#### ExcelExcel
EasyExcel工具

**List<T> getList(MultipartFile file, Class<T> clazz):** 根据excel，获取对应的List<T>

**List<T> getList(MultipartFile file, Class<T> clazz, Integer sheetNo, Integer headRowNumber):** 根据excel，获取对应的List<T>

**void setInitValueToList(Object filedValue, Integer rowIndex, Integer columnIndex, List<T> data):** 设置合并单元格的值
