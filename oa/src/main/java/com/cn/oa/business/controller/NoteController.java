package com.cn.oa.business.controller;

import com.cn.oa.business.service.NoteService;
import com.cn.oa.domain.Employee;
import com.cn.oa.domain.Note;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
public class NoteController {

    @Resource
    private NoteService noteService;

    @GetMapping(value = "noteController/selectAllNote")
    public ModelAndView selectAllNote(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "4")Integer pageSize,@RequestParam(required = false,defaultValue = "") Integer estatus,HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        PageHelper.startPage(pageNum,pageSize);
        Employee emplyee = (Employee) session.getAttribute("user");
        Integer did=emplyee.getDid();
        Integer position=emplyee.getPosition();
        Integer eid= emplyee.getEid();
        List<Note> notes = noteService.selectAllNote(did,position,eid,estatus);
        PageInfo<Note> pageInfo=new PageInfo<>(notes);
        modelAndView.addObject("notes",pageInfo);
        modelAndView.setViewName("/leave.jsp");
        return modelAndView;
    }

    @GetMapping(value = "selectRecord")
    public ModelAndView selectRecord(){
        ModelAndView modelAndView=new ModelAndView();
        List<Note> notes = noteService.selectRecord();
        modelAndView.addObject("notes",notes);
        modelAndView.setViewName("/leave.jsp");
        return modelAndView;
    }

    @PostMapping(value = "/noteController/addRecord")
    public boolean addRecord(@RequestBody Note note, HttpSession session){
        Employee employee = (Employee) session.getAttribute("user");
        note.setEid(employee.getEid());
        note.setReldate(new Date());
        note.setSubdate(new Date());
        note.setEstatus(0);
        Integer integer = noteService.insertSelective(note);
        if (integer!=1){
            return false;
        }
        return true;
    }

    @GetMapping(value = "/noteController/addRecord")
    public boolean updateNote(@RequestParam(value = "estatus") Integer estatus, @RequestParam(value = "nid") Integer nid){
        Integer integer = noteService.updateNote(estatus, nid);
        if (integer!=1)return false;
        return true;
    }

}
