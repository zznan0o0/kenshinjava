import org.kenshin.annotationgetter.Getter;

@Getter
public class App {
    private String value;

    public App(String value){
        this.value = value;
    }

    public static void main(String[] args){
        App app = new App("Getter annotation success");
        //没成功不知道为啥
//        System.out.println(app.getValue());

    }
}
