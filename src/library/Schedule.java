package library;

import java.time.LocalDateTime;

public class Schedule {
    private int sl_id;
    private int dn_id;
    private String group_name;
    private LocalDateTime schedule_dat;

    public Schedule(int sl_id, int dn_id, String group_name, LocalDateTime schedule_date) {
        this.sl_id = sl_id;
        this.dn_id = dn_id;
        this.group_name = group_name;
        this.schedule_dat = schedule_date;
    }

    public int getSl_id() {
        return sl_id;
    }

    public int getDn_id() {
        return dn_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public LocalDateTime  getSchedule_dat() {
        return schedule_dat;
    }
}
