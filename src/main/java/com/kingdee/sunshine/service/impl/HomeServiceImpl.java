package com.kingdee.sunshine.service.impl;

import com.kingdee.sunshine.entity.SysDept;
import com.kingdee.sunshine.entity.SysUser;
import com.kingdee.sunshine.service.DeptService;
import com.kingdee.sunshine.service.HomeService;
import com.kingdee.sunshine.service.PermissionService;
import com.kingdee.sunshine.service.UserService;
import com.kingdee.sunshine.vo.resp.HomeRespVO;
import com.kingdee.sunshine.vo.resp.PermissionRespNode;
import com.kingdee.sunshine.vo.resp.UserInfoRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    private UserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private PermissionService permissionService;

    @Override
    public HomeRespVO getHomeInfo(String userId) {


        SysUser sysUser = userService.getById(userId);
        UserInfoRespVO vo = new UserInfoRespVO();

        if (sysUser != null) {
            BeanUtils.copyProperties(sysUser, vo);
            SysDept sysDept = deptService.getById(sysUser.getDeptId());
            if (sysDept != null) {
                vo.setDeptId(sysDept.getId());
                vo.setDeptName(sysDept.getName());
            }
        }

        List<PermissionRespNode> menus = permissionService.permissionTreeList(userId);

        HomeRespVO respVO = new HomeRespVO();
        respVO.setMenus(menus);
        respVO.setUserInfo(vo);

        return respVO;
    }
}
