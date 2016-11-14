/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;

/**
 * User model
 * @author Alexta
 */
public class UserModel {

    private String kind;
    private String etag;
    private String objectType;
    private String id;
    private String displayName;
    private String url;
    private Map<String, String> image;

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUrl() {
        return url;
    }

    public String getURL() {
        return url;
    }

    public Map<String, String> getImage() {
        return image;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage(Map<String, String> image) {
        this.image = image;
    }
    
    @Override
    public String toString()
    {
        return id;
    }
}
