# Share-Kotlin-Utils
## 1. [Koltin 简介](https://zh.wikipedia.org/wiki/Kotlin)
    
 -   Java,Groovy,Gradle......Koltin???
 -   怎么又有新语言出来啊，简直要疯掉了
    ![image](http://oa5504rxk.bkt.clouddn.com/week29_kotlin/3.png)

 -   有困难要上，没困难制造困难也要上。
 -   靠，这尼玛究竟是谁说的，好有道理！


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


By Kotlin
```
data class Person(val id: Int, val name: String)
```


### 2.2.2 又见空指针
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
.................
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

### 2.2.5 再见，Utils,函数拓展

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

### 2.2.6 晚安，ButterKnife
过去 你的 View 都是用 ButterKnife 注入的：

```
@BindView(R.id.nameView) TextView nameView;
...
nameView.setText("橘右京");
...
```
现在

```
nameView.text = "橘右京"
```

### 3. Kotlin使用
### 3.1 基础语法介绍
你以为我会列出来吗？？？
[Think too much](http://kotlinlang.org/docs/reference/basic-syntax.html) 

### 3.2 [Kotlin高阶函数的使用](http://www.jianshu.com/p/03db2203e0f2)
### 3.2.1 TODO
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

### 3.2.2 apply,with,let...
    
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

### 3.2.3 lazy

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
### 3.2.4 repeat,run

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
### 3.2.5 to

```
public infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)
```
可以用来创建map

```
val map =  mapOf(1 to 2,2 to 3)
```

### 3.3 创建（引入）Kotlin
    AS操作
### 3.4 高阶函数解析，常见使用问题讨论
    函数拓展,函数传递，包级函数...
