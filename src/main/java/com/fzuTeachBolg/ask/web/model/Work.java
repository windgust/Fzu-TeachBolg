package com.fzuTeachBolg.ask.web.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "work")
public class Work {

    private String id ;
    private String userid;  //用户id
    private String userName;  //用户名
    private String title;  //标题
    private String workRequirements;   //作业要求
    private long createTime;  //创建时间
    private long startTime;  //提交时间
    private long closeTime;  //截止时间
    private int submitCount = 0;  //提交情况
    private String type;   //作业类别
    private String fileUrl;  //文件url
    private String fileName = "";   //文件名
    private String fileSuffix = "";  //文件后缀

    @Id
    @Column(length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkRequirements() {
        return workRequirements;
    }

    public void setWorkRequirements(String workRequirements) {
        this.workRequirements = workRequirements;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(long closeTime) {
        this.closeTime = closeTime;
    }

    public int getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(int submitCount) {
        this.submitCount = submitCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", workRequirements='" + workRequirements + '\'' +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", closeTime=" + closeTime +
                ", submitCount=" + submitCount +
                ", type='" + type + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSuffix='" + fileSuffix + '\'' +
                '}';
    }
}
