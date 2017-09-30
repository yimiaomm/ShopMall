package cn.shoppingmall.bean;



public class RecommentBean {
    String imageUrl,imageName,price;

    public RecommentBean(String imageUrl, String imageName,String price) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
