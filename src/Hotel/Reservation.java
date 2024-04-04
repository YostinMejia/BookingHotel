package Hotel;

import java.util.Date;

public class Reservation {
    private Integer id;
    private String userInfo;
    private Date from;
    private Date to;

    public Reservation(Integer id, String userInfo, Date from, Date to) {
        this.id  =id;
        this.userInfo = userInfo;
        this.from = from;
        this.to = to;
    }

    public Integer getId() {
        return id;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

}