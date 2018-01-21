package design.mode;

/**
 * Created by sven on 2018/1/11.
 */
class Product{
    private String name;
    private String type;
    public void show(){
        System.out.println("参数名："+name);
        System.out.println("类型："+type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
interface Build{
    void setPart(String arg1,String arg2);
    Product getProduct();
}
class ConcreteBuild implements Build{
     Product product = new Product();

    @Override
    public void setPart(String arg1, String arg2) {
        product.setName(arg1);
        product.setType(arg2);
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
public class Create {

}
