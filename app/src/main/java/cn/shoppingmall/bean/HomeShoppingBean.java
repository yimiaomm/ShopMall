package cn.shoppingmall.bean;

/**
 * Created by ${易淼} on 2017/8/24.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class HomeShoppingBean {
    String imageUrl,imageName;

    public HomeShoppingBean(String imageUrl, String imageName) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
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
