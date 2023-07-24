package product.Model;

public class ProductCls {

	int categoryId;
	int prodId;
	String productName,prodDesc,prodImgUrl;
	int qty;
	double price;
	

	public ProductCls() {
		super();
	}
	public ProductCls(int categoryId, int prodId, String productName, String prodDesc, String prodImgUrl, int qty,
			double price) {
		super();
		this.categoryId = categoryId;
		this.prodId = prodId;
		this.productName = productName;
		this.prodDesc = prodDesc;
		this.prodImgUrl = prodImgUrl;
		this.qty = qty;
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductCls [categoryId=" + categoryId + ", prodId=" + prodId + ", productName=" + productName
				+ ", prodDesc=" + prodDesc + ", prodImgUrl=" + prodImgUrl + ", qty=" + qty + ", price=" + price + "]";
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getProdImgUrl() {
		return prodImgUrl;
	}
	public void setProdImgUrl(String prodImgUrl) {
		this.prodImgUrl = prodImgUrl;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	
}
