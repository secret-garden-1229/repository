package com.cn.oa.business.service;

import com.cn.oa.domain.Note;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoteService {

    List<Note> selectAllNote(Integer did,Integer position,Integer eid,Integer estatus);
    List<Note> selectRecord();
    Integer insertSelective(Note note);
    Integer updateNote(Integer estatus, Integer nid);
}
