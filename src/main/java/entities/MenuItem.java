package entities;

public class MenuItem {
    private int id;
    private String tenMon;
    private double gia;
    private String moTa;
    private String nguyenLieu;
    private String hinhAnh;

    public MenuItem(int id, String tenMon, double gia, String moTa, String nguyenLieu, String hinhAnh) {
        this.id = id;
        this.tenMon = tenMon;
        this.gia = gia;
        this.moTa = moTa;
        this.nguyenLieu = nguyenLieu;
        this.hinhAnh = hinhAnh;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTenMon() { return tenMon; }
    public void setTenMon(String tenMon) { this.tenMon = tenMon; }

    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getNguyenLieu() { return nguyenLieu; }
    public void setNguyenLieu(String nguyenLieu) { this.nguyenLieu = nguyenLieu; }

    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }
}
