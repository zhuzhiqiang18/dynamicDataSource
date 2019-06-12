package com.zzq.service;

import com.zzq.model.User;

public interface UserService {
    public int save(Long id, User user);
    public User select(Long id);
    public int update(Long id, User user);
    public int delete(Long id);
}
