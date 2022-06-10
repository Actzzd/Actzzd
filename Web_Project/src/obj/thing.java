package obj;

public class thing {
	private String id;
	private String codeId;
	private String containerNum;
	private String containerType;
	private String invoiceNum;
	private String boxId;
	private String loadingTime;
	private String supplier;
	private String labelCode;
	private String boxOrder;
	private String boxNum;
	private String boxType;
	private String component;
	private String color;
	private String volume;
	private String count;
	private String prepareNum;
	private String grossWeight;
	private String netWeight;
	private String units;
	private String labelNum;
	private String state;
	private String scanTime;
	private String lock;
	private String serial;
	

	public thing(String id, String codeId, String containerNum, String containerType, String invoiceNum, String boxId,
			String loadingTime, String supplier, String labelCode, String boxOrder, String boxNum, String boxType,
			String component, String color, String volume, String count, String prepareNum, String grossWeight,
			String netWeight, String units, String labelNum, String state, String scanTime, String lock
			) {
		super();
		this.id = id;
		this.codeId = codeId;
		this.containerNum = containerNum;
		this.containerType = containerType;
		this.invoiceNum = invoiceNum;
		this.boxId = boxId;
		this.loadingTime = loadingTime;
		this.supplier = supplier;
		this.labelCode = labelCode;
		this.boxOrder = boxOrder;
		this.boxNum = boxNum;
		this.boxType = boxType;
		this.component = component;
		this.color = color;
		this.volume = volume;
		this.count = count;
		this.prepareNum = prepareNum;
		this.grossWeight = grossWeight;
		this.netWeight = netWeight;
		this.units = units;
		this.labelNum = labelNum;
		this.state = state;
		this.scanTime = scanTime;
		this.lock = lock;
	}
	public thing(String id, String codeId, String containerNum, String containerType, String invoiceNum, String boxId,
			String loadingTime, String supplier, String labelCode, String boxOrder, String boxNum, String boxType,
			String component, String color, String volume, String count, String prepareNum, String grossWeight,
			String netWeight, String units, String labelNum, String state, String scanTime, String lock,
			String serial) {
		super();
		this.id = id;
		this.codeId = codeId;
		this.containerNum = containerNum;
		this.containerType = containerType;
		this.invoiceNum = invoiceNum;
		this.boxId = boxId;
		this.loadingTime = loadingTime;
		this.supplier = supplier;
		this.labelCode = labelCode;
		this.boxOrder = boxOrder;
		this.boxNum = boxNum;
		this.boxType = boxType;
		this.component = component;
		this.color = color;
		this.volume = volume;
		this.count = count;
		this.prepareNum = prepareNum;
		this.grossWeight = grossWeight;
		this.netWeight = netWeight;
		this.units = units;
		this.labelNum = labelNum;
		this.state = state;
		this.scanTime = scanTime;
		this.lock = lock;
		this.serial = serial;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getContainerNum() {
		return containerNum;
	}
	public void setContainerNum(String containerNum) {
		this.containerNum = containerNum;
	}
	public String getContainerType() {
		return containerType;
	}
	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}
	public String getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}
	public String getBoxId() {
		return boxId;
	}
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}
	public String getLoadingTime() {
		return loadingTime;
	}
	public void setLoadingTime(String loadingTime) {
		this.loadingTime = loadingTime;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getLabelCode() {
		return labelCode;
	}
	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}
	public String getBoxOrder() {
		return boxOrder;
	}
	public void setBoxOrder(String boxOrder) {
		this.boxOrder = boxOrder;
	}
	public String getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(String boxNum) {
		this.boxNum = boxNum;
	}
	public String getBoxType() {
		return boxType;
	}
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPrepareNum() {
		return prepareNum;
	}
	public void setPrepareNum(String prepareNum) {
		this.prepareNum = prepareNum;
	}
	public String getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}
	public String getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getLabelNum() {
		return labelNum;
	}
	public void setLabelNum(String labelNum) {
		this.labelNum = labelNum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getScanTime() {
		return scanTime;
	}
	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}
	public String getLock() {
		return lock;
	}
	public void setLock(String lock) {
		this.lock = lock;
	}
	
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	@Override
	public String toString() {
		return "('"+id + "','"+ codeId + "','"+ containerNum + "','"
				+ containerType + "','" + invoiceNum + "','" + boxId + "','" + loadingTime
				+ "','" + supplier + "','" + labelCode + "','" + boxOrder + "','"
				+ boxNum + "','" + boxType + "','" + component + "','" + color + "','"
				+ volume + "','" + count + "','" + prepareNum + "','" + grossWeight
				+ "','" + netWeight + "','" + units + "','" + labelNum + "','" + state
				+ "','" + scanTime + "','" + lock+"')";
	}

}