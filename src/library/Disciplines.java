package library;

public class Disciplines {
    private int dn_id;
    private String dn_name;
    private int dn_max_score;
    private int dn_num_lessons;

    public Disciplines(int dn_id, String dn_name, int dn_max_score, int dn_num_lessons) {
        this.dn_id = dn_id;
        this.dn_name = dn_name;
        this.dn_max_score = dn_max_score;
        this.dn_num_lessons = dn_num_lessons;
    }

    public int getDn_id() {
        return dn_id;
    }

    public String getDn_name() {
        return dn_name;
    }

    public int getDn_max_score() {
        return dn_max_score;
    }

    public int getDn_num_lessons() {
        return dn_num_lessons;
    }
}
