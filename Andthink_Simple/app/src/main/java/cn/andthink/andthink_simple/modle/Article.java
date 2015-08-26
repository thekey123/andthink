package cn.andthink.andthink_simple.modle;

import cn.bmob.v3.BmobObject;

/**
 * Created by thekey123 on 2015/8/25.
 *
 * 所有的文章实体类
 *
 */
public class Article extends BmobObject {


    /**缺省字段：时间**/

    //标题
    private String title;
    //内容
    private String content;
    //图片
    private String image;
    //是否通过审核
    private Boolean ispass;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getIspass() {
        return ispass;
    }

    public void setIspass(Boolean ispass) {
        this.ispass = ispass;
    }
}
