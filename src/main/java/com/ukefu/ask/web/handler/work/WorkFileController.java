package com.ukefu.ask.web.handler.work;

import com.ukefu.ask.message.BaseMessage;
import com.ukefu.ask.message.WorkMessage;
import com.ukefu.ask.service.repository.WorkFileRepository;
import com.ukefu.ask.web.handler.Handler;
import com.ukefu.ask.web.model.User;
import com.ukefu.ask.web.model.WorkFile;
import com.ukefu.util.Menu;
import com.ukefu.util.UKTools;
import org.apache.commons.io.FileUtils;
import org.elasticsearch.common.lang3.StringUtils;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "/work")
public class WorkFileController extends Handler {

    @Autowired
    private WorkFileRepository workFileRepository;

    @Value("${web.upload-path}")
    private String path;

    /*
    * 返回作业页面
    * */
    @RequestMapping("/workFile")
    @Menu(type = "apps" , subtype = "work" , access = false)
    public ModelAndView workFile(HttpServletRequest request , HttpServletResponse response) {
        //返回templates目录下的html
        ModelAndView view = request(super.createAppsTempletResponse("/apps/work/workFile")) ;
        return view;
    }

    /*
    * 下载作业
    * @param {id} 作业id
    * @param {fileId} 作业文件路径fileUrl
    * @return
    * */
    @RequestMapping("/download/{id}/{fileId}")
    @Menu(type = "apps" , subtype = "work" , access = true)
    public void index(HttpServletResponse response, @PathVariable String id,@PathVariable String fileId) throws IOException {
        File avatar = new File(path ,fileId) ;
        if(avatar.exists() && !StringUtils.isBlank(fileId)){
            WorkFile workFile = workFileRepository.getOne(id);
            workFile.setDownload(workFile.getDownload()+1);
            workFileRepository.save(workFile);
            response.getOutputStream().write(FileUtils.readFileToByteArray(new File(path ,fileId)));
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
                            @RequestParam(value = "file", required = false) MultipartFile file,
                            HttpServletRequest request){
        BaseMessage message = new BaseMessage();
        ModelAndView view = request(super.createRequestPageTempletResponse("redirect:/"));
        User user = super.getUser(request);
        WorkFile workFile = null;
        try {
            String imgid = UKTools.getUUID() ;
            System.out.println("文件路径：" + path + ","+imgid);
//            System.out.println(user);
            if(file!=null){
                File workfile = new File(path , imgid);
                FileUtils.writeByteArrayToFile(workfile, file.getBytes());
                System.out.println(workfile.getAbsolutePath());
            }else {
                System.out.println("文件为空");
            }
            message.msg = "success";
            workFile = new WorkFile();
            workFile.setUserid(user.getId());
            workFile.setUptime(System.currentTimeMillis());
            workFile.setFileUrl(imgid);
            workFile.setTitle(title);
            workFileRepository.save(workFile);
        }
        catch (Exception e){
            message.msg = "fail";
            e.printStackTrace();
        }
        return message;
    }

    /*
    * 查询作业列表
    * @param title 作业标题
    * @param curPage 当前页，默认为0，代表第一页
    * @param pageSize 每页条数，默认为10
    * @return
    * */
    @ResponseBody
    @RequestMapping(value = "/find", produces = { "application/json;charset=UTF-8" })
    public WorkMessage find(@RequestParam(value = "title",defaultValue = "")String title,
                     @RequestParam(value = "curPage",defaultValue = "1")int curPage,
                     @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Page page = null;
        WorkMessage message = new WorkMessage();
        try {
            if (StringUtils.isBlank(title)){
                page =  workFileRepository.findAll(new PageRequest(curPage - 1,pageSize));
            }else {
                page =  workFileRepository.findByTitleLike("%"+title+"%",new PageRequest(curPage - 1,pageSize));
            }
            message.msg = "success";
            message.data = page.getContent();
            message.curPage = curPage;
            message.pageSize = pageSize;
            message.count = (int) page.getTotalElements();
            message.allPage = page.getTotalPages();

        }
        catch (Exception e){
            message.msg = "fail";
            e.printStackTrace();
        }
        return message;
    }

    /*
    * 给作业打分
    * @param {id} 作业id
    * @param score 作业分数
    * @return
    * */
    @ResponseBody
    @RequestMapping(value = "/check/{id}",produces = {"application/json;charset=UTF-8"})
    public BaseMessage check(@PathVariable String id, @RequestParam(value = "score")double score){
        BaseMessage message = new BaseMessage();
        try {
            message.msg = "success";
            WorkFile workFile = workFileRepository.getOne(id);
            if (workFile != null){
                workFile.setScore(score);
                workFileRepository.save(workFile);
            }
        }
        catch (Exception e){
            message.msg = "fail";
            e.printStackTrace();
        }
        return message;
    }
}
