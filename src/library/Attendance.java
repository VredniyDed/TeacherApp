package library;

public class Attendance {
    private int att_id;
    private int st_id;
    private int dn_id;
    private int att_status;
    private int sl_id;


    public Attendance(int att_id, int st_id, int dn_id, int att_status, int sl_id) {
        this.att_id = att_id;
        this.st_id = st_id;
        this.dn_id = dn_id;
        this.att_status = att_status;
        this.sl_id = sl_id;
    }

    public int getAtt_id() {
        return att_id;
    }

    public int getSt_id() {
        return st_id;
    }

    public int getDn_id() {
        return dn_id;
    }

    public int getAtt_status() {
        return att_status;
    }

    public int getSl_id() {
        return sl_id;
    }
}
