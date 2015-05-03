package Bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by aaa on 15-5-1.
 */
@Table(name = "cate")
public class Cate {
    @Id(column = "id")
    @NoAutoIncrement
    private String categoryID;
    @Column(column = "name")
    private String name;
    @Column(column = "imageUrl")
    private String imageUrl;
    @Column(column = "launchShow")
    private String launchShow;
    @Column(column = "icon")
    private String icon;
    @Column(column = "count")
    private String count;

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLaunchShow() {
        return launchShow;
    }

    public void setLaunchShow(String launchShow) {
        this.launchShow = launchShow;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
