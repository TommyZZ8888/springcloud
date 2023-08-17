# 开发手册

## 程序方面

- 金钱类使用Money字段
- 枚举类可直接使用(无需转化) 但是必须要继承```BaseEnumsInterface```
- 使用Mapper.xml与数据库交互
- feign直接使用```ResponseResult<JavaBean>```来接受,如：

```java
@RequestMapping(value = "/test/test1", method = RequestMethod.GET)
ResponseResult<List<TestEntity>>getTest();
```

- 所有控制器需要继承BaseController
- 创建时间和更新时间可以在Mapper.xml的sql中用```sysdate()``` / ```now()```来填充
- ```@EnableUserInfo```开启```UserInfoEntity```的根据```@UserInfo```中的```field```自动填充序列化

## 数据库方面

- 枚举类型使用char(1) 对应的JavaType为String类型
- 主键ID使用的是UUID(无制表符的char(32)) ```replace(uuid(),'-','')```
- 主键命令使用表前缀 eg: table_name(log) => primary_key(log_id)
- 所有单查询语句必须添加```limit 1```的操作
- 禁止全表扫描
- 适当建立索引,在链表查询时,关联字段尽量都是索引
- 对于sql优化 可以使用explain来解析

## 工具类

### Excel导入导出

| 参数  |类型| 默认值  |    描述|
| ---- | ---- | ---- | ---- |
| sort    |int|    Integer.MAX_VALUE |导出时在excel中排序，值越小越靠前|
| name    |String| 空 导出到Excel中的名字|
| dateFormat|    String| 空| 日期格式, 如: yyyy-MM-dd|
| readConverterExp    |String| 空 读取内容转表达式 (如: 0=男,1=女,2=未知)|
| separator    |String |,| 分隔符，读取字符串组内容|
| scale    |int |-1|    BigDecimal 精度 默认:-1(默认不开启BigDecimal格式化)|
| roundingMode|    int|    BigDecimal.ROUND_HALF_EVEN|    BigDecimal 舍入规则 默认:BigDecimal.ROUND_HALF_EVEN|
| celltype|    Enum|    Type.STRING |导出类型（0数字 1字符串 2图片）|
| height|    String|    14 |导出时在excel中每个列的高度 单位为字符|
| width|    String|    16 |导出时在excel中每个列的宽 单位为字符|
| suffix|    String| 空| 文字后缀,如% 90 变成90%|
| defaultValue|    String| 空 |当值为空时,字段的默认值|
| prompt|    String| 空 |提示信息|
| combo|    String|    Null| 设置只能选择不能输入的列内容|
| headerBackgroundColor    |Enum|    IndexedColors.GREY_50_PERCENT| 导出列头背景色IndexedColors.XXXX|
| headerColor|    Enum|    IndexedColors.WHITE |导出列头字体颜色IndexedColors.XXXX|
| backgroundColor|    Enum    |IndexedColors.WHITE| 导出单元格背景色IndexedColors.XXXX|
| color    |Enum    |IndexedColors.BLACK |导出单元格字体颜色IndexedColors.XXXX|
| targetAttr|    String| 空 |另一个类中的属性名称,支持多级获取,以小数点隔开|
| isStatistics|    boolean|    false |是否自动统计数据,在最后追加一行统计数据总和|
| type    |Enum|    Type.ALL| 字段类型（0：导出导入；1：仅导出；2：仅导入）|
| align    |Enum    |HorizontalAlignment.CENTER| 导出对齐方式HorizontalAlignment.XXXX|
| handler    |Class|    ExcelHandlerAdapter.class| 自定义数据处理器|
| args    |String[]  |  {}| 自定义数据处理器参数|

```java
/**
 * 使用方法
 */
ExcelUtil<JavaBean> excel new ExcelUtil<JavaBean>(JavaBean.class);
```

```java
/**
 * 导出部门多个对象
 * deptName 为 User中的一个属性
 * leader 为User中的一个属性
 * MyDataHandler为自定义数据处理器
 */
@Excels({
        @Excel(name = "部门名称", handler = MyDataHandler.class, targetAttr = "deptName", type = Type.EXPORT),
        @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
})
private User user;
```

```java
/**
 * 编写数据处理器MyDataHandler继承ExcelHandlerAdapter，返回值为处理后的值。
 */
public class MyDataHandler implements ExcelHandlerAdapter {
    @Override
    public Object format(Object value, String[] args) {
        // value 为单元格数据值
        // args 为excel注解args参数组
        return value;
    }
}
```

```java
import lombok.Data;

/**
 * 支持List子列表导出
 */
@Data
class A {
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "成绩")
    private List<B> list;
}

@Data
class B {
    @Excel(name = "学科")
    private String project;
    @Excel(name = "分数")
    private String score;
}
```

结果为下 会自动合并部分单元格

| 姓名 | 成绩 |      |
|----|----|--------|
|    | 学科 | 分数  |
| 张三 | 语文 | 100 |
|    | 数学 | 90 |
|    | 英语 | 95  |
| 李四 | 语文 | 100  |
|    | 数学 | 90  |
|    | 英语 | 95   |

### word模板导出

- 参考文档：http://deepoove.com/poi-tl/
- 数据填充：{{name}}
- 图片填充：{{@image}}
- 时间格式：{{new java.text.SimpleDateFormat('yyyy-MM-dd').format(date)}}
- list填充：表头{{lists}} 列表[name]、[phoneNumber]、[qq]、[email]

````java
/**
 * 对应实体
 */
class A {
    private String name;
    private PictureRenderData image;
    private Date date;
    private List<B> lists;
}

class B {
    private String name;
    private String phoneNumber;
    private String qq;
    @Email(message = "邮箱格式错误")
    private String email;
}
    /**
     *  config配置
     */
    LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
    //插件列表,可以去官网查看，有列循环，还有行循环，这里是行循环实例
    Configure config = Configure.builder()
            //在模板标签中使用SpringEL表达式
            .useSpringEL()
            .bind("lists", policy)
            .build();
````

- 工具类WordUtil.createWord参数：
    - templatePath 模板文件路径
    - fileName 导出文件名称
    - data 数据（map/Object）
    - config 模板配置

  

