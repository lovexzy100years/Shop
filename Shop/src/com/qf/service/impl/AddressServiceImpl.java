package com.qf.service.impl;

import com.qf.mapper.IAddressMapper;
import com.qf.mapper.IUserMapper;
import com.qf.mapper.impl.AddressMapperImpl;
import com.qf.mapper.impl.UserMapperImpl;
import com.qf.pojo.Address;
import com.qf.pojo.User;
import com.qf.service.IAddressService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class AddressServiceImpl implements IAddressService {

    private IAddressMapper addressMapper=new AddressMapperImpl();
    private IUserMapper userMapper=new UserMapperImpl();
    @Override
    public void addAddress(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {

        String userId = request.getParameter("userId");
        System.out.println("---"+userId);
        Map<String, String[]> map = request.getParameterMap();
        Address address = new Address();
        BeanUtils.populate(address,map);
        System.out.println("--"+userId);


        List<Address> addresses = addressMapper.queryByUserId(Integer.parseInt(userId));
        if (addresses.isEmpty()) {
            address.setIsdefault("是");
        }else{
            address.setIsdefault("否");
        }
        addressMapper.add(address);
    }


    //获取用户ID对应的地址列表
    @Override
    public void getAllAddress(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        List<Address> addresses = addressMapper.queryByUserId(Integer.parseInt(userId));
        request.setAttribute("addresses",addresses);
    }

    @Override
    public Address initModify(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Address address = addressMapper.queryById(Integer.parseInt(id));
        System.out.println(id);
        System.out.println(address.getIsdefault());
        return address;
    }

    @Override
    public void modify(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        Address newAddress = new Address();
        BeanUtils.populate(newAddress,map);
//        System.out.println(newAddress.getIsdefault());
        addressMapper.update(newAddress);
        System.out.println(newAddress);
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        System.out.println(id);
        addressMapper.delete(Integer.parseInt(id));
    }


}
