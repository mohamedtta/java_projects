package Main;

public class user_info {
    private  String user,pass,dep;
    public user_info(String user , String pass , String dep){
        this.user = user;
        this.pass = pass;
        this.dep = dep;
    }
    public void setdep(String dep){
        this.dep = dep;
    }
    public void setuser(String user){
        this.user = user;
    }
    public void setpass(String dep){
        this.pass = pass;
    }
    public String getuser(){
        return user;
    }
    public String getPass(){
        return pass;
    }
    public String getDep(){
        return dep;
    }
}
