package library;

public class Requests {
    private String full_name;
    private int grade;
    private String gradeStr;

    public Requests(String full_name) {
        this.full_name = full_name;
    }

    public Requests(String full_name, int grade) {
        this.full_name = full_name;
        this.grade = grade;
    }
    public Requests(String full_name, String grade) {
        this.full_name = full_name;
        this.gradeStr = grade;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getGradeStr() {
        return gradeStr;
    }

    public int getGrade() {
        return grade;
    }
}
