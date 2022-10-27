package model.rec;

public class ConsultingVO {
	private String id;
	private String date;
	private String values;
	public ConsultingVO(){
		
	}
	public ConsultingVO(String id,String date,String values) {
		this.id = id;
		this.date = date;
		this.values = values;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	
}
