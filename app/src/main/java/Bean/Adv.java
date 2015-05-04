package Bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by aaa on 15-5-1.
 */
@Table(name = "adv")//加上表名，混淆后表名不受影响
public class Adv {
    @Id(column = "id")
    @NoAutoIncrement
    private String projectID;
    @Column(column = "imageUrl")
    private String imageUrl;
    @Column(column = "h5Url")
    private String h5Url;
    @Column(column = "type")
    private String type;
    @Column(column = "title")
    private String title;

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
