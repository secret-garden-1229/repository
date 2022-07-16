package com.cn.oa.business.mapper;

import com.cn.oa.domain.Note;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NoteMapper {


    List<Note> selectAllNote(@Param("estatus") Integer estatus);
    List<Note> selectRecord();

    Integer insertSelective(Note note);
    @Update("update note set estatus=#{estatus} where nid=#{nid} ")
    Integer updateNote(@Param("estatus") Integer estatus,@Param("nid") Integer nid);

}
