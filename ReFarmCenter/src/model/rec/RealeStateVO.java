package model.rec;

public class RealeStateVO {
	private int sel_Num;
	private int area_dong_Num;
	private int propertyNum;
	private int sell_category_num;
	private int sel_price;
	private int sell_surfacearea;
	private String name;
	private String area;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public RealeStateVO() {
		
	}
	public RealeStateVO(int sel_Num,int area_dong_num,int propertyNum,int sell_categorynum,int sel_price,int sell_surfacearea) {
		this.sel_Num = sel_Num;
		this.area_dong_Num = area_dong_num;
		this.propertyNum = propertyNum;
		this.sell_category_num = sell_categorynum;
		this.sel_price = sel_price;
		this.sell_surfacearea = sell_surfacearea;
	}
	public int getSell_category_num() {
		return sell_category_num;
	}
	public void setSell_category_num(int sell_category_num) {
		this.sell_category_num = sell_category_num;
	}
	public int getSel_Num() {
		return sel_Num;
	}
	public void setSel_Num(int sel_Num) {
		this.sel_Num = sel_Num;
	}
	public int getArea_dong_Num() {
		return area_dong_Num;
	}
	public void setArea_dong_Num(int area_dong_Num) {
		this.area_dong_Num = area_dong_Num;
	}
	public int getPropertyNum() {
		return propertyNum;
	}
	public void setPropertyNum(int propertyNum) {
		this.propertyNum = propertyNum;
	}
	public int getSel_price() {
		return sel_price;
	}
	public void setSel_price(int sel_price) {
		this.sel_price = sel_price;
	}
	public int getSell_surfacearea() {
		return sell_surfacearea;
	}
	public void setSell_surfacearea(int sell_surfacearea) {
		this.sell_surfacearea = sell_surfacearea;
	}
	
	
}
