package ashishrpa.timeplace;

import java.io.Serializable;

/**
 * Created by WINDOWS 8.1 on 10/17/2016.
 */

public class MyDate implements Serializable {
    private static final long serialVersionUID = 1L;

    public int getmYear() {
        return mYear;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }

    public int getmMonth() {
        return mMonth;
    }

    public void setmMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public int getmDay() {
        return mDay;
    }

    public void setmDay(int mDay) {
        this.mDay = mDay;
    }

    private int mYear;
    private int mMonth;
    private int mDay;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
