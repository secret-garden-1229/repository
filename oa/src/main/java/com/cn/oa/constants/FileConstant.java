package com.cn.oa.constants;
/**
 *枚举文件类型
 * */
public enum FileConstant {
    DOC,DOCX,EXCEX;
    private final String [] imgFormat={"jpeg","jpg","gif","png"};//图片类型格式


    FileConstant() {
    }


    public  String[] getAllFileFormat(){
        System.out.println(imgFormat);
        return imgFormat;
    }




}
