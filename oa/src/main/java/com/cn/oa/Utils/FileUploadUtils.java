package com.cn.oa.Utils;


import com.cn.oa.constants.FileConstant;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * 上传文件工具类：
 *          删除：创建实列对象的同时将路径传入，再调用deleteFile方法删除文件，支持删除多个文件。
 * */
public class FileUploadUtils {

    private  String pathArray[];
    private String[]arrayFormat= FileConstant.EXCEX.getAllFileFormat();
    private  String format;
    private MultipartFile multipartFile;
    private static ThreadLocal<String> threadLocal=new ThreadLocal<>();

    public FileUploadUtils() {
    }

    public FileUploadUtils(String...path) {
        this.pathArray = path;
    }

    public FileUploadUtils(MultipartFile multipartFile){
        this.multipartFile=multipartFile;
    }

    public static String getPath(){
       return threadLocal.get();
    }

    /**
     * 对上传的我文件类型进行判断
     * */
    private void verifyFileType(MultipartFile multipartFile){
        if (multipartFile.isEmpty()){
            return ;
        }
        String type = multipartFile.getContentType();
        Integer index =type.indexOf("/");
        String formats = type.substring(index+1, type.length()).toLowerCase();



        boolean flag = this.isInclude(this.arrayFormat, formats);

        if (flag){
            this.format=formats;
        }


    }

    private boolean isInclude(String [] array,String one ){
        for (String s : array) {
            if (s.equals(one)) return  true;
        }

        return false;
    }

    /**
     *对文件重名名
     * */
    private String rename(){
        String uuid =UUID.randomUUID().toString().replace("-","");
        long time = new Date().getTime();
        String name =uuid+time;
        return name+"."+this.format;
    }

    /**
     * 将一个文件上传到服务器上
     *
     * @return*/
    public String saveFile() throws Exception {
        this.verifyFileType(this.multipartFile);
        String path =this.getClass().getClassLoader().getResource("").getPath();
        if (path.contains("WEB-INF")){
            int i = path.indexOf("WEB-INF");
            path=path.substring(0,i);
            path=path+"imgFile";
            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }
            path=path+"/"+rename();
            File file2 = new File(path);
            threadLocal.set(path);
            multipartFile.transferTo(file2);
            String path1=path.substring(path.indexOf("imgFile"));
            return path1;
        }else {
            path=path+"imgFile";
            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }
            path=path+"/"+rename();
            File file2 = new File(path);
            threadLocal.set(path);
            multipartFile.transferTo(file2);
            return path;
        }


    }

    /**
     * 判断文件是否存在
     * */
    private boolean isExist(String path){
        File file = new File(path);
        if (file.exists()&&file.isFile()) return true;
        return false;
    }

    /**
     *删除文件
     * */
    public  boolean deleteFile(){
        String[] array = this.pathArrayIsExist();
        Arrays.asList(array).forEach(x->{
            new File(x).delete();
        });
        return false;
    }

    /**
     * 判断文件是否存在
     * */
    private String []  pathArrayIsExist(){
        String [] path=new String[pathArray.length];
        int i=0;
        Arrays.asList(pathArray).forEach(x->{
            if (this.isExist(x)) path[i]=x;
        });
        return path;
    }




}
