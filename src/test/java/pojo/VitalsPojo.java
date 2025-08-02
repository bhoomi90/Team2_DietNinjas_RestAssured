package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VitalsPojo {

	@JsonProperty("Weight")
	private float weight;
	@JsonProperty("Height")
	private float height;
	@JsonProperty("Temperature")
	private float temperature;
	@JsonProperty("SP")
	private float sp;
	@JsonProperty("DP")
	private float dp;
	
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getSP() {
		return sp;
	}
	public void setSP(float sP) {
		this.sp = sP;
	}
	public float getDP() {
		return dp;
	}
	public void setDP(float dP) {
		this.dp = dP;
	}
}
