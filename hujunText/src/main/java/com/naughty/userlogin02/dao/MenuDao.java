package com.naughty.userlogin02.dao;


import com.naughty.userlogin02.Po.MainMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    public List<MainMenu> getMainMenus();
}
