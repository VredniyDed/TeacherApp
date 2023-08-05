package library;

public class Students {

    private String st_id;
    private String st_name;
    private String st_surname;
    private String group_name;

    public Students(String st_id, String st_name, String st_surname, String group_name) {
        this.st_id = st_id;
        this.st_name = st_name;
        this.st_surname = st_surname;
        this.group_name = group_name;

    }


    public String getId() {
        return st_id;
    }

    public String getSt_name() {
        return st_name;
    }

    public String getSt_surname() {
        return st_surname;
    }
    public String getGroup_name() {
        return group_name;
    }


}
