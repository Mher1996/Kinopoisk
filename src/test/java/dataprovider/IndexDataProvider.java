package dataprovider;

import org.testng.annotations.DataProvider;
import base.Helper;

public class IndexDataProvider {
    @DataProvider(name = "Indexes")
    public Object[][] indexProvider(){
        Helper helper=new Helper();
        return helper.index(6);
    }

}
