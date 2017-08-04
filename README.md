# Share-Kotlin-Utils
## 1. [Koltin 简介](https://zh.wikipedia.org/wiki/Kotlin)
    
 -   Java,Groovy,Gradle......Koltin???
 -   怎么又有新语言出来啊，简直要疯掉了
 -   有困难要上，没困难制造困难也要上。
 -   靠，这尼玛究竟是谁说的，好有道理！
 
 
   ![image](http://oa5504rxk.bkt.clouddn.com/week29_kotlin/3.png)


## 2. 为什么要使用Kotlin
### 2.1  常见代码对比

> Java

``` 
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final boolean fistClick = getSharedPreferences("", 0).getBoolean("fistClick", true);
        fab.setMinimumHeight(20);
        
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "First Click "+fistClick, Snackbar.LENGTH_LONG).show();
                getSharedPreferences("", 0).edit().putBoolean("fistClick",false).commit();
            }
        });

    }
}

``` 

> Kotlin

``` 
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var fistClick by Preference(this, "fistClick", true)

        with(fab) {
            minimumHeight = 20
            setOnClickListener {
                Snackbar.make(this, "First Click $fistClick", Snackbar.LENGTH_LONG).show()
                fistClick = false
            }
        }
    }
}
``` 

### 2.2 常见情景对比

### 2.2.1 消失的 Getter 和 Setter


``` 
public class Person{
    private int id;
    private String name;
    //瞅啥瞅，省略掉的是占用了 80% 的篇幅 getter 和 setter！
    ...
}
```

曾经试图不写 Getter 和 Setter，可作为一个写 Java 这么多年的人，没了 Getter 和 Setter 让你感觉就像是。。。
![image](http://oa5504rxk.bkt.clouddn.com/week29_kotlin/4.png)


> By Kotlin
```
data class Person(val id: Int, val name: String)
```


### 2.2.2 阿尼陀佛空空空
常见代码

```
public void setPersionName(Persion persion){
    person.setName("橘右京");
}
```
![image](http://oa5504rxk.bkt.clouddn.com/week29_kotlin/6.png)

好像没什么问题，回过神来，突然看到这行代码，这哥们名字叫 橘右京，可这哥们是谁？万一他是个 null 呢？
然后

```
public void setPersionName(Persion persion){
    if(persion!=null) person.setName("橘右京");
}

```
嗯，看起来没问题了，哪天产品说，我要给他的儿子的老婆的姐姐的...起个外号...

```
public void setPersionName(Persion persion){
   person.children.wife.sister...wtf.setName("橘右京");
}
................. or 常见的数据模型
public void onNetBack(Response result){
  result.getData().getList().get(0).getContents().get(0).getName()//这些法....心惊胆战
}
```
这时候Kotlin大神说,我不是针对谁,我是说,在座的各位都是....

```
fun setPersionName(persion Persion){//Persion 不能为空,否则编译错误,需要为空使用persion Persion?
    person.children?.wife?.sister?.mother.setName("橘右京的...妈妈")?:return;
}
```
![image](http://oa5504rxk.bkt.clouddn.com/week29_kotlin/9.png)

###2.2.3 Smart Cast

Java 有一种特别缺心眼的写法

```
if(view instanceof ViewGroup){
    ((ViewGroup) view).addView(child);
}
```
强转加方法调用，两对括号，写到手抽筋啊。可我已经告诉 Java view 是 ViewGroup 了啊，结果还是要强转，这种感觉就像我坐地铁的时候本来刷卡进站，结果到了车上，还有人查票！』

Kotlin

```
if(view is ViewGroup){
    view.addView(child) // 现在 Kotlin 已经知道 view 是 ViewGroup 类型了！
}
```
or

```
if(!(view is ViewGroup)) return
 view.addView(child) // 现在 Kotlin 已经知道 view 是 ViewGroup 类型了！
```


### 2.2.4 字符串模板
有个函数传入了三个参数，

```
void check(ArrayList<String> list, String tag, int id);
```
你想把他们的值打印一下，于是你不假思索地敲出了一行代码：

```
Log.d("list: "+list.size()+"; tag="+tag+";id="+id);
```
这样的语句并没有引起你的任何不适——毕竟，你早已习惯了它的丑陋。


Kotlin

```
Log.d("list: ${list.size}; tag=$tag; id=$id")
```

### 2.2.5 Bye，Utils

str.equals(“”) ，
!str.equals(“”)，
if(!str.equals(“”)))
```
public class StringUtils{
    public static boolean notEmpty(String str){
        return !"".equals(str);
    }
    public static boolean isEmpty(String str){
        return "".equals(str);
    }
}
```
我要是能重写一下 Java 的 String 类好了，我一定先给它加上这俩方法！！

Kotlin :可以啊,随意啊,你喜欢啊


```
fun String.notEmpty(){...}
fun String.isEmpty(){...}
```

### 2.2.6 睡吧，ButterKnife
过去  View 都是用 ButterKnife 注入的：

```
@BindView(R.id.nameView) TextView nameView;
...
nameView.setText("橘右京");

or
 TextView nameView;
 ...
    nameView = findViewById(R.id.nameView)
    nameView.setText("橘右京");
...
```
现在

```
nameView.text = "橘右京"
```
>
>
>
### 3. Kotlin使用
### 3.1 基础语法介绍
[你以为我会列出来吗？？？](https://github.com/huanglizhuo/kotlin-in-chinese)
[Think too much](http://kotlinlang.org/docs/reference/basic-syntax.html) 
> ![image](http://img1.cache.netease.com/house/2016/4/27/201604272032280c84c.jpg)

>
### 3.2 Koltin结构说明
>
### 3.2.1 修饰符
默认使用public修饰
```
>private means visible inside this class only (including all its members);
>protected — same as private + visible in subclasses too;
>internal — any client inside this module who sees the declaring class sees its internal members;
>public — any client who sees the declaring class sees its public members.
```
Examples
```
// file name: example.kt
package foo

private fun foo() {} // visible inside example.kt

public var bar: Int = 5 // property is visible everywhere
    private set         // setter is visible only in example.kt
    
internal val baz = 6    // visible inside the same module
```
>
### 3.2.2 Class
#### 3.2.2.1内部类
```
open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4  // public by default
    
    protected class Nested {
        public val e: Int = 5
    }
    Inner class Inter{
        val f = d+2;
    }
}

class Subclass : Outer() {
    init{
       a = 2
    }
    // a is not visible
    // b, c and d are visible
    // Nested and e are visible

    override val b = 5   // 'b' is protected
}

class Unrelated(o: Outer) {
    // o.a, o.b are not visible
    // o.c and o.d are visible (same module)
    // Outer.Nested is not visible, and Nested::e is not visible either 
    println("f = $Outer().Inter().f")
}
```
>

#### 3.2.2.1 匿名内部类
```
fun test(){
   viewPager.addOnPagerChangeListener(object : OnPagerChangeListener{
   //重写的方法 
   ....
   })
}
```
> 
### 3.2.2.2 Interfaces
Kotlin中,接口可以有实现
```
interface B {
    fun f() { print("B") } // interface members are 'open' by default
    fun b() { print("b") }
}

open class A {
    open fun f() { print("A") }
    fun a() { print("a") }
}
class C() : A(), B {
    // The compiler requires f() to be overridden:
    override fun f() {
        super<A>.f() // call to A.f()
        super<B>.f() // call to B.f()
    }
}
```
>
>
### 3.2.2.3 data class 数据结构类
``` 
-equals()/hashCode() pair,
-toString() of the form "User(name=John, age=42)",
-copy() function (see below).

data class User(val name: String = "", val age: Int = 0)
```
默认实现copy方法
```
val jack = User(name = "Jack", age = 1)
val olderJack = jack.copy(age = 2)
```
可以解构使用
```
val jane = User("Jane", 35) 
val (name, age) = jane
println("$name, $age years of age") // prints "Jane, 35 years of age"
```

>
### 3.2.2.4 object class 单例 (类似Java 的Utils)
例子 
> Java
```
public class CommonUtils{
   public static void getCommonString(){}
   public void setDefault(){}
}
```
> Ktolin
```
class CommonUtils{
  companion object {
        fun getCommonString(){}
    }
    
    fun setDefault(){}
}
```
------ 纯粹的Utils类
```
object CommonUtils{
     @JvmStatic //如果不加入,在Java中调用CommonUtils.companion.xxx
     fun getCommonString(){}
     ...
}

```

>
### 3.2.3 属性 Properties and Fields
> 上代码
```
class Address {
    var name: String = ...
    var street: String = ...
    var city: String = ...
    var state: String? = ...
    var zip: String = ...
}
```
> The full syntax for declaring a property is
```
var <propertyName>[: <PropertyType>] [= <property_initializer>]
    [<getter>]
    [<setter>]
```
举个栗子
```
var stringRepresentation: String
    get() = this.toString()
    set(value) {
        setDataFromString(value) // parses the string and assigns values to other properties
    }
```
>
>
### 3.2.4 Functions 函数
定义
```
fun funName(val param :String = ""):Unit{
... body
}

funName()
funName("newName")
funName(param = "newName")
```
>支持表达式
```
fun double(x: Int): Int = x * 2
fun double2(x: Int): Int = double(x)+2
```
> 可变参数
```
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}

asList(1,2,3)
```
> 嵌套函数
```
fun dfs(graph: Graph) {
    fun dfs(current: Vertex, visited: Set<Vertex>) {
        if (!visited.add(current)) return
        for (v in current.neighbors)
            dfs(v, visited)
    }

    dfs(graph.vertices[0], HashSet())
}
```

> Package Level Fun
```
//Example.kt
fun test(){}

//Test.kt
class Test{
    fun main(){
        test()
    }
}

```

> 拓展函数
....省


>
>
### 3.2.5 Collections 集合类
通用创建方式 xxxOf(...items)
```
val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
val readOnlyView: List<Int> = numbers
println(numbers)        // prints "[1, 2, 3]"
numbers.add(4)
println(readOnlyView)   // prints "[1, 2, 3, 4]"
readOnlyView.clear()    // -> does not compile

val strings = hashSetOf("a", "b", "c", "c")
assert(strings.size == 3)
```
Collections结合Lambda提供了很多方便的Api
```
   arrayListOf(1, 2,3,4)
                .filter { it == 2 }
                .map { it to it + 2 }
                .subList(0, 1)
                .firstOrNull { it.second == 4 }
                ?.second ?: return.toString().log()
```




>
>
### 3.3 [Kotlin进阶函数的使用](http://www.jianshu.com/p/03db2203e0f2)
### 3.3.1 TODO
    代码运行到这回抛未实现的异常，提醒你这边还未做!
    
```
fun init(){
    TODO("还没有实现!")
}

 FATAL EXCEPTION: main
 kotlin.NotImplementedError: An operation is not implemented: 还没有实现!
 at com.xxx.xxx.ui.TestActivity.init(TestActivity.kt:56)
 at com.xxx.xxx.ui.TestActivity.initialize(TestActivity.kt:52)
 at com.xxx.xxx.view.KActivity.onCreate(KActivity.kt:47)
 ...
```

### 3.3.2 apply,with,let...
    
```
//使用apply
val textView = TextView(this@TestActivity).apply {
    textSize = sp(10f).toFloat()
    textColor = Color.BLUE
    leftDrawable(R.drawable.icon_user,10)
    ....
}

//等同于下面代码
val textView1 = TextView(this@TestActivity)
textView1.textSize =  sp(10f).toFloat()
textView1.textColor = Color.BLUE
textView1.leftDrawable(R.drawable.icon_user,10)
textView1....

//也等同于
with( textView1 ) {
    textSize = sp(10f).toFloat()
    textColor = Color.BLUE
    leftDrawable(R.drawable.icon_user,10)
    ....
}
//等同于,但是返回了drawable
val drawable = textView1.let {
    textSize = sp(10f).toFloat()
    textColor = Color.BLUE
    leftDrawable(R.drawable.icon_user,10)
    ....
    textView1.drawable
}
```

### 3.3.3 lazy

```
fun <T> lazy(initializer: () -> T): Lazy<T>

fun <T> lazy( mode: LazyThreadSafetyMode, initializer: () -> T): Lazy<T>

fun <T> lazy(lock: Any?, initializer: () -> T): Lazy<T>

```
函数定义如上，可以看出这个方法就是得到延迟初始化对象，前面的参数线程同步有关,使用方法 如下:

```
//得到lazy对象
val init = lazy(LazyThreadSafetyMode.SYNCHRONIZED) { TextView(this).apply {
    textSize = sp(10f).toFloat()
    textColor = Color.BLUE}
 }

//或者 不设置参数
val init = lazy { TextView(this).apply {
    textSize = sp(10f).toFloat()
    textColor = Color.BLUE}
 }

//等同于
val init by lazy { TextView(this).apply {
    textSize = sp(10f).toFloat()
    textColor = Color.BLUE}
}

//使用这个lazy<TextView>对象
init.value.gravity = Gravity.CENTER

```
### 3.3.4 repeat,run

```
@kotlin.internal.InlineOnly
public inline fun repeat(times: Int, action: (Int) -> Unit) {
    for (index in 0..times - 1) {
        action(index)
    }
}

//使用方法
repeat(10) { print("index:$it")}
```

---
运行一段代码块，可以是否有返回值
```
run {  }
"xxx".run { toUpperCase() }
```

>
>
### 3.3.5 to

```
public infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)
```
可以用来创建map

```
val map =  mapOf(1 to 2,2 to 3)
```

### 3.4 创建（引入）Kotlin
    AS操作
### 3.4 高阶函数解析，常见使用问题讨论
    函数拓展,函数传递，包级函数...
### [Ktolin For Gradle ](https://github.com/gradle/kotlin-dsl/releases/tag/v0.9.0)
