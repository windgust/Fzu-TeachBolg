import com.ukefu.ask.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringTest {

//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void ElasticsearchTest(){
        try {
//            if (elasticsearchTemplate == null) System.out.println("null");
            System.out.println("null");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
