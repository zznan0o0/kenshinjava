import org.junit.Test;
import org.kenshin.annotationgetter.Getter;

public class GetterTest {
    @Test
    public void testGetter(){
        People people = new People();
        people.name = "tom";
//        System.out.println(people.getName());
    }

    @Getter
    public class People{
        public String name;
    }
}
