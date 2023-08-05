package library;

public class Users {
    private String login;
    private String pass;
    private Integer adm_rules;

    public Users(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public Users(String login, String pass, Integer adm_rules) {
        this.login = login;
        this.pass = pass;
        this.adm_rules = adm_rules;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public Integer getAdm_rules() {
        return adm_rules;
    }

}
