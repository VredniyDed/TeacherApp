package library;

import java.time.LocalDateTime;

public class Grades {
    private int grades_id;
    private int st_id;
    private int dn_id;
    private int grade;
    private LocalDateTime gr_date;

    public Grades(int grades_id, int st_id, int dn_id, int grade, LocalDateTime gr_date) {
        this.grades_id = grades_id;
        this.st_id = st_id;
        this.dn_id = dn_id;
        this.grade = grade;
        this.gr_date = gr_date;
    }

    public int getGrades_id() {
        return grades_id;
    }

    public int getSt_id() {
        return st_id;
    }

    public int getDn_id() {
        return dn_id;
    }

    public int getGrade() {
        return grade;
    }

    public LocalDateTime getGr_date() {
        return gr_date;
    }
}
