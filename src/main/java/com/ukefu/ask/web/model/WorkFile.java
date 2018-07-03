package com.ukefu.ask.web.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "work_file")
@org.hibernate.annotations.Proxy(lazy = false)
public class WorkFile {

    private String id ;
//    private User user;
    private String userid;
    private String userName;
    private String fileUrl;  //文件路径
    private String fileName; //文件名
    private String fileSuffix;  //文件后缀
    private double score = 0;  //分数
    private int download = 0;  //下载量
    private long uptime;  //上传时间
    private String title;  //作业标题
    private String content; //作业内容
    private int type;   //作业类型，1、是提交作业，2、是资源

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

//    @ManyToOne
//    @JoinColumn(name = "userid")


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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WorkFile{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", userName='" + userName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSuffix='" + fileSuffix + '\'' +
                ", score=" + score +
                ", download=" + download +
                ", uptime=" + uptime +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                '}';
    }
}
