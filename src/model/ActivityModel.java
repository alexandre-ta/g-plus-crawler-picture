/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Activity model
 * @author Alexta
 */
public class ActivityModel {

    private String kind;
    private String etag;
    private String title;
    private String published;
    private String updated;
    private String id;
    private String url;
    private ActorModel actor;
    private ObjectModel object;
    private ProviderModel provider;
    private AccessModel access;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ActorModel getActor() {
        return actor;
    }

    public void setActor(ActorModel actor) {
        this.actor = actor;
    }

    public ObjectModel getObject() {
        return object;
    }

    public void setObject(ObjectModel object) {
        this.object = object;
    }

    public ProviderModel getProvider() {
        return provider;
    }

    public void setProvider(ProviderModel provider) {
        this.provider = provider;
    }

    public AccessModel getAccess() {
        return access;
    }

    public void setAccess(AccessModel access) {
        this.access = access;
    }
}
