package fileupload;

import constant.FileConstant;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    public FileUploadUtils() {
    }

    public FileUploadUtils(String...path) {
        this.pathArray = path;
    }

    public FileUploadUtils(MultipartFile multipartFile){
        this.multipartFile=multipartFile;
    }


    /**
     * 对上传的我文件类型进行判断
     * */
    private Object verifyFileType(MultipartFile multipartFile){
        if (multipartFile.isEmpty()){
            return "上传的文件不能为空！";
        }
        String type = multipartFile.getContentType();
        Integer index =type.indexOf("/");
        String formats = type.substring(index+1, type.length()).toLowerCase();
        if (!Arrays.asList(arrayFormat).contains(formats)){
            return  "文件格式不对";
        }
        this.format=formats;
        return formats;
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
     * */
    public  void saveFile() throws IOException {
        String type = (String) this.verifyFileType(multipartFile);
        String path =this.getClass().getClassLoader().getResource("").getPath();
        path=path+"imgFile";
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        path=path+"/"+rename();
        File file2 = new File(path);
        multipartFile.transferTo(file2);
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
