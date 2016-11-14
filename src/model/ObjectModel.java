/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 * Object model
 * @author Alexta
 */
public class ObjectModel {

    private String objectType;
    private String content;
    private String url;
    private RepliesModel replies;
    private PlusOnersModel plusoners;
    private ResharersModel resharers;
    
    private List<AttachmentsModel> attachments;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
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

    public RepliesModel getReplies() {
        return replies;
    }

    public void setReplies(RepliesModel replies) {
        this.replies = replies;
    }

    public PlusOnersModel getPlusoners() {
        return plusoners;
    }

    public void setPlusoners(PlusOnersModel plusoners) {
        this.plusoners = plusoners;
    }

    public ResharersModel getResharers() {
        return resharers;
    }

    public void setResharers(ResharersModel resharers) {
        this.resharers = resharers;
    }

    public List<AttachmentsModel> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentsModel> attachments) {
        this.attachments = attachments;
    }

}
