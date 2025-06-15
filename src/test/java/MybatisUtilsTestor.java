import cn.lyky.oa.utils.MybatisUtils;
import org.junit.Test;

public class MybatisUtilsTestor {
    @Test
    public void testCase1() {
        String result = (String)MybatisUtils.executeQuery(sqlSession -> {
            String out = (String)sqlSession.selectOne("test.sample");
//            System.out.println(out);
            return out;
        });
        System.out.println(result);
    }
}
