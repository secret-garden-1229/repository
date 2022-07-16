package com.cn.oa.business.service.imp;

import com.cn.oa.business.mapper.NoteMapper;
import com.cn.oa.business.service.NoteService;
import com.cn.oa.domain.Note;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoteServiceImp implements NoteService {

    @Resource
    private NoteMapper noteMapper;

    @Override
    public List<Note> selectAllNote(Integer estatus) {
        return noteMapper.selectAllNote( estatus);
    }

    @Override
    public List<Note> selectRecord() {
        return noteMapper.selectRecord();
    }

    @Override
    public Integer insertSelective(Note note) {
        return noteMapper.insertSelective(note);
    }

    @Override
    public Integer updateNote(Integer estatus, Integer nid) {
        return noteMapper.updateNote(estatus,nid);
    }
}

