import org.kenshin.annotationgetter.Getter;

@Getter
public class App {
    private String value;

    public App(String value){
        this.value = value;
    }

    public static void main(String[] args){
        App app = new App("Getter annotation success");
        //测的时候再打开idea报错烦-_-|||,用package，然后运行jar包测，debug不行不知道为啥，不过总算成功了
//        System.out.println(app.getValue());

    }
}
