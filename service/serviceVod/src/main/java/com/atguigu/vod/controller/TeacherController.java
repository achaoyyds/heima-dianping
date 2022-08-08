package com.atguigu.vod.controller;


import com.atguigu.ggkt.model.vod.Teacher;

import com.atguigu.R;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.atguigu.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.jdbc.StringUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.LambdaConversionException;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-03
 */
@RestController
@RequestMapping("/admin/vod/teacher")
@CrossOrigin
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public R getAll() {
       return R.ok(teacherService.list());
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id) {
        Teacher teacher = teacherService.getById(id);
        return R.ok(teacher);
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") long id) {
        boolean flag = teacherService.removeById(id);
        return flag ? R.ok("删除成功！") : R.fail("删除失败！");
    }
    @DeleteMapping
    public R deleteBatch(@RequestParam List<Long> ids) {
        boolean flag = teacherService.removeByIds(ids);
        return flag ? R.ok("批量删除成功！") : R.fail("批量删除失败！");
    }

    @PostMapping("page/{currentPage}/{pageSize}")
    public R page(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestBody TeacherQueryVo teacherQueryVo)  {
        Page<Teacher> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();

        if (teacherQueryVo != null) {
            wrapper.like(teacherQueryVo.getName() != null, Teacher::getName, teacherQueryVo.getName());
            wrapper.like(teacherQueryVo.getLevel() != null, Teacher::getLevel, teacherQueryVo.getLevel());
            wrapper.gt(teacherQueryVo.getJoinDateBegin()!=null,Teacher::getCreateTime,teacherQueryVo.getJoinDateBegin());
            wrapper.lt(teacherQueryVo.getJoinDateEnd()!=null,Teacher::getUpdateTime,teacherQueryVo.getJoinDateEnd());
        }
        teacherService.page(page, wrapper);
        return R.ok(page);
    }

    @PostMapping
    public R save(@RequestBody Teacher teacher) {
        boolean flag = teacherService.save(teacher);
        return flag ? R.ok("添加成功！") : R.fail("添加失败");
    }

    @PutMapping
    public R update(@RequestBody Teacher teacher){
        boolean flag = teacherService.updateById(teacher);
        return flag ? R.ok("修改成功！") : R.fail("修改失败！");
    }


}

