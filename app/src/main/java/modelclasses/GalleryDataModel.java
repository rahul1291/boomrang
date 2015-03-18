package modelclasses;

import java.io.Serializable;

/**
 * Created by rahul on 3/18/2015.
 */
public class GalleryDataModel implements Serializable {
    private String image_path;
    private String filemimetype;
    private String filetitle;
    private String filethumnbail;
    private String video_path;
    private String filepath;

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getFilemimetype() {
        return filemimetype;
    }

    public void setFilemimetype(String filemimetype) {
        this.filemimetype = filemimetype;
    }

    public String getFiletitle() {
        return filetitle;
    }

    public void setFiletitle(String filetitle) {
        this.filetitle = filetitle;
    }

    public String getFilethumnbail() {
        return filethumnbail;
    }

    public void setFilethumnbail(String filethumnbail) {
        this.filethumnbail = filethumnbail;
    }

    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
    }
}
