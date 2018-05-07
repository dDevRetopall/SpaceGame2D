package proposiciones;

import constants.Constants;

public class ItemId {
	private String path;
	private boolean isWeapon;

	private int shieldIncreaseValue;
	private int healthIncreaseValue;
	private float probability;
	private int id;
	private int countDown;
	private int heightImage;
	private int widthImage;
	private int quality;
	private String name;
	private int incrementMaterial;
	private boolean isMaterial;
	private int maxStacks;
	private int initialQuantity;
	public ItemId(String name,String path,int shieldIncreaseValue,int healthIncreaseValue,float probability,int countDown,int id,int widthImage,int heightImage,int quality,int maxStacks,int initialQuantity) {
		this.name = name;
		this.path = path;
		this.shieldIncreaseValue = shieldIncreaseValue;
		this.healthIncreaseValue = healthIncreaseValue;
		this.probability = probability;
		this.countDown = countDown;
		this.id = id;
		this.widthImage = widthImage;
		this.heightImage = heightImage;
		this.quality = quality;
		this.maxStacks = maxStacks;
		this.initialQuantity = initialQuantity;
		this.isWeapon=false;
		
	}
	public ItemId(String name,String path,boolean isMaterial,float probability,int id,int widthImage,int heightImage,int quality,int incrementMaterial,int maxStacks) {
		this.initialQuantity=1;
		this.maxStacks=maxStacks;
		this.isMaterial = isMaterial;
		this.probability = probability;
		this.path = path;
	
		this.id = id;
		this.widthImage = widthImage;
		this.heightImage = heightImage;
		this.quality = quality;
		this.name = name;
		this.incrementMaterial = incrementMaterial;
	}
	
	public int getInitialQuantity() {
		return initialQuantity;
	}
	public void setInitialQuantity(int initialQuantity) {
		this.initialQuantity = initialQuantity;
	}
	public int getMaxStacks() {
		return maxStacks;
	}
	public void setMaxStacks(int maxStacks) {
		this.maxStacks = maxStacks;
	}
	public int getCountDown() {
		return countDown;
	}
	public void setCountDown(int countDown) {
		this.countDown = countDown;
	}
	public String getPath() {
		return Constants.imagesPath+path;
	}
	public boolean isWeapon() {
		return isWeapon;
	}
	
	public int getIncrementMaterial() {
		return incrementMaterial;
	}
	public boolean isMaterial() {
		return isMaterial;
	}
	public void setIncrementMaterial(int incrementMaterial) {
		this.incrementMaterial = incrementMaterial;
	}
	public void setMaterial(boolean isMaterial) {
		this.isMaterial = isMaterial;
	}
	public int getShieldIncreaseValue() {
		return shieldIncreaseValue;
	}
	public int getHealthIncreaseValue() {
		return healthIncreaseValue;
	}
	public float getProbability() {
		return probability;
	}
	public int getId() {
		return id;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setWeapon(boolean isWeapon) {
		this.isWeapon = isWeapon;
	}
	public void setShieldIncreaseValue(int shieldIncreaseValue) {
		this.shieldIncreaseValue = shieldIncreaseValue;
	}
	public void setHealthIncreaseValue(int healthIncreaseValue) {
		this.healthIncreaseValue = healthIncreaseValue;
	}
	public void setProbability(float probability) {
		this.probability = probability;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHeightImage() {
		return heightImage;
	}
	public int getWidthImage() {
		return widthImage;
	}
	public void setHeightImage(int heightImage) {
		this.heightImage = heightImage;
	}
	public void setWidthImage(int widthImage) {
		this.widthImage = widthImage;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
