package com.ukefu.ask.web.handler.work;

import com.ukefu.ask.message.BaseMessage;
import com.ukefu.ask.message.WorkMessage;
import com.ukefu.ask.service.repository.WorkRepository;
import com.ukefu.ask.web.handler.Handler;
import com.ukefu.ask.web.model.User;
import com.ukefu.ask.web.model.Work;
import com.ukefu.ask.web.model.WorkFile;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "/teacher/work")
public class WorkController  extends Handler {

    @Autowired
    private WorkRepository workRepository;

    @Value("${web.upload-path}")
    private String path;

    /*
    * 下载作业
    * @param {id} 作业id
    * @param {fileId} 作业文件路径fileUrl
    * @return
    * */
    @RequestMapping("/download/{id}/{fileId}")
    @Menu(type = "apps" , subtype = "work" , access = true)
    public void index(HttpServletResponse response, @PathVariable String id, @PathVariable String fileId) throws IOException {
        File avatar = new File(path ,fileId) ;
        if(avatar.exists() && !org.elasticsearch.common.lang3.StringUtils.isBlank(fileId)){
            Work work = workRepository.getOne(id);
            String fileName =  new String(work.getFileName().getBytes("gb2312"), "ISO8859-1")+work.getFileSuffix();
            response.getOutputStream().write(FileUtils.readFileToByteArray(new File(path ,fileId)));
            response.setCharacterEncoding("utf-8");
            System.out.println(fileName);
            response.setContentType("application/msword");
            response.addHeader("Content-Disposition", "attachment;filename="+fileName);
        }else{
            response.sendRedirect("/images/user/default.png");
        }
        return ;
    }

    /*
    * 上传作业
    * @param title 作业标题
    * @param file 作业
    * @return
    * */
    @ResponseBody
    @RequestMapping(value = "/save", produces = { "application/json;charset=UTF-8" })
    public BaseMessage save(@RequestParam(value = "title")String title,
                            @RequestParam(value = "workRequirements")String workRequirements,
                            @RequestParam(value = "type")String type,
                            @RequestParam(value = "startTime")long startTime,
                            @RequestParam(value = "closeTime")long closeTime,
                            @RequestParam(value = "file", required = false) MultipartFile file,
                            HttpServletRequest request){
        BaseMessage message = new BaseMessage();
//        ModelAndView view = request(super.createAppsTempletResponse("/apps/work/file")) ;
        User user = super.getUser(request);
        Work work = null;
        String fileId = "",fileName = "";
        try {
            if(file!=null){
                fileId = UKTools.getUUID() ;
                fileName = file.getOriginalFilename();
                System.out.println("文件路径：" + path + ","+fileId);
                System.out.println(file.getOriginalFilename());
                File workfile = new File(path , fileId);
                FileUtils.writeByteArrayToFile(workfile, file.getBytes());
                System.out.println(workfile.getAbsolutePath());
            }else {
                System.out.println("文件为空");
            }
            message.msg = "success";
            work = new Work();
            work.setUserid(user.getId());
            work.setUserName(user.getUsername());
            work.setTitle(title);
            work.setWorkRequirements(workRequirements);
            work.setCreateTime(System.currentTimeMillis());
            work.setStartTime(startTime);
            work.setCloseTime(closeTime);
            work.setType(type);
            if (!StringUtils.isBlank(fileName)){
                work.setFileName(fileName.substring(0,fileName.lastIndexOf(".")));
                work.setFileSuffix(fileName.substring(fileName.lastIndexOf(".")));
            }
            work.setFileUrl(fileId);
            workRepository.save(work);
        }
        catch (Exception e){
            message.msg = "fail";
            e.printStackTrace();
//            view = request(super.createRequestPageTempletResponse("redirect:/"));
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/find")
    public WorkMessage find(@RequestParam(value = "type",defaultValue = "")String type,
                            @RequestParam(value = "me",defaultValue = "false")boolean me,
                            @RequestParam(value = "curPage",defaultValue = "1")int curPage,
                            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                            HttpServletRequest request){
        WorkMessage message = new WorkMessage();
        Page page = null;
        try {
            String userid = "";
            if (me) {
                User user = super.getUser(request);
                userid = user.getId();
            }
            page = workRepository.findByTypeLikeAndUseridLike("%"+type+"%","%"+userid+"%",new PageRequest(curPage - 1,pageSize));
            message.msg = "success";
            message.data = page.getContent();
            message.curPage = curPage;
            message.pageSize = pageSize;
            message.count = (int) page.getTotalElements();
            message.allPage = page.getTotalPages();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }
}
