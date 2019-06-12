package com.zzq.service.impl;

import com.zzq.dao.UserMapper;
import com.zzq.model.User;
import com.zzq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;

    @Override
    public int save(Long id, User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User select(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
