import java.util.HashMap;

public class iDandpaswords {
    HashMap <String,String> loginInfo = new HashMap<String,String>();
    iDandpaswords(){
        loginInfo.put("Abdullah","290102");
        loginInfo.put("pizza","123");
        loginInfo.put("dog","321");

    }
    protected HashMap getlogininfo(){
        return loginInfo;
    }
}
