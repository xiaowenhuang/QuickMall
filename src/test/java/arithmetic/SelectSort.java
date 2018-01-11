package arithmetic;

/** 冒泡排序 加模版方法
 * Created by sven on 2018/1/5.
 */
interface IStrategy{
    void doSomething();
}
class ConcreteStrategy1 implements IStrategy{

    @Override
    public void doSomething() {
        System.out.println("具体策略1");
    }
}
class ConcreteStrategy2 implements IStrategy{

    @Override
    public void doSomething() {
        System.out.println("具体策略2");
    }
}
class Context{
    private IStrategy strategy;
    public Context(IStrategy strategy){
        this.strategy = strategy;
    }
    public void execute(){
        strategy.doSomething();
    }
}
public class SelectSort{
    public static void main(String[] args) {
        Context cx = new Context(new ConcreteStrategy1());
        cx.execute();
        cx = new Context(new ConcreteStrategy2());
        cx.execute();
    }
}