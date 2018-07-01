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
    private String fileUrl;  //文件路径
    private double score = 0;  //分数
    private int download = 0;  //下载量
    private long uptime;  //上传时间
    private String title;  //作业标题
    private String content; //作业内容

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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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

    @Override
    public String toString() {
        return "WorkFile{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", score=" + score +
                ", download=" + download +
                ", uptime=" + uptime +
                ", title='" + title + '\'' +
                '}';
    }
}
