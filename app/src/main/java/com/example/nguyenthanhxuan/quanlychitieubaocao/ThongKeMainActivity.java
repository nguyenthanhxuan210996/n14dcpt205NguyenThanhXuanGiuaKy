package com.example.nguyenthanhxuan.quanlychitieubaocao;

/**
 * Created by Nguyen Thanh Xuan on 4/6/2018.
 */

public class ThongKeMainActivity {
    private String khoanthukhoanchi;
    private String sotien;

    public ThongKeMainActivity() {
    }

    public ThongKeMainActivity(String khoanthukhoanchi, String sotien) {
        this.khoanthukhoanchi = khoanthukhoanchi;
        this.sotien = sotien;
    }

    public String getKhoanthukhoanchi() {
        return khoanthukhoanchi;
    }

    public void setKhoanthukhoanchi(String khoanthukhoanchi) {
        this.khoanthukhoanchi = khoanthukhoanchi;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }
}
