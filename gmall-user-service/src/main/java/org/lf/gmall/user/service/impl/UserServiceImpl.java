package org.lf.gmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.lf.gmall.api.model.UmsMember;
import org.lf.gmall.api.service.UserService;
import org.lf.gmall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UmsMember> getAllUser() {
        return userMapper.selectAll();
    }
}
