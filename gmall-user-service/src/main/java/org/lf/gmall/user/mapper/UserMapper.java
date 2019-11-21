package org.lf.gmall.user.mapper;

import org.lf.gmall.api.model.UmsMember;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserMapper extends Mapper<UmsMember> {
    List<UmsMember> selectAllUser();
}
