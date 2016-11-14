/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Attachments model
 * @author Alexta
 */
public class AttachmentsModel {

    private String objectType;
    private String id;
    private String content;
    private String url;
    private ImageModel image;
    private ImageModel fullImage;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageModel getImage() {
        return image;
    }

    public void setImage(ImageModel image) {
        this.image = image;
    }

    public ImageModel getFullImage() {
        return fullImage;
    }

    public void setFullImage(ImageModel fullImage) {
        this.fullImage = fullImage;
    }
    
}
